package com.ronvel.farztev.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListArticle;
import com.ronvel.farztev.admin.dao.model.ArticleModel;
import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.service.ArticleServiceTest;
import com.ronvel.farztev.admin.service.BaseServiceTest;
import com.ronvel.farztev.admin.service.MediaServiceTest;

public class ArticleControllerTest extends BaseControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ModelMapper mapper;
  
  private MediaModel mediaModel;

  @Before
  public void before() {
    clear();
    assertEquals(0L, articleDao.count());
    countryDao.save(BaseServiceTest.createDummyCountriesForTest());
    mediaModel = MediaServiceTest.createMediaWithoutAlbum();
  }

  @Test
  public void articlesGet() {
    articleDao.save(ArticleServiceTest.createSwissArticle(mediaModel));

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListArticle>> articlesResponse = this.restTemplate.exchange("/api/article",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListArticle>>() {});
    assertTrue(articlesResponse.getStatusCode().is2xxSuccessful());

    ArticleServiceTest.testListArticles(articlesResponse.getBody());
  }

  @Test
  public void articlesGet_empty() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListArticle>> articlesResponse = this.restTemplate.exchange("/api/article",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListArticle>>() {});
    assertTrue(articlesResponse.getStatusCode().is2xxSuccessful());
    assertTrue(articlesResponse.getBody().isEmpty());
  }

  @Test
  public void articlesArticleIdGet() {
    ArticleModel newArticle = ArticleServiceTest.createSwissArticle(mediaModel);
    articleDao.save(newArticle);
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Article> articleResponse = this.restTemplate.exchange("/api/article/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Article>() {});
    assertTrue(articleResponse.getStatusCode().is2xxSuccessful());
    ArticleServiceTest.testSwissArticle(articleResponse.getBody(), mediaModel);
  }

  @Test
  public void articlesArticleIdGet_notExisting() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Article> articleResponse = this.restTemplate.exchange("/api/article/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Article>() {});
    assertTrue(articleResponse.getStatusCode().is4xxClientError());
  }

  @Test
  public void articlesPost() {
    Article newArticle = mapper.map(ArticleServiceTest.createSwissArticle(mediaModel), Article.class);

    HttpEntity<Article> request = new HttpEntity<Article>(newArticle);

    ResponseEntity<Article> articleResponse = this.restTemplate.exchange("/api/article",
        HttpMethod.POST, request, new ParameterizedTypeReference<Article>() {});
    assertTrue(articleResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, articleDao.count());
    ArticleServiceTest.testSwissArticle(articleResponse.getBody(), mediaModel);
  }

  @Test
  public void articlesArticleIdPut() {
    articleDao.save(ArticleServiceTest.createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());
    Article updateArticle =
        mapper.map(ArticleServiceTest.createUpdateSwissArticle(), Article.class);

    HttpEntity<Article> request = new HttpEntity<Article>(updateArticle);

    ResponseEntity<Void> updateResponse = this.restTemplate.exchange("/api/article/1",
        HttpMethod.PUT, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, articleDao.count());

    ResponseEntity<Article> articleResponse = this.restTemplate.exchange("/api/article/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Article>() {});
    assertTrue(articleResponse.getStatusCode().is2xxSuccessful());
    ArticleServiceTest.testUpdatedSwissArticle(articleResponse.getBody());
  }

  @Test
  public void articlesArticleIdDelete() {
    articleDao.save(ArticleServiceTest.createSwissArticle(mediaModel));
    assertEquals(1L, articleDao.count());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Void> deleteResponse = this.restTemplate.exchange("/api/article/1",
        HttpMethod.DELETE, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(deleteResponse.getStatusCode().is2xxSuccessful());
    assertEquals(0L, articleDao.count());
  }

}
