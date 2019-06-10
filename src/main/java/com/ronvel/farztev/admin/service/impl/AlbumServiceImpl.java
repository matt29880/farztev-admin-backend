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

  @Override
  public Optional<Album> findAlbumById(Long id) {
    Optional<Album> optional;
    AlbumModel albumModel = albumDao.findOne(id);
    if (albumModel == null) {
      optional = Optional.empty();
    } else {
      Album album = mapAlbum(albumModel);
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
    album.setId(id);
    AlbumModel albumModel = mapper.map(album, AlbumModel.class);
    albumModel.setAlbumType(albumTypeDao.findOne(album.getAlbumTypeId()));
    albumModel.setUpdated(new Date());
    albumDao.save(albumModel);
  }
  
  private Album mapAlbum(AlbumModel resultAlbumModel) {
    Album resultAlbum = mapper.map(resultAlbumModel, Album.class);
    resultAlbum.setCountryId(resultAlbumModel.getAlbumType().getCountry().getId());
    resultAlbum.setCountryName(resultAlbumModel.getAlbumType().getCountry().getName());
    return resultAlbum;
  }
  
  private ListAlbum mapListAlbum(AlbumModel resultAlbumModel) {
    ListAlbum listAlbum = mapper.map(resultAlbumModel, ListAlbum.class);
    listAlbum.setCountryId(resultAlbumModel.getAlbumType().getCountry().getId());
    listAlbum.setCountryName(resultAlbumModel.getAlbumType().getCountry().getName());
    return listAlbum;
  }

  @Override
  public void deleteAlbum(Long id) {
    albumDao.delete(id);
  }

}
