package com.ronvel.farztev.admin.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDescriptionParagraph extends ArticleDescription {
	private String content;
	
	public ArticleDescriptionParagraph() {
		super(ArticleDescriptionType.PARAGRAPH);
	}
}
