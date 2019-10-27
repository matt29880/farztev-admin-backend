package com.ronvel.farztev.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ronvel.farztev.admin.controller.dto.Article;

public interface TripArticleController {

	@GetMapping(value = "/api/triparticle/{tripId}", produces = { "application/json" })
	ResponseEntity<List<Article>> listArticlesByTrip(@PathVariable("tripId") Long tripId);

	@PostMapping(value = "/api/triparticle/{tripId}/{articleId}", produces = { "application/json" })
	ResponseEntity<Void> addTrip(@PathVariable("tripId") Long tripId, @PathVariable("articleId") Long articleId);

	@DeleteMapping(value = "/api/triparticle/{tripId}")
	ResponseEntity<Void> deleteTrip(@PathVariable("tripId") Long tripId);
}
