package com.ronvel.farztev.admin.controller.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.ArticleController;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListArticle;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2017-09-07T23:10:06.754+02:00")

@Controller
public class ArticleControllerImpl implements ArticleController {

  public ResponseEntity<Void> apiArticleArticleIdDelete(
      @ApiParam(value = "Article ID", required = true) @PathVariable("articleId") Long articleId) {
    // do some magic!
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Article> apiArticleArticleIdGet(
      @ApiParam(value = "Article ID", required = true) @PathVariable("articleId") Long articleId) {
    // do some magic!
    return new ResponseEntity<Article>(HttpStatus.OK);
  }

  public ResponseEntity<Article> apiArticleArticleIdPut(
      @ApiParam(value = "Article ID", required = true) @PathVariable("articleId") Long articleId,
      @ApiParam(value = "Article data.", required = true) @RequestBody Article article) {
    // do some magic!
    return new ResponseEntity<Article>(HttpStatus.OK);
  }

  public ResponseEntity<List<ListArticle>> apiArticleGet() {
    // do some magic!
    return new ResponseEntity<List<ListArticle>>(HttpStatus.OK);
  }

  public ResponseEntity<Article> apiArticlePost(
      @ApiParam(value = "Article data.", required = true) @RequestBody Article article) {
    // do some magic!
    return new ResponseEntity<Article>(HttpStatus.OK);
  }

}
