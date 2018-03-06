package com.ronvel.farztev.admin.controller.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
/**
 * AlbumType
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-04T17:13:19.508+01:00")

public class AlbumType   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("countryId")
  private Long countryId = null;

  @JsonProperty("countryName")
  private String countryName = null;

  public AlbumType id(Long id) {
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

  public AlbumType name(String name) {
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

  public AlbumType countryId(Long countryId) {
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

  public AlbumType countryName(String countryName) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlbumType albumType = (AlbumType) o;
    return Objects.equals(this.id, albumType.id) &&
        Objects.equals(this.name, albumType.name) &&
        Objects.equals(this.countryId, albumType.countryId) &&
        Objects.equals(this.countryName, albumType.countryName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, countryId, countryName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlbumType {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    countryId: ").append(toIndentedString(countryId)).append("\n");
    sb.append("    countryName: ").append(toIndentedString(countryName)).append("\n");
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

