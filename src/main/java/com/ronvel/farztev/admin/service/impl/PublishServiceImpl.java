package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.net.ftp.FTPClient;
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

import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.Timeline;
import com.ronvel.farztev.admin.component.TripPage;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.PublishType;
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
	private final String environmentSuffix;
	private final String environmentUrl;
	
	private static final String TMP_FOLDER = "/tmp/farzteo";
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
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
			@Value("${application.environment.suffix}") String environmentSuffix,
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
		this.environmentSuffix = environmentSuffix; 
		this.environmentUrl = environmentUrl; 
	}

	@Override
	public void publishAllWebsite(PublishType publishType) throws IOException {
		log.info("Generate homepage - start");
		File mainCss = copyCss(TMP_FOLDER, "styles.css");
		log.info("Generate homepage - styles.css copied");
		File timelineCss = copyCss(TMP_FOLDER, "timeline.css");
		log.info("Generate homepage - timeline.css copied");
		Homepage homepage = new Homepage();
		List<TripDto> trips = tripService.listTrips(true);
		List<Timeline> timelines = trips.stream()
				.map(this::mapToTimeline)
				.collect(Collectors.toList());
		homepage.setTimelines(timelines);
		log.info("Generate homepage - mapping done");
		String html = htmlService.generateHomepage(homepage);
		log.info("Generate homepage - html generated = {}", html);
		File indexHtml = new File(TMP_FOLDER + "/index.html");
		FileUtils.write(indexHtml, html, StandardCharsets.UTF_8);
		log.info("Generate homepage - published in {}", indexHtml.getAbsolutePath()); 
		
		Map<Long, File> tripHtmls = generateTrips();
		Map<Long, File> articleHtmls = generateArticles();
		Map<Long, File> albumHtmls = generateAlbums();

//		if (publishType != PublishType.ONLY_HTML) {
//			new ThumbnailGenerator(host, username, password, environmentSuffix, environmentUrl, 300, 300, publishType)
//					.generateThumbnails();
//			new ThumbnailGenerator(host, username, password, environmentSuffix, environmentUrl, 600, 600, publishType)
//					.generateThumbnails();
//		}
		
		sendToFtp(indexHtml, mainCss, timelineCss, tripHtmls, articleHtmls, albumHtmls);
	}
	
	private Map<Long, File> generateTrips() throws IOException {
		List<TripDto> trips = tripService.listTrips(true);
		Map<Long, File> tripHtmls = new LinkedHashMap<>();
		for(TripDto trip : trips) {
			trip.setName(StringEscapeUtils.escapeHtml4(trip.getName()));
			trip.setSummary(StringEscapeUtils.escapeHtml4(trip.getSummary()));
			List<Article> articles = tripArticleService.listTripArticle(trip.getId());
			articles.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			List<Album> albums = tripAlbumService.listTripAlbum(trip.getId());
			albums.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			String tripHtml = htmlService.generateTrip(new TripPage(trip, articles, albums));
			File tripFile = new File(TMP_FOLDER + "/trips/"+ trip.getId() + ".html");
			FileUtils.write(tripFile, tripHtml, StandardCharsets.UTF_8);
			tripHtmls.put(trip.getId(), tripFile);
		}
		return tripHtmls;
	}
	
	private Map<Long, File> generateArticles() throws IOException {
		List<Article> articles = articleService.listArticles().stream()
				.map(a -> articleService.findArticleById(a.getId()).get())
				.collect(Collectors.toList());
		Map<Long, File> articlesHtml = new LinkedHashMap<>();
		for(Article article : articles) {
			articles.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			File articleFile = new File(TMP_FOLDER + "/articles/"+ article.getId() + ".html");
			TripDto trip = tripArticleService.getTripByArticle(article.getId());
			String articleHtml = htmlService.generateArticle(article, trip);
			FileUtils.write(articleFile, articleHtml, StandardCharsets.UTF_8);
			articlesHtml.put(article.getId(), articleFile);
		}
		return articlesHtml;
	}
	
	private Map<Long, File> generateAlbums() throws IOException {
		List<Album> albums = albumService.listAlbums().stream()
				.map(a -> albumService.findAlbumById(a.getId()).get())
				.collect(Collectors.toList());
		Map<Long, File> albumHtmls = new LinkedHashMap<>();
		for(Album album : albums) {
			List<ListMedia> medias = mediaService.listAlbumMedias(album.getId(), MediaType.PHOTO);
			albums.forEach(a -> a.setName(StringEscapeUtils.escapeHtml4(a.getName())));
			File albumFile = new File(TMP_FOLDER + "/albums/"+ album.getId() + ".html");
			TripDto albumTrip = tripAlbumService.getTripByAlbum(album.getId());
			String albumHtml = htmlService.generateAlbum(album, medias, albumTrip);
			FileUtils.write(albumFile, albumHtml, StandardCharsets.UTF_8);
			albumHtmls.put(album.getId(), albumFile);
		}
		return albumHtmls;
	}

	private Timeline mapToTimeline(TripDto trip) {
		Timeline timeline = new Timeline();
		timeline.setId(trip.getId());
		timeline.setTitle(StringEscapeUtils.escapeHtml4(trip.getName()));
		timeline.setSummary(StringEscapeUtils.escapeHtml4(trip.getSummary()));
		timeline.setFuture(false);
		timeline.setImage(trip.getThumbnailUrl());
		if (trip.getStart() != null) {
			timeline.setStart(dateTimeFormatter.format(trip.getStart()));			
		}
		if (trip.getEnd() != null) {
			timeline.setEnd(dateTimeFormatter.format(trip.getEnd()));			
		}
		timeline.setPeriodDescription(StringEscapeUtils.escapeHtml4(trip.getPeriodDescription()));
		return timeline;
	}
	
	public static File copyCss(String rootFolder, String filename) throws IOException {
		InputStream is = PublishServiceImpl.class.getClassLoader().getResourceAsStream(filename);
		File cssFile = new File(rootFolder + filename);
		FileUtils.copyInputStreamToFile(is, cssFile);
		return cssFile;
	}
	
	public void sendToFtp(File homepage, File css, File timelineCss, Map<Long, File> tripHtmls, Map<Long, File> articleHtmls,
			Map<Long, File> albumHtmls) {
		FileInputStream fis = null;
		FTPClient client = null;

		try {
			client = connectClient();
			log.info("environmentSuffix : {}",environmentSuffix);
			String baseFolder = "prod".equals(environmentSuffix) ? "farzteo" : "farzteo_" + environmentSuffix;
			log.info("Base directory : {}", baseFolder);
			client.changeWorkingDirectory(baseFolder);
			
			boolean res = client.storeFile("index.html", FileUtils.openInputStream(homepage));
			log.info("index.html send to ftp : {}", res);
			res = client.storeFile("styles.css", FileUtils.openInputStream(css));
			log.info("styles.css send to ftp : {}", res);
			res = client.storeFile("timeline.css", FileUtils.openInputStream(timelineCss));
			log.info("timeline.css send to ftp : {}", res);

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
			
			scale();
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
//		client.setFileType(FTP.ASCII_FILE_TYPE);
//		client.setControlEncoding("UTF-8");
		client.login(username, password);
		client.enterLocalPassiveMode(); 
		client.setControlKeepAliveTimeout(300); // set timeout to 5 minutes
		client.setSoTimeout(300000);
		return client;
	}
	
	private void scale() throws ClientProtocolException, IOException {
		String url = environmentUrl + "/images/scale.php";
		System.out.println(url);
		HttpGet request = new HttpGet(url);
        
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
