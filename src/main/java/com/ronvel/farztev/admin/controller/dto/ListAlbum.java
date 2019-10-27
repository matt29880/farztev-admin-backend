package com.ronvel.farztev.admin.controller.dto;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * ListAlbum
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-04T17:58:43.630+01:00")

public class ListAlbum   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("created")
  private Date created = null;

  @JsonProperty("updated")
  private Date updated = null;

  @JsonProperty("thumbnail")
  private Long thumbnail = null;

  @JsonProperty("countryId")
  private Long countryId = null;

  @JsonProperty("countryName")
  private String countryName = null;

  @JsonProperty("albumTypeId")
  private Long albumTypeId = null;

  @JsonProperty("albumTypeName")
  private String albumTypeName = null;

  @JsonProperty("online")
  private Boolean online = null;

  public ListAlbum id(Long id) {
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

  public ListAlbum name(String name) {
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

  public ListAlbum description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Patong is the most famous place of Phuket...", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ListAlbum created(Date created) {
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

  public ListAlbum updated(Date updated) {
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

  public ListAlbum thumbnail(Long thumbnail) {
    this.thumbnail = thumbnail;
    return this;
  }

   /**
   * Get thumbnail
   * @return thumbnail
  **/
  @ApiModelProperty(example = "68", value = "")
  public Long getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(Long thumbnail) {
    this.thumbnail = thumbnail;
  }

  public ListAlbum countryId(Long countryId) {
    this.countryId = countryId;
    return this;
  }

   /**
   * Get countryId
   * @return countryId
  **/
  @ApiModelProperty(example = "1", value = "")
  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public ListAlbum countryName(String countryName) {
    this.countryName = countryName;
    return this;
  }

   /**
   * Get countryName
   * @return countryName
  **/
  @ApiModelProperty(example = "Ireland", value = "")
  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public ListAlbum albumTypeId(Long albumTypeId) {
    this.albumTypeId = albumTypeId;
    return this;
  }

   /**
   * Get albumTypeId
   * @return albumTypeId
  **/
  @ApiModelProperty(example = "1", value = "")
  public Long getAlbumTypeId() {
    return albumTypeId;
  }

  public void setAlbumTypeId(Long albumTypeId) {
    this.albumTypeId = albumTypeId;
  }

  public ListAlbum albumTypeName(String albumTypeName) {
    this.albumTypeName = albumTypeName;
    return this;
  }

   /**
   * Get albumTypeName
   * @return albumTypeName
  **/
  @ApiModelProperty(example = "Zug canton", value = "")
  public String getAlbumTypeName() {
    return albumTypeName;
  }

  public void setAlbumTypeName(String albumTypeName) {
    this.albumTypeName = albumTypeName;
  }

  public ListAlbum online(Boolean online) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListAlbum listAlbum = (ListAlbum) o;
    return Objects.equals(this.id, listAlbum.id) &&
        Objects.equals(this.name, listAlbum.name) &&
        Objects.equals(this.description, listAlbum.description) &&
        Objects.equals(this.created, listAlbum.created) &&
        Objects.equals(this.updated, listAlbum.updated) &&
        Objects.equals(this.thumbnail, listAlbum.thumbnail) &&
        Objects.equals(this.countryId, listAlbum.countryId) &&
        Objects.equals(this.countryName, listAlbum.countryName) &&
        Objects.equals(this.albumTypeId, listAlbum.albumTypeId) &&
        Objects.equals(this.albumTypeName, listAlbum.albumTypeName) &&
        Objects.equals(this.online, listAlbum.online);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, created, updated, thumbnail, countryId, countryName, albumTypeId, albumTypeName, online);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListAlbum {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    thumbnail: ").append(toIndentedString(thumbnail)).append("\n");
    sb.append("    countryId: ").append(toIndentedString(countryId)).append("\n");
    sb.append("    countryName: ").append(toIndentedString(countryName)).append("\n");
    sb.append("    albumTypeId: ").append(toIndentedString(albumTypeId)).append("\n");
    sb.append("    albumTypeName: ").append(toIndentedString(albumTypeName)).append("\n");
    sb.append("    online: ").append(toIndentedString(online)).append("\n");
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

