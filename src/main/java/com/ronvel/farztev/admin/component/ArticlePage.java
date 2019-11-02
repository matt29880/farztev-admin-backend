package com.ronvel.farztev.admin.component;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ArticlePage {
	private Long id = null;
	private Long countryId = null;
	private String countryName = null;
	private String name = null;
	private List<ArticleDescription> descriptions = null;
	private Date created = null;
	private Date updated = null;
	private String thumbnailUrl = null;
}
