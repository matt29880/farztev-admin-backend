package com.ronvel.farztev.admin.component;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleDescriptionPhotoList extends ArticleDescription {
	private List<PhotoItem> photos;
	
	public ArticleDescriptionPhotoList() {
		super(ArticleDescriptionType.PHOTO_LIST);
	}
}