package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.dao.TripArticleDao;
import com.ronvel.farztev.admin.dao.model.TripAlbumModel;
import com.ronvel.farztev.admin.dao.model.TripArticleModel;
import com.ronvel.farztev.admin.dao.model.TripArticleModelId;
import com.ronvel.farztev.admin.service.TripArticleService;

@Service
public class TripArticleServiceImpl implements TripArticleService {

	private final TripArticleDao tripArticleDao;
	private final ArticleServiceImpl articleService;
	private final TripServiceImpl tripService;

	@Autowired
	public TripArticleServiceImpl(TripArticleDao tripArticleDao, ArticleServiceImpl articleService,
			TripServiceImpl tripService) {
		this.tripArticleDao = tripArticleDao;
		this.articleService = articleService;
		this.tripService = tripService;
	}

	@Override
	public List<Article> listTripArticle(Long tripId) {
		List<Article> articles = new ArrayList<Article>();
		Iterable<TripArticleModel> tripArticles = tripArticleDao.getByTripArticleIdTripId(tripId);
		for(TripArticleModel t : tripArticles) {
			Article article = articleService.findArticleById(t.getTripArticleId().getArticleId()).get();
			articles.add(article);
		}
		return articles;
	}

	@Override
	public void addTripArticle(Long tripId, Long articleId) {
		TripArticleModel tripArticleModel = new TripArticleModel(
				new TripArticleModelId(tripId, articleId));
		tripArticleDao.save(tripArticleModel);
	}

	@Override
	public void deleteTripArticle(Long tripId, Long articleId) {
		tripArticleDao.delete(new TripArticleModelId(tripId, articleId));
	}

	@Override
	public TripDto getTripByArticle(Long articleId) {
		List<TripArticleModel> tripArticles = tripArticleDao.getByTripArticleIdArticleId(articleId);
		return tripService.getTrip(tripArticles.get(0).getTripArticleId().getTripId());
	}

}
