package com.ronvel.farztev.admin.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.ronvel.farztev.admin.enums.MediaType;

@Entity
@Table(name = "media")
public class MediaModel extends BaseModel {
  @NotNull
  @Size(max = 255)
  private String name;
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(length = 45)
  private MediaType type;
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date created;
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  private Date updated;
  @NotNull
  @Type(type = "org.hibernate.type.NumericBooleanType")
  @Column(columnDefinition = "TINYINT(1)")
  private Boolean online;

  @NotNull
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "album_id")
  private AlbumModel album;

  @NotNull
  @Size(max = 255)
  private String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MediaType getType() {
    return type;
  }

  public void setType(MediaType type) {
    this.type = type;
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

  public AlbumModel getAlbum() {
    return album;
  }

  public void setAlbum(AlbumModel album) {
    this.album = album;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


}
