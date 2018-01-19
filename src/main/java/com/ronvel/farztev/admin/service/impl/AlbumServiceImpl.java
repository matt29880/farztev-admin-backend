package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.service.AlbumService;

@Service
public class AlbumServiceImpl implements AlbumService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AlbumDao albumDao;

  @Override
  public Optional<Album> findAlbumById(Long id) {
    Optional<Album> optional;
    AlbumModel albumModel = albumDao.findOne(id);
    if (albumModel == null) {
      optional = Optional.empty();
    } else {
      Album album = mapper.map(albumModel, Album.class);
      optional = Optional.of(album);
    }
    return optional;
  }

  @Override
  public List<ListAlbum> listAlbums() {
    List<ListAlbum> listAlbums = new ArrayList<>();
    Iterable<AlbumModel> albums = albumDao.findAll();
    albums.forEach(album -> listAlbums.add(mapper.map(album, ListAlbum.class)));
    return listAlbums;
  }

  @Override
  public Album addAlbum(Album album) {
    AlbumModel albumModel = mapper.map(album, AlbumModel.class);
    AlbumModel resultAlbumModel = albumDao.save(albumModel);
    Album resultAlbum = mapper.map(resultAlbumModel, Album.class);
    return resultAlbum;
  }

  @Override
  public void updateAlbum(Long id, Album album) {
    album.setId(id);
    AlbumModel albumModel = mapper.map(album, AlbumModel.class);
    albumDao.save(albumModel);
  }

  @Override
  public void deleteAlbum(Long id) {
    albumDao.delete(id);
  }

}
