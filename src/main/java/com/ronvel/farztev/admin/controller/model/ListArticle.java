package com.ronvel.farztev.admin.controller.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * SmallArticle
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-09T12:22:56.850+02:00")

public class ListArticle   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("photo")
  private String photo = null;

  public ListArticle id(Long id) {
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

  public ListArticle country(String country) {
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

  public ListArticle name(String name) {
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

  public ListArticle photo(String photo) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListArticle smallArticle = (ListArticle) o;
    return Objects.equals(this.id, smallArticle.id) &&
        Objects.equals(this.country, smallArticle.country) &&
        Objects.equals(this.name, smallArticle.name) &&
        Objects.equals(this.photo, smallArticle.photo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, country, name, photo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SmallArticle {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
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

