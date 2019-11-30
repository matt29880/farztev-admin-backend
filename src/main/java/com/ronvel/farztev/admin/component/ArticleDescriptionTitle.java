package com.ronvel.farztev.admin.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDescriptionTitle extends ArticleDescription {
	private String content;
	
	public ArticleDescriptionTitle() {
		super(ArticleDescriptionType.TITLE);
	}
}
