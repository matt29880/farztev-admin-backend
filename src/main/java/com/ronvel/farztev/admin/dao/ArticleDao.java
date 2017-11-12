package com.ronvel.farztev.admin.dao;

import org.springframework.data.repository.CrudRepository;
import com.ronvel.farztev.admin.dao.model.ArticleModel;

public interface ArticleDao extends CrudRepository<ArticleModel, Long> {
	
}
