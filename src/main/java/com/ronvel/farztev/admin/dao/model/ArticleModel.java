package com.ronvel.farztev.admin.dao.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ArticleModel extends BaseModel {
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country_id")
  private CountryModel country;

  @NotNull
  @Size(max = 30)
  private String name;
  @NotNull
  @Size(max = 255)
  private String photo;
  @NotNull
  private String description;
  @NotNull
  private Date created;
  @NotNull
  private Date updated;
  @NotNull
  private Boolean online;

  public CountryModel getCountry() {
    return country;
  }

  public void setCountry(CountryModel country) {
    this.country = country;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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



}
