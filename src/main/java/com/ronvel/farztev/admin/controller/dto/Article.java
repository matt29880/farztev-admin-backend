package com.ronvel.farztev.admin.controller.dto;

import java.util.Objects;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Article
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-11T19:02:46.574+01:00")

public class Article   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("photo")
  private String photo = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("created")
  private LocalDate created = null;

  @JsonProperty("updated")
  private LocalDate updated = null;

  @JsonProperty("online")
  private Boolean online = null;

  public Article id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Article country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
  @ApiModelProperty(example = "fr", value = "")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Article name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Visit in Dublin", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Article photo(String photo) {
    this.photo = photo;
    return this;
  }

   /**
   * Get photo
   * @return photo
  **/
  @ApiModelProperty(example = "dublin.jpg", value = "")
  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Article description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Dublin is the capital of Irland...", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Article created(LocalDate created) {
    this.created = created;
    return this;
  }

   /**
   * Get created
   * @return created
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public LocalDate getCreated() {
    return created;
  }

  public void setCreated(LocalDate created) {
    this.created = created;
  }

  public Article updated(LocalDate updated) {
    this.updated = updated;
    return this;
  }

   /**
   * Get updated
   * @return updated
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public LocalDate getUpdated() {
    return updated;
  }

  public void setUpdated(LocalDate updated) {
    this.updated = updated;
  }

  public Article online(Boolean online) {
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
    Article article = (Article) o;
    return Objects.equals(this.id, article.id) &&
        Objects.equals(this.country, article.country) &&
        Objects.equals(this.name, article.name) &&
        Objects.equals(this.photo, article.photo) &&
        Objects.equals(this.description, article.description) &&
        Objects.equals(this.created, article.created) &&
        Objects.equals(this.updated, article.updated) &&
        Objects.equals(this.online, article.online);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, country, name, photo, description, created, updated, online);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Article {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
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

