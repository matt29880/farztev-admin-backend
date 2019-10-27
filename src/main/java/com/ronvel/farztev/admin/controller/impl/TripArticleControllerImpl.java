package com.ronvel.farztev.admin.controller.impl;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ronvel.farztev.admin.controller.TripArticleController;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.service.TripArticleService;

@Controller
public class TripArticleControllerImpl implements TripArticleController {

	private final TripArticleService tripArticleService;

	@Autowired
	public TripArticleControllerImpl(TripArticleService tripArticleService) {
		this.tripArticleService = tripArticleService;
	}

	@GetMapping(value = "/api/triparticle/{tripId}", produces = { "application/json" })
	public ResponseEntity<List<Article>> listArticlesByTrip(@PathVariable("tripId") Long tripId) {
		List<Article> articles = tripArticleService.listTripArticle(tripId);
		return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
	}

	@PostMapping(value = "/api/triparticle/{tripId}/{articleId}", produces = { "application/json" })
	public ResponseEntity<Void> addTrip(@PathVariable("tripId") Long tripId, @PathVariable("articleId") Long articleId) {
		tripArticleService.addTripArticle(tripId, articleId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/triparticle/{tripId}")
	public ResponseEntity<Void> deleteTrip(@PathVariable("tripId") Long tripId) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
