package com.ronvel.farztev.admin.component;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ArticleDescriptionType {
	PARAGRAPH, TITLE, PHOTO, ALBUM, UL, PHOTO_LIST, PANORAMA;
	
	@JsonCreator
	public ArticleDescriptionType fromString(String type) {
		return valueOf(type.toUpperCase());
	}
}
