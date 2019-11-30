package com.ronvel.farztev.admin.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Helper;
import com.github.jknack.handlebars.Options;
import com.github.jknack.handlebars.Template;
import com.github.jknack.handlebars.internal.lang3.StringEscapeUtils;
import com.ronvel.farztev.admin.component.AlbumPage;
import com.ronvel.farztev.admin.component.ArticleDescription;
import com.ronvel.farztev.admin.component.ArticleDescriptionParagraph;
import com.ronvel.farztev.admin.component.ArticleDescriptionTitle;
import com.ronvel.farztev.admin.component.ArticleDescriptionType;
import com.ronvel.farztev.admin.component.ArticlePage;
import com.ronvel.farztev.admin.component.Homepage;
import com.ronvel.farztev.admin.component.TripPage;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.service.HtmlService;

@Service
public class HtmlServiceImpl implements HtmlService {

	private final Handlebars handlebars;
	
	public HtmlServiceImpl() {
		handlebars = new Handlebars();
		handlebars.registerHelper("article-description", new Helper<ArticleDescription>() {
			public String apply(ArticleDescription description, Options options) throws IOException {
				final Handlebars handlebars = new Handlebars();
				String templateAsString;
				if (description.getType() == ArticleDescriptionType.UL) {
					templateAsString = loadTemplate("templates/article-descriptions/ul.tpl");
				} else {
					templateAsString = loadTemplate("templates/article-descriptions/content.tpl");
				}
				Template template = handlebars.compileInline(templateAsString);
				String res = template.apply(description);
				return res;
			}
		});
	}
	
	@Override
	public String generateHomepage(Homepage homepage) throws IOException {
		String top = loadTemplate("templates/top.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");
		
		String templateAsString = loadTemplate("templates/homepage.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return top + template.apply(homepage) + bottom;
	}

	@Override
	public String generateTrip(TripPage tripPage) throws IOException {
		String top = loadTemplate("templates/topN1.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");
		
		String templateAsString = loadTemplate("templates/trip.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return top + template.apply(tripPage) + bottom;
	}

	@Override
	public String generateArticle(Article article) throws IOException {
		String top = loadTemplate("templates/topN1.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");
		
		String templateAsString = loadTemplate("templates/article.tpl");
		Template template = handlebars.compileInline(templateAsString);
		
		ArticlePage articlePage = new ArticlePage();
		articlePage.setId(article.getId());
		articlePage.setName(article.getName());
		articlePage.setThumbnailUrl(article.getThumbnailUrl());
		articlePage.setCreated(article.getCreated());
		articlePage.setUpdated(article.getUpdated());
		
		ObjectMapper mapper = new ObjectMapper();
		List<ArticleDescription> descriptions = mapper.readValue(article.getDescription(), new TypeReference<List<ArticleDescription>>() {});
		descriptions.stream()
			.filter(d -> d instanceof ArticleDescriptionParagraph)
			.map(d -> (ArticleDescriptionParagraph)d)
			.forEach(d -> d.setContent(StringEscapeUtils.escapeHtml4(d.getContent())));
		descriptions.stream()
			.filter(d -> d instanceof ArticleDescriptionTitle)
			.map(d -> (ArticleDescriptionTitle)d)
			.forEach(d -> d.setContent(StringEscapeUtils.escapeHtml4(d.getContent())));
		articlePage.setDescriptions(descriptions);

		return top + template.apply(articlePage) + bottom;
	}

	@Override
	public String generateAlbum(Album album, List<ListMedia> medias) throws IOException {
		String top = loadTemplate("templates/topN1.tpl");
		String bottom = loadTemplate("templates/bottom.tpl");

		AlbumPage albumPage = new AlbumPage();
		albumPage.setId(album.getId());
		albumPage.setName(album.getName());
		albumPage.setThumbnailUrl(album.getThumbnailUrl());
		albumPage.setCreated(album.getCreated());
		albumPage.setUpdated(album.getUpdated());
		albumPage.setImages(medias);
		
		String templateAsString = loadTemplate("templates/album.tpl");
		Template template = handlebars.compileInline(templateAsString);

		return top + template.apply(albumPage) + bottom;
	}

	private String loadTemplate(String path) throws IOException {
		InputStream is = HtmlServiceImpl.class.getClassLoader().getResourceAsStream(path);
		return IOUtils.toString(is, StandardCharsets.UTF_8);
	}

}
