package com.ronvel.farztev.admin.dao.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;

@Entity
@Table(name="country")
public class CountryModel extends BaseModel {
  @NotNull
  @Size(max = 30)
  private String name;
  //@NotNull
  @Size(max = 20)
  @Column(name = "abbreviation", nullable = true, length = 65535, columnDefinition="TEXT")
  private String abbreviation;
  @Column(name = "description", nullable = true, length = 65535, columnDefinition="TEXT")
  private String description;
  @Column(name = "trip", nullable = true, length = 65535, columnDefinition="TEXT")
  private String trip;
  @Column(name = "why", nullable = true, length = 65535, columnDefinition="TEXT")
  private String why;
  @Column(name = "favorite", nullable = true, length = 65535, columnDefinition="TEXT")
  private String favorite;
  @Column(name = "conclusion", nullable = true, length = 65535, columnDefinition="TEXT")
  private String conclusion;
  private Date beginning;
  private Date ending;
  //@NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  //@NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;
  //@NotNull
  @Type(type = "org.hibernate.type.NumericBooleanType")
  @Column(columnDefinition = "TINYINT(1)")
  private Boolean online;
  @Size(max = 30)
  private String continent;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getTrip() {
    return trip;
  }

  public void setTrip(String trip) {
    this.trip = trip;
  }

  public String getWhy() {
    return why;
  }

  public void setWhy(String why) {
    this.why = why;
  }

  public String getFavorite() {
    return favorite;
  }

  public void setFavorite(String favorite) {
    this.favorite = favorite;
  }

  public String getConclusion() {
    return conclusion;
  }

  public void setConclusion(String conclusion) {
    this.conclusion = conclusion;
  }

  public Date getBeginning() {
    return beginning;
  }

  public void setBeginning(Date beginning) {
    this.beginning = beginning;
  }

  public Date getEnding() {
    return ending;
  }

  public void setEnding(Date ending) {
    this.ending = ending;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getUpdated() {
    return updated;
  }

  public void setUpdated(Date updated) {
    this.updated = updated;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline(Boolean online) {
    this.online = online;
  }

  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

}
