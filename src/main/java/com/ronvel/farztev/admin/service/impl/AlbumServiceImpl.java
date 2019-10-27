package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.MediaDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AlbumDao albumDao;
  
  @Autowired
  private AlbumTypeDao albumTypeDao;
  
  @Autowired
  private MediaDao mediaDao;

  @Override
  public Optional<Album> findAlbumById(Long id) {
    Optional<Album> optional;
    AlbumModel albumModel = albumDao.findOne(id);
    if (albumModel == null) {
      optional = Optional.empty();
    } else {
      Album album = mapAlbum(albumModel);
      if(albumModel.getThumbnail() != null) {
    	  album.setThumbnailId(albumModel.getThumbnail().getId());
    	  album.setThumbnailUrl(albumModel.getThumbnail().getUrl());
      }
      optional = Optional.of(album);
    }
    return optional;
  }

  @Override
  public List<ListAlbum> listAlbums() {
    List<ListAlbum> listAlbums = new ArrayList<>();
    Iterable<AlbumModel> albums = albumDao.findAll();
    albums.forEach(album -> listAlbums.add(mapListAlbum(album)));
    return listAlbums;
  }

  @Override
  public Album addAlbum(Album album) {
    AlbumModel albumModel = mapper.map(album, AlbumModel.class);
    albumModel.setAlbumType(albumTypeDao.findOne(albumModel.getAlbumType().getId()));
    albumModel.setCreated(new Date());
    albumModel.setUpdated(new Date());
    AlbumModel resultAlbumModel = albumDao.save(albumModel);
    return mapAlbum(resultAlbumModel);
  }

  @Override
  public void updateAlbum(Long id, Album album) {
    AlbumModel albumModel = new AlbumModel();
    albumModel.setId(id);
    albumModel.setName(album.getName());
    albumModel.setDescription(album.getDescription());
    albumModel.setCreated(album.getCreated());
    albumModel.setOnline(album.getOnline());
    albumModel.setUpdated(new Date());
    albumModel.setAlbumType(albumTypeDao.findOne(album.getAlbumTypeId()));
    albumModel.setUpdated(new Date());
    if(album.getThumbnailId() != null) {
    	albumModel.setThumbnail(mediaDao.findOne(album.getThumbnailId()));
    }    
    albumDao.save(albumModel);
  }
  
  private Album mapAlbum(AlbumModel resultAlbumModel) {
    Album album = new Album();
    album.setAlbumTypeId(resultAlbumModel.getAlbumType().getId());
    album.setAlbumTypeName(resultAlbumModel.getAlbumType().getName());
    album.setCreated(resultAlbumModel.getCreated());
    album.setUpdated(resultAlbumModel.getUpdated());
    album.setId(resultAlbumModel.getId());
    album.setDescription(resultAlbumModel.getDescription());
    album.setName(resultAlbumModel.getName());
    album.setOnline(resultAlbumModel.getOnline());
    if (resultAlbumModel.getThumbnail() != null) {
        album.setThumbnailId(resultAlbumModel.getThumbnail().getId());
        album.setThumbnailUrl(resultAlbumModel.getThumbnail().getUrl());
    }
    album.setCountryId(resultAlbumModel.getAlbumType().getCountry().getId());
    album.setCountryName(resultAlbumModel.getAlbumType().getCountry().getName());
    return album;
  }
  
  private ListAlbum mapListAlbum(AlbumModel resultAlbumModel) {
    ListAlbum listAlbum = new ListAlbum();
    listAlbum.setAlbumTypeId(resultAlbumModel.getAlbumType().getId());
    listAlbum.setAlbumTypeName(resultAlbumModel.getAlbumType().getName());
    listAlbum.setCreated(resultAlbumModel.getCreated());
    listAlbum.setUpdated(resultAlbumModel.getUpdated());
    listAlbum.setId(resultAlbumModel.getId());
    listAlbum.setDescription(resultAlbumModel.getDescription());
    listAlbum.setName(resultAlbumModel.getName());
    listAlbum.setOnline(resultAlbumModel.getOnline());
    if (resultAlbumModel.getThumbnail() != null) {
        listAlbum.setThumbnail(resultAlbumModel.getThumbnail().getId());
    }
    listAlbum.setCountryId(resultAlbumModel.getAlbumType().getCountry().getId());
    listAlbum.setCountryName(resultAlbumModel.getAlbumType().getCountry().getName());
    return listAlbum;
  }

  @Override
  public void deleteAlbum(Long id) {
    albumDao.delete(id);
  }

}
