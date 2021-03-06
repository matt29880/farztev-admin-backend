package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListArticle;
import com.ronvel.farztev.admin.dao.ArticleDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.ArticleModel;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.dao.model.MediaModel;

public class ArticleServiceTest extends BaseServiceTest {

  @Autowired
  private ArticleService articleService;

  @Autowired
  private ArticleDao articleDao;
  
  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ModelMapper mapper;
  
  private MediaModel mediaModel;

  @Before
  public void before() {
    clear();
    assertEquals(0L, articleDao.count());
    countryDao.save(createDummyCountriesForTest());
    mediaModel = MediaServiceTest.createMediaWithoutAlbum();
  }

  @Test
  public void findArticle() {
    articleDao.save(createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());
    Optional<Article> optionalArticle = articleService.findArticleById(1L);
    assertTrue(optionalArticle.isPresent());
    testSwissArticle(optionalArticle.get(), mediaModel);
    assertEquals(new Date(1234567911L), optionalArticle.get().getCreated());
    assertEquals(new Date(1234567913L), optionalArticle.get().getUpdated());
  }

  @Test
  public void findArticle_notFound() {
    Optional<Article> optionalArticle = articleService.findArticleById(1L);
    assertFalse(optionalArticle.isPresent());
  }

  @Test
  public void listArticles() {
    articleDao.save(createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());
    List<ListArticle> articles = articleService.listArticles();
    testListArticles(articles);
  }

  @Test
  public void listArticles_empty() {
    List<ListArticle> articles = articleService.listArticles();
    assertNotNull(articles);
    assertTrue(articles.isEmpty());
  }

  @Test
  public void addArticle() {
    Date dateBeforeCreation = new Date();
    assertEquals(0L, articleDao.count());
    Article newArticle = mapper.map(createSwissArticle(mediaModel), Article.class);
    Article article = articleService.addArticle(newArticle);
    testSwissArticle(article, mediaModel);
    assertTrue(article.getCreated().after(dateBeforeCreation));
    assertTrue(article.getUpdated().after(dateBeforeCreation));
    assertEquals(1L, articleDao.count());
  }

  @Test
  public void updateArticle() {
    articleDao.save(createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());
    Article updateArticle = createUpdateSwissArticle();
    articleService.updateArticle(1L, updateArticle);
    Optional<Article> optionalArticle = articleService.findArticleById(1L);
    assertTrue(optionalArticle.isPresent());
    testUpdatedSwissArticle(optionalArticle.get());
  }

  @Test
  public void deleteArticle() {
    articleDao.save(createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());
    articleService.deleteArticle(1L);
    assertEquals(0L, articleDao.count());
  }

  public static void testSwissArticle(Article article, MediaModel mediaModel) {
    assertNotNull(article);
    assertEquals(1L, article.getId().longValue());
    assertEquals(1L, article.getCountryId().longValue());
    assertEquals("Switzerland", article.getCountryName());
    assertEquals("The zug description", article.getDescription());
    assertEquals("Zug, the place to be", article.getName());
    assertTrue(article.getOnline());
    assertEquals(mediaModel.getId(), article.getThumbnailId());
    assertEquals(mediaModel.getUrl(), article.getThumbnailUrl());
  }

  public static void testListArticles(List<ListArticle> articles) {
    assertNotNull(articles);
    assertEquals(1, articles.size());
    ListArticle article = articles.get(0);
    assertEquals(1L, article.getId().longValue());
    assertEquals(1L, article.getCountryId().longValue());
    assertEquals("Switzerland", article.getCountryName());
    assertEquals(new Date(1234567911L), article.getCreated());
    assertEquals("Zug, the place to be", article.getName());
    assertTrue(article.getOnline());
    assertEquals(new Date(1234567913L), article.getUpdated());
  }

  public static ArticleModel createSwissArticle(MediaModel mediaModel) {
    ArticleModel article = new ArticleModel();
    article.setId(1L);
    CountryModel country = new CountryModel();
    country.setId(1L);
    article.setCountry(country);
    article.setDescription("The zug description");
    article.setName("Zug, the place to be");
    article.setOnline(true);
    article.setCreated(new Date(1234567911L));
    article.setUpdated(new Date(1234567913L));
    article.setThumbnail(mediaModel);
    return article;
  }

  public static Article createUpdateSwissArticle() {
    Article article = new Article();
    article.setId(1L);
    article.setCountryId(2L);
    article.setCountryName("Spain");
    article.setCreated(new Date(12345679112L));
    article.setDescription("The zug description2");
    article.setName("Zug, the place to be2");
    article.setOnline(false);
    article.setUpdated(new Date(12345679132L));
    return article;
  }

  public static void testUpdatedSwissArticle(Article article) {
    assertNotNull(article);
    assertEquals(1L, article.getId().longValue());
    assertEquals(2L, article.getCountryId().longValue());
    assertEquals("Spain", article.getCountryName());
    assertEquals(new Date(12345679112L), article.getCreated());
    assertEquals("The zug description2", article.getDescription());
    assertEquals("Zug, the place to be2", article.getName());
    assertFalse(article.getOnline());
    assertTrue(article.getUpdated().after(new Date(12345679132L)));
  }

}
