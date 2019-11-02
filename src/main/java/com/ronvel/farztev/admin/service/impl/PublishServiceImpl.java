package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.internal.lang3.StringEscapeUtils;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.Timeline;
import com.ronvel.farztev.admin.component.TripPage;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.enums.MediaType;
import com.ronvel.farztev.admin.service.AlbumService;
import com.ronvel.farztev.admin.service.ArticleService;
import com.ronvel.farztev.admin.service.HtmlService;
import com.ronvel.farztev.admin.service.MediaService;
import com.ronvel.farztev.admin.service.PublishService;
import com.ronvel.farztev.admin.service.TripAlbumService;
import com.ronvel.farztev.admin.service.TripArticleService;
import com.ronvel.farztev.admin.service.TripService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublishServiceImpl implements PublishService {

	private final HtmlService htmlService;
	private final TripService tripService;
	private final TripArticleService tripArticleService;
	private final TripAlbumService tripAlbumService;
	private final ArticleService articleService;
	private final AlbumService albumService;
	private final MediaService mediaService;
	private final String host;
	private final String username;
	private final String password;
	private final String environmentUrl;
	
	private static final String ROOT_FOLDER = "/tmp/farzteo";
	
	public PublishServiceImpl(HtmlService htmlService, 
			TripService tripService, 
			TripArticleService tripArticleService, 
			TripAlbumService tripAlbumService, 
			ArticleService articleService, 
			AlbumService albumService, 
			MediaService mediaService,
			@Value("${application.ftp.host}") String host,
			@Value("${application.ftp.username}") String username,
			@Value("${application.ftp.password}") String password,
			@Value("${application.environment.url}") String environmentUrl) {
		this.htmlService = htmlService;
		this.tripService = tripService;
		this.tripArticleService = tripArticleService;
		this.tripAlbumService = tripAlbumService;
		this.articleService = articleService;
		this.albumService = albumService;
		this.mediaService = mediaService;
		this.host = host;
		this.username = username;
		this.password = password;
		this.environmentUrl = environmentUrl; 
	}

	@Override
	public void publishAllWebsite() throws IOException {
		log.info("Generate homepage - start");
		File css = copyCss(ROOT_FOLDER);
		log.info("Generate homepage - css copied");
		Homepage homepage = new Homepage();
		List<TripDto> trips = tripService.listTrips(true);
		List<Timeline> timelines = trips.stream()
				.map(this::mapToTimeline)
				.collect(Collectors.toList());
		homepage.setTimelines(timelines);
		log.info("Generate homepage - mapping done");
		String html = htmlService.generateHomepage(homepage);
		log.info("Generate homepage - html generated = {}", html);
		File indexHtml = new File(ROOT_FOLDER + "/index.html");
		FileUtils.write(indexHtml, html, StandardCharsets.UTF_8);
		log.info("Generate homepage - published in {}", indexHtml.getAbsolutePath()); 
		
		Map<Long, File> tripHtmls = generateTrips();
		Map<Long, File> articleHtmls = generateArticles();
		Map<Long, File> albumHtmls = generateAlbums();
		
		sendToFtp(indexHtml, css, tripHtmls, articleHtmls, albumHtmls);
	}
	
	private Map<Long, File> generateTrips() throws IOException {
		List<TripDto> trips = tripService.listTrips(true);
		Map<Long, File> tripHtmls = new LinkedHashMap<>();
		for(TripDto trip : trips) {
			List<Article> articles = tripArticleService.listTripArticle(trip.getId());
			articles.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			List<Album> albums = tripAlbumService.listTripAlbum(trip.getId());
			albums.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			String tripHtml = htmlService.generateTrip(new TripPage(trip, articles, albums));
			File tripFile = new File(ROOT_FOLDER + "/trips/"+ trip.getId() + ".html");
			FileUtils.write(tripFile, tripHtml, StandardCharsets.UTF_8);
			tripHtmls.put(trip.getId(), tripFile);
		}
		return tripHtmls;
	}
	
	private Map<Long, File> generateArticles() throws IOException {
		List<Article> articles = articleService.listArticles().stream()
				.map(a -> articleService.findArticleById(a.getId()).get())
				.collect(Collectors.toList());
		Map<Long, File> tripHtmls = new LinkedHashMap<>();
		for(Article article : articles) {
			articles.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			File articleFile = new File(ROOT_FOLDER + "/articles/"+ article.getId() + ".html");
			String articleHtml = htmlService.generateArticle(article);
			FileUtils.write(articleFile, articleHtml, StandardCharsets.UTF_8);
			tripHtmls.put(article.getId(), articleFile);
		}
		return tripHtmls;
	}
	
	private Map<Long, File> generateAlbums() throws IOException {
		List<Album> albums = albumService.listAlbums().stream()
				.map(a -> albumService.findAlbumById(a.getId()).get())
				.collect(Collectors.toList());
		Map<Long, File> tripHtmls = new LinkedHashMap<>();
		for(Album album : albums) {
			List<ListMedia> medias = mediaService.listAlbumMedias(album.getId(), MediaType.PHOTO);
			albums.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			File albumFile = new File(ROOT_FOLDER + "/albums/"+ album.getId() + ".html");
			String albumHtml = htmlService.generateAlbum(album, medias);
			FileUtils.write(albumFile, albumHtml, StandardCharsets.UTF_8);
			tripHtmls.put(album.getId(), albumFile);
		}
		return tripHtmls;
	}
	
	private Timeline mapToTimeline(TripDto trip) {
		Timeline timeline = new Timeline();
		timeline.setId(trip.getId());
		timeline.setTitle(StringEscapeUtils.escapeHtml4(trip.getName()));
		timeline.setSummary(trip.getSummary());
		timeline.setFuture(false);
		timeline.setImage(trip.getThumbnailUrl());
		if (trip.getStart() != null) {
			timeline.setStart(trip.getStart().toString());			
		}
		if (trip.getEnd() != null) {
			timeline.setEnd(trip.getEnd().toString());			
		}
		return timeline;
	}
	
	public static File copyCss(String rootFolder) throws IOException {
		InputStream is = PublishServiceImpl.class.getClassLoader().getResourceAsStream("styles.css");
		File cssFile = new File(rootFolder + "/styles.css");
		FileUtils.copyInputStreamToFile(is, cssFile);
		return cssFile;
	}
	
	public void sendToFtp(File homepage, File css, 
			Map<Long, File> tripHtmls, Map<Long, File> articleHtmls, Map<Long, File> albumHtmls) {
		FileInputStream fis = null;
		FTPClient client = null;

		try {
			generateThumbnails();

			client = connectClient();
			client.changeWorkingDirectory("farztev_test");

			client.setFileType(FTP.ASCII_FILE_TYPE);
			
			boolean res = client.storeFile("index.html", FileUtils.openInputStream(homepage));
			log.info("index.html send to ftp : {}", res);
			res = client.storeFile("styles.css", FileUtils.openInputStream(css));
			log.info("styles.css send to ftp : {}", res);

			client.changeWorkingDirectory("trips");
			for (Entry<Long, File> entry : tripHtmls.entrySet()) {
				res = client.storeFile(entry.getKey() + ".html", FileUtils.openInputStream(entry.getValue()));
				log.info("trips/{}.html send to ftp : {}", entry.getKey(), res);
			}
			client.changeWorkingDirectory("../articles");
			for (Entry<Long, File> entry : articleHtmls.entrySet()) {
				res = client.storeFile(entry.getKey() + ".html", FileUtils.openInputStream(entry.getValue()));
				log.info("articles/{}.html send to ftp : {}", entry.getKey(), res);
			}
			client.changeWorkingDirectory("../albums");
			for (Entry<Long, File> entry : albumHtmls.entrySet()) {
				res = client.storeFile(entry.getKey() + ".html", FileUtils.openInputStream(entry.getValue()));
				log.info("albums/{}.html send to ftp : {}", entry.getKey(), res);
			}

			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				client.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private FTPClient connectClient() throws SocketException, IOException {
		FTPClient client = new FTPClient();

		client.connect(host);
		int reply = client.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
        	client.disconnect();
            throw new RuntimeException("Exception in connecting to FTP Server");
        }
		client.login(username, password);
		client.enterLocalPassiveMode(); 
		client.setControlKeepAliveTimeout(300); // set timeout to 5 minutes
		client.setSoTimeout(300000);
		return client;
	}
	
	private void generateThumbnails() throws SocketException, IOException {
		FTPClient client = new FTPClient();
		FTPClient client2 = new FTPClient();
		client = connectClient();
		client2 = connectClient();
		client.setFileType(FTP.BINARY_FILE_TYPE);
		client2.setFileType(FTP.BINARY_FILE_TYPE);

		client.changeWorkingDirectory("farztev_test");
		client.changeWorkingDirectory("images");
		client2.changeWorkingDirectory("farztev_test");
		client2.changeWorkingDirectory("images");
		client2.changeWorkingDirectory("thumbnails");

		generateThumbails(client, client2, "", "");
	}
	
	private void generateThumbails(FTPClient client,FTPClient client2, String directory, String absolutePath) throws IOException {
		System.out.println("Directory : " + directory);
		if (!directory.isEmpty()) {
			client.changeWorkingDirectory(directory);
			if (client2.makeDirectory(directory)) {
				System.out.println("Directory has been created : " + absolutePath);
			}
			client2.changeWorkingDirectory(directory);
		}
		
		FTPFile[] files = client.listFiles();
		for(FTPFile file : files) {
			String fileName = file.getName();
			if(file.isFile()) {
				String filePath = absolutePath + "/" + fileName;
				String fileToCopy = filePath.substring(1, filePath.length());
				scale(fileToCopy);
				System.out.println("File : " + fileToCopy);
				System.out.println("Thumbnail generated : " + fileToCopy);
			}
		}
		
		FTPFile[] subDirectories = client.listDirectories();
		for(FTPFile subDirectory : subDirectories) {
			String subDirectoryName = subDirectory.getName();
			if(!(".".equals(subDirectoryName) || "..".equals(subDirectoryName) || "thumbnails".equals(subDirectoryName))) {
				generateThumbails(client, client2, subDirectoryName, absolutePath + "/" + subDirectoryName);
			}
		}
		client.changeWorkingDirectory("..");
		client2.changeWorkingDirectory("..");
	}
	
	private void scale(String absolutePath) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(environmentUrl + "/images/scaler.php?filename=" + absolutePath);
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        try (CloseableHttpResponse response = httpClient.execute(request)) {

            // Get HttpResponse Status
            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }
	}
	

	
}
