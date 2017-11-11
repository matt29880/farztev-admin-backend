package com.ronvel.farztev.admin.controller.dto;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.LocalDate;
import javax.validation.constraints.*;
/**
 * ListCountry
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-11-11T19:02:46.574+01:00")

public class ListCountry   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("abbreviation")
  private String abbreviation = null;

  @JsonProperty("beginning")
  private LocalDate beginning = null;

  @JsonProperty("ending")
  private LocalDate ending = null;

  @JsonProperty("created")
  private LocalDate created = null;

  @JsonProperty("updated")
  private LocalDate updated = null;

  @JsonProperty("online")
  private Boolean online = null;

  @JsonProperty("continent")
  private String continent = null;

  public ListCountry id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(example = "10", value = "")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ListCountry name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Espagne", value = "")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ListCountry abbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }

   /**
   * Get abbreviation
   * @return abbreviation
  **/
  @ApiModelProperty(example = "ES", value = "")
  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public ListCountry beginning(LocalDate beginning) {
    this.beginning = beginning;
    return this;
  }

   /**
   * Get beginning
   * @return beginning
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public LocalDate getBeginning() {
    return beginning;
  }

  public void setBeginning(LocalDate beginning) {
    this.beginning = beginning;
  }

  public ListCountry ending(LocalDate ending) {
    this.ending = ending;
    return this;
  }

   /**
   * Get ending
   * @return ending
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public LocalDate getEnding() {
    return ending;
  }

  public void setEnding(LocalDate ending) {
    this.ending = ending;
  }

  public ListCountry created(LocalDate created) {
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

  public ListCountry updated(LocalDate updated) {
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

  public ListCountry online(Boolean online) {
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

  public ListCountry continent(String continent) {
    this.continent = continent;
    return this;
  }

   /**
   * Get continent
   * @return continent
  **/
  @ApiModelProperty(example = "ASIA", value = "")
  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ListCountry listCountry = (ListCountry) o;
    return Objects.equals(this.id, listCountry.id) &&
        Objects.equals(this.name, listCountry.name) &&
        Objects.equals(this.abbreviation, listCountry.abbreviation) &&
        Objects.equals(this.beginning, listCountry.beginning) &&
        Objects.equals(this.ending, listCountry.ending) &&
        Objects.equals(this.created, listCountry.created) &&
        Objects.equals(this.updated, listCountry.updated) &&
        Objects.equals(this.online, listCountry.online) &&
        Objects.equals(this.continent, listCountry.continent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, abbreviation, beginning, ending, created, updated, online, continent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ListCountry {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    abbreviation: ").append(toIndentedString(abbreviation)).append("\n");
    sb.append("    beginning: ").append(toIndentedString(beginning)).append("\n");
    sb.append("    ending: ").append(toIndentedString(ending)).append("\n");
    sb.append("    created: ").append(toIndentedString(created)).append("\n");
    sb.append("    updated: ").append(toIndentedString(updated)).append("\n");
    sb.append("    online: ").append(toIndentedString(online)).append("\n");
    sb.append("    continent: ").append(toIndentedString(continent)).append("\n");
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

