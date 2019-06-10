package com.ronvel.farztev.admin.dao.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="album_type")
public class AlbumTypeModel extends BaseModel {
  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "country")
  private CountryModel country;
  
  @NotNull
  @Size(max = 255)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public CountryModel getCountry() {
    return country;
  }

  public void setCountry(CountryModel country) {
    this.country = country;
  }

}
