package com.ronvel.farztev.admin.controller.dto;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * Media
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-06T21:36:35.916+01:00")

public class Media   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("created")
  private Date created = null;

  @JsonProperty("updated")
  private Date updated = null;

  @JsonProperty("url")
  private String url = null;

  @JsonProperty("online")
  private Boolean online = null;

  @JsonProperty("albumId")
  private Long albumId = null;

  @JsonProperty("albumName")
  private String albumName = null;

  public Media id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "1", value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Media name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Patong", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Media type(String type) {
    this.type = type;
    return this;
  }

   /**
   * Get type
   * @return type
  **/
  @ApiModelProperty(example = "PHOTO", value = "")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Media created(Date created) {
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Media updated(Date updated) {
    this.updated = updated;
    return this;
  }

   /**
   * Get updated
   * @return updated
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Media url(String url) {
    this.url = url;
    return this;
  }

   /**
   * Get url
   * @return url
  **/
  @ApiModelProperty(example = "/1/1.jpg", value = "")
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Media online(Boolean online) {
    this.online = online;
    return this;
  }

   /**
   * Get online
   * @return online
  **/
  @ApiModelProperty(example = "true", value = "")
  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  public Media albumId(Long albumId) {
    this.albumId = albumId;
    return this;
  }

   /**
   * Get albumId
   * @return albumId
  **/
  @ApiModelProperty(example = "1", value = "")
  public Long getAlbumId() {
    return albumId;
  }

  public void setAlbumId(Long albumId) {
    this.albumId = albumId;
  }

  public Media albumName(String albumName) {
    this.albumName = albumName;
    return this;
  }

   /**
   * Get albumName
   * @return albumName
  **/
  @ApiModelProperty(example = "Ireland", value = "")
  public String getAlbumName() {
    return albumName;
  }

  public void setAlbumName(String albumName) {
    this.albumName = albumName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Media media = (Media) o;
    return Objects.equals(this.id, media.id) &&
        Objects.equals(this.name, media.name) &&
        Objects.equals(this.type, media.type) &&
        Objects.equals(this.created, media.created) &&
        Objects.equals(this.updated, media.updated) &&
        Objects.equals(this.url, media.url) &&
        Objects.equals(this.online, media.online) &&
        Objects.equals(this.albumId, media.albumId) &&
        Objects.equals(this.albumName, media.albumName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type, created, updated, url, online, albumId, albumName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Media {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    online: ").append(toIndentedString(online)).append("\n");
    sb.append("    albumId: ").append(toIndentedString(albumId)).append("\n");
    sb.append("    albumName: ").append(toIndentedString(albumName)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

