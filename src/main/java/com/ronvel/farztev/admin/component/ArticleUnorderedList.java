package com.ronvel.farztev.admin.component;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleUnorderedList extends ArticleDescription {
	private List<ArticleListItem> items;
	
	public ArticleUnorderedList() {
		super(ArticleDescriptionType.UL);
	}
}
