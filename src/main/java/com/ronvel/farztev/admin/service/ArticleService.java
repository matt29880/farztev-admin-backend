package com.ronvel.farztev.admin.service;

import java.util.List;
import java.util.Optional;
import com.ronvel.farztev.admin.controller.dto.Article;
import com.ronvel.farztev.admin.controller.dto.ListArticle;

/**
 * Service for selecting articles.
 * 
 * @author mronvel
 *
 */
public interface ArticleService {
	/**
	 * Find an article by id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Article> findArticleById(Long id);

	/**
	 * List all the articles.
	 * 
	 * @return
	 */
	List<ListArticle> listArticles();

	/**
	 * Add a new article.
	 * @param article
	 * @return
	 */
	Article addArticle(Article article);

	/**
	 * Update a article.
	 * @param id
	 * @param article
	 */
	void updateArticle(Long id, Article article);
	
	/**Delete a article.
	 * 
	 * @param id
	 */
	void deleteArticle(Long id);
}
