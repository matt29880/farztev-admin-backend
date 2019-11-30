package com.ronvel.farztev.admin.component;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDescriptionAlbum extends ArticleDescription {
	private Long albumId;
	
	public ArticleDescriptionAlbum() {
		super(ArticleDescriptionType.ALBUM);
	}
}
