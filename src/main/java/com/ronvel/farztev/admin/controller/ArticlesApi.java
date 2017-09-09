package com.ronvel.farztev.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ronvel.farztev.admin.controller.model.Article;
import com.ronvel.farztev.admin.controller.model.ListArticle;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-07T23:10:06.754+02:00")

@Api(value = "articles", description = "the articles API")
public interface ArticlesApi {

    @ApiOperation(value = "", notes = "", response = Article.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Article.class),
        @ApiResponse(code = 404, message = "Article not found", response = Article.class) })
    @RequestMapping(value = "/articles/{articleId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Article> articlesArticleIdGet(@ApiParam(value = "ID of the article to return",required=true ) @PathVariable("articleId") Long articleId);


    @ApiOperation(value = "", notes = "", response = ListArticle.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ListArticle.class),
        @ApiResponse(code = 400, message = "Invalid status value", response = ListArticle.class) })
    @RequestMapping(value = "/articles",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ListArticle>> articlesGet();

}
