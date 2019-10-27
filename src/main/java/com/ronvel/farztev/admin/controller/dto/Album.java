package com.ronvel.farztev.admin.controller.dto;

import java.util.Date;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * Album
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-04T17:58:43.630+01:00")

public class Album   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("countryId")
  private Long countryId = null;

  @JsonProperty("countryName")
  private String countryName = null;

  @JsonProperty("albumTypeId")
  private Long albumTypeId = null;

  @JsonProperty("albumTypeName")
  private String albumTypeName = null;

  @JsonProperty("created")
  private Date created = null;

  @JsonProperty("updated")
  private Date updated = null;

  @JsonProperty("online")
  private Boolean online = null;

  private Long thumbnailId = null;

  private String thumbnailUrl = null;

  public Album id(Long id) {
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

  public Album name(String name) {
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

  public Album description(String description) {
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

  public Album countryId(Long countryId) {
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

  public Album countryName(String countryName) {
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

  public Album albumTypeId(Long albumTypeId) {
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

  public Album albumTypeName(String albumTypeName) {
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

  public Album created(Date created) {
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

  public Album updated(Date updated) {
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

  public Album online(Boolean online) {
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


  	public Long getThumbnailId() {
  		return thumbnailId;
	}
	
	public void setThumbnailId(Long thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
	
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}
	
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

@Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Album album = (Album) o;
    return Objects.equals(this.id, album.id) &&
        Objects.equals(this.name, album.name) &&
        Objects.equals(this.description, album.description) &&
        Objects.equals(this.countryId, album.countryId) &&
        Objects.equals(this.countryName, album.countryName) &&
        Objects.equals(this.albumTypeId, album.albumTypeId) &&
        Objects.equals(this.albumTypeName, album.albumTypeName) &&
        Objects.equals(this.created, album.created) &&
        Objects.equals(this.updated, album.updated) &&
        Objects.equals(this.online, album.online);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, countryId, countryName, albumTypeId, albumTypeName, created, updated, online);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Album {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    countryId: ").append(toIndentedString(countryId)).append("\n");
    sb.append("    countryName: ").append(toIndentedString(countryName)).append("\n");
    sb.append("    albumTypeId: ").append(toIndentedString(albumTypeId)).append("\n");
    sb.append("    albumTypeName: ").append(toIndentedString(albumTypeName)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
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

