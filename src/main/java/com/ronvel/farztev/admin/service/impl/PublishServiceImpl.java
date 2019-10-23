package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
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
	
	private static final String ROOT_FOLDER = "/tmp/farzteo";
	
	public PublishServiceImpl(HtmlService htmlService, TripService tripService) {
		this.htmlService = htmlService;
		this.tripService = tripService;
	}

	@Override
	public void publishAllWebsite() throws IOException {
		log.info("Generate homepage - start");
		copyCss(ROOT_FOLDER);
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
	
	public static void copyCss(String rootFolder) throws IOException {
		InputStream is = PublishServiceImpl.class.getClassLoader().getResourceAsStream("styles.css");
		FileUtils.copyInputStreamToFile(is, new File(rootFolder + "/style.css"));
	}
	
}
