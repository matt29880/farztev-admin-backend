package com.ronvel.farztev.admin.controller.impl;

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



    public ResponseEntity<Article> articlesArticleIdGet(@ApiParam(value = "ID of the article to return",required=true ) @PathVariable("articleId") Long articleId) {
        // do some magic!
        return new ResponseEntity<Article>(HttpStatus.OK);
    }

    public ResponseEntity<List<SmallArticle>> articlesGet() {
        // do some magic!
        return new ResponseEntity<List<SmallArticle>>(HttpStatus.OK);
    }

}
