package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.internal.lang3.StringEscapeUtils;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.Timeline;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.service.HtmlService;
import com.ronvel.farztev.admin.service.PublishService;
import com.ronvel.farztev.admin.service.TripService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PublishServiceImpl implements PublishService {

	private final HtmlService htmlService;
	private final TripService tripService;
	private final String host;
	private final String username;
	private final String password;
	
	private static final String ROOT_FOLDER = "/tmp/farzteo";
	
	public PublishServiceImpl(HtmlService htmlService, 
			TripService tripService, 
			@Value("${application.ftp.host}") String host,
			@Value("${application.ftp.username}") String username,
			@Value("${application.ftp.password}") String password) {
		this.htmlService = htmlService;
		this.tripService = tripService;
		this.host = host;
		this.username = username;
		this.password = password;
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
		
		sendToFtp(indexHtml, css, host, username, password);
	}
	
	private Timeline mapToTimeline(TripDto trip) {
		Timeline timeline = new Timeline();
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
	
	public static void sendToFtp(File homepage, File css, String host, String username, String password) {
		FTPClient client = new FTPClient();
		FileInputStream fis = null;

		try {
			client.connect(host);
			int reply = client.getReplyCode();
	        if (!FTPReply.isPositiveCompletion(reply)) {
	        	client.disconnect();
	            throw new RuntimeException("Exception in connecting to FTP Server");
	        }
			client.login(username, password);
			client.setFileType(FTP.BINARY_FILE_TYPE);
			client.enterLocalPassiveMode();

			client.changeWorkingDirectory("farztev_test");
			boolean res = client.storeFile("index.html", FileUtils.openInputStream(homepage));
			log.info("index.html send to ftp : {}", res);
			res = client.storeFile("styles.css", FileUtils.openInputStream(css));
			log.info("styles.css send to ftp : {}", res);
			
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
	
}
