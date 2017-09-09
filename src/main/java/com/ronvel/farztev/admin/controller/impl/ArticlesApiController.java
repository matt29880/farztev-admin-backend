package com.ronvel.farztev.admin.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.ronvel.farztev.admin.controller.ArticlesApi;
import com.ronvel.farztev.admin.controller.model.Article;
import com.ronvel.farztev.admin.controller.model.SmallArticle;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-07T23:10:06.754+02:00")

@Controller
public class ArticlesApiController implements ArticlesApi {

	public ResponseEntity<Article> articlesArticleIdGet(
			@ApiParam(value = "ID of the article to return", required = true) @PathVariable("articleId") Long articleId) {
		Article article = new Article();
		article.setId(1L);
		article.setCountry("DE");
		article.setName("Visit in Berlin");
		article.setDescription("Berlin is a the capital of Germany");
		article.setPhoto("http://lorempixel.com/400/200/city/");
		return new ResponseEntity<Article>(article,HttpStatus.OK);
	}

	public ResponseEntity<List<SmallArticle>> articlesGet() {
		List<SmallArticle> articles = new ArrayList<>();
		SmallArticle article = new SmallArticle();
		article.setId(1L);
		article.setCountry("DE");
		article.setName("Visit in Berlin");
		article.setPhoto("http://lorempixel.com/400/200/city/");
		articles.add(article);
		article = new SmallArticle();
		article.setId(1L);
		article.setCountry("EN");
		article.setName("Visit in London");
		article.setPhoto("http://lorempixel.com/400/200/city/");
		articles.add(article);
		return new ResponseEntity<List<SmallArticle>>(articles, HttpStatus.OK);
	}

}
