package com.ronvel.farztev.admin.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDescriptionPanorama extends ArticleDescription {
	private Long id;
	private String url;
	
	public ArticleDescriptionPanorama() {
		super(ArticleDescriptionType.PANORAMA);
	}
}