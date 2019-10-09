package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import com.github.jknack.handlebars.internal.lang3.StringEscapeUtils;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.Timeline;
import com.ronvel.farztev.admin.controller.dto.ListArticle;
import com.ronvel.farztev.admin.service.ArticleService;
import com.ronvel.farztev.admin.service.HtmlService;
import com.ronvel.farztev.admin.service.PublishService;

@Service
public class PublishServiceImpl implements PublishService {

	private final HtmlService htmlService;
	private final ArticleService articleService;
	
	private static final String ROOT_FOLDER = "/home/mathieu/tmp/test";
	
	public PublishServiceImpl(HtmlService htmlService, ArticleService articleService) {
		this.htmlService = htmlService;
		this.articleService = articleService;
	}

	@Override
	public void publishAllWebsite() throws IOException {
		copyCss(ROOT_FOLDER);
		Homepage homepage = new Homepage();
		List<ListArticle> articles = articleService.listArticles();
		List<Timeline> timelines = articles.stream()
				.map(this::mapToTimeline)
				.collect(Collectors.toList());
		homepage.setTimelines(timelines);
		String html = htmlService.generateHomepage(homepage);
		FileUtils.write(new File(ROOT_FOLDER + "/index.html"), html, StandardCharsets.UTF_8);
	}
	
	private Timeline mapToTimeline(ListArticle article) {
		Timeline timeline = new Timeline();
		timeline.setTitle(StringEscapeUtils.escapeHtml4(article.getName()));
		timeline.setFuture(false);
		timeline.setImage(article.getThumbnailUrl());
		timeline.setDate(article.getCreated().toString());
		return timeline;
	}
	
	public static void copyCss(String rootFolder) throws IOException {
		URL url = ClassLoader.getSystemClassLoader().getResource("./styles.css");
		File css = new File(url.getFile());
		FileUtils.copyFile(css, new File(rootFolder + "/style.css"));
	}
	
}
