package com.ronvel.farztev.admin.controller.model;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * Country
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-11T22:47:11.093+02:00")

public class Country   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("abbreviation")
  private String abbreviation = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("trip")
  private String trip = null;

  @JsonProperty("why")
  private String why = null;

  @JsonProperty("favorite")
  private String favorite = null;

  @JsonProperty("conclusion")
  private String conclusion = null;

  @JsonProperty("beginning")
  private Date beginning = null;

  @JsonProperty("ending")
  private Date ending = null;

  @JsonProperty("created")
  private Date created = null;

  @JsonProperty("updated")
  private Date updated = null;

  @JsonProperty("online")
  private Boolean online = null;

  @JsonProperty("continent")
  private String continent = null;

  public Country id(Long id) {
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

  public Country name(String name) {
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

  public Country abbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
    return this;
  }

   /**
   * Get abbreviation
   * @return abbreviation
  **/
  @ApiModelProperty(example = "es", value = "")
  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public Country description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Country trip(String trip) {
    this.trip = trip;
    return this;
  }

   /**
   * Get trip
   * @return trip
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public String getTrip() {
    return trip;
  }

  public void setTrip(String trip) {
    this.trip = trip;
  }

  public Country why(String why) {
    this.why = why;
    return this;
  }

   /**
   * Get why
   * @return why
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public String getWhy() {
    return why;
  }

  public void setWhy(String why) {
    this.why = why;
  }

  public Country favorite(String favorite) {
    this.favorite = favorite;
    return this;
  }

   /**
   * Get favorite
   * @return favorite
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public String getFavorite() {
    return favorite;
  }

  public void setFavorite(String favorite) {
    this.favorite = favorite;
  }

  public Country conclusion(String conclusion) {
    this.conclusion = conclusion;
    return this;
  }

   /**
   * Get conclusion
   * @return conclusion
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public String getConclusion() {
    return conclusion;
  }

  public void setConclusion(String conclusion) {
    this.conclusion = conclusion;
  }

  public Country beginning(Date beginning) {
    this.beginning = beginning;
    return this;
  }

   /**
   * Get beginning
   * @return beginning
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public Date getBeginning() {
    return beginning;
  }

  public void setBeginning(Date beginning) {
    this.beginning = beginning;
  }

  public Country ending(Date ending) {
    this.ending = ending;
    return this;
  }

   /**
   * Get ending
   * @return ending
  **/
  @ApiModelProperty(example = "Blablabla", value = "")
  public Date getEnding() {
    return ending;
  }

  public void setEnding(Date ending) {
    this.ending = ending;
  }

  public Country created(Date created) {
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

  public Country updated(Date updated) {
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

  public Country online(Boolean online) {
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

  public Country continent(String continent) {
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
    Country country = (Country) o;
    return Objects.equals(this.id, country.id) &&
        Objects.equals(this.name, country.name) &&
        Objects.equals(this.abbreviation, country.abbreviation) &&
        Objects.equals(this.description, country.description) &&
        Objects.equals(this.trip, country.trip) &&
        Objects.equals(this.why, country.why) &&
        Objects.equals(this.favorite, country.favorite) &&
        Objects.equals(this.conclusion, country.conclusion) &&
        Objects.equals(this.beginning, country.beginning) &&
        Objects.equals(this.ending, country.ending) &&
        Objects.equals(this.created, country.created) &&
        Objects.equals(this.updated, country.updated) &&
        Objects.equals(this.online, country.online) &&
        Objects.equals(this.continent, country.continent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, abbreviation, description, trip, why, favorite, conclusion, beginning, ending, created, updated, online, continent);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Country {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    abbreviation: ").append(toIndentedString(abbreviation)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    trip: ").append(toIndentedString(trip)).append("\n");
    sb.append("    why: ").append(toIndentedString(why)).append("\n");
    sb.append("    favorite: ").append(toIndentedString(favorite)).append("\n");
    sb.append("    conclusion: ").append(toIndentedString(conclusion)).append("\n");
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

