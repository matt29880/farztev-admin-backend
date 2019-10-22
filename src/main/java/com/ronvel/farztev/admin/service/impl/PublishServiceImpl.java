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

@Service
public class PublishServiceImpl implements PublishService {

	private final HtmlService htmlService;
	private final TripService tripService;
	
	private static final String ROOT_FOLDER = "/home/mathieu/tmp/test";
	
	public PublishServiceImpl(HtmlService htmlService, TripService tripService) {
		this.htmlService = htmlService;
		this.tripService = tripService;
	}

	@Override
	public void publishAllWebsite() throws IOException {
		copyCss(ROOT_FOLDER);
		Homepage homepage = new Homepage();
		List<TripDto> trips = tripService.listTrips(true);
		List<Timeline> timelines = trips.stream()
				.map(this::mapToTimeline)
				.collect(Collectors.toList());
		homepage.setTimelines(timelines);
		String html = htmlService.generateHomepage(homepage);
		FileUtils.write(new File(ROOT_FOLDER + "/index.html"), html, StandardCharsets.UTF_8);
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
		InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("./styles.css");
		FileUtils.copyInputStreamToFile(is, new File(rootFolder + "/style.css"));
	}
	
}
