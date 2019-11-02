package com.ronvel.farztev.admin.component;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;

@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type")
@JsonSubTypes({ 
  @Type(value = ArticleDescriptionParagraph.class, name = "paragraph"), 
  @Type(value = ArticleDescriptionTitle.class, name = "title") , 
  @Type(value = ArticleDescriptionPhoto.class, name = "photo") , 
  @Type(value = ArticleDescriptionAlbum.class, name = "album") 
})
@Data
public abstract class ArticleDescription {
	private String type;
}
