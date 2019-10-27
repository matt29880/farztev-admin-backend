package com.ronvel.farztev.admin.service;

import java.util.List;

import com.ronvel.farztev.admin.controller.dto.Article;

public interface TripArticleService {

	List<Article> listTripArticle(Long tripId);
	
	void addTripArticle(Long tripId, Long articleId);

	void deleteTripArticle(Long tripId, Long articleId);
	
}
