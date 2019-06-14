package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListArticle;
import com.ronvel.farztev.admin.dao.ArticleDao;
import com.ronvel.farztev.admin.dao.MediaDao;
import com.ronvel.farztev.admin.dao.model.ArticleModel;
import com.ronvel.farztev.admin.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private ArticleDao articleDao;

  @Autowired
  private MediaDao mediaDao;

  @Override
  public Optional<Article> findArticleById(Long id) {
    Optional<Article> optional;
    ArticleModel articleModel = articleDao.findOne(id);
    if (articleModel == null) {
      optional = Optional.empty();
    } else {
      Article article = mapper.map(articleModel, Article.class);
      optional = Optional.of(article);
      if(articleModel.getThumbnail() != null) {
    	  article.setThumbnailId(articleModel.getThumbnail().getId());
    	  article.setThumbnailUrl(articleModel.getThumbnail().getUrl());
      }
    }
    return optional;
  }

  @Override
  public List<ListArticle> listArticles() {
    List<ListArticle> listArticles = new ArrayList<>();
    Iterable<ArticleModel> articles = articleDao.findAll();
    articles.forEach(articleModel -> {
    	ListArticle listArticle = mapper.map(articleModel, ListArticle.class);
        if(articleModel.getThumbnail() != null) {
        	listArticle.setThumbnailId(articleModel.getThumbnail().getId());
        	listArticle.setThumbnailUrl(articleModel.getThumbnail().getUrl());
        }
    	listArticles.add(listArticle);
    	});
    return listArticles;
  }

  @Override
  public Article addArticle(Article article) {
    Date created = new Date();
    article.setCreated(created);
    article.setUpdated(created);
    ArticleModel articleModel = mapper.map(article, ArticleModel.class);
    ArticleModel resultArticleModel = articleDao.save(articleModel);
    Article resultArticle = mapper.map(resultArticleModel, Article.class);
    if(article.getThumbnailId() != null) {
    	articleModel.setThumbnail(mediaDao.findOne(article.getThumbnailId()));
    }
    return resultArticle;
  }

  @Override
  public void updateArticle(Long id, Article article) {
    article.setId(id);
    article.setUpdated(new Date());
    ArticleModel articleModel = mapper.map(article, ArticleModel.class);
    if(article.getThumbnailId() != null) {
    	articleModel.setThumbnail(mediaDao.findOne(article.getThumbnailId()));
    }
    articleDao.save(articleModel);
  }

  @Override
  public void deleteArticle(Long id) {
    articleDao.delete(id);
  }

}
