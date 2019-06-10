package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.cdn.CdnRepository;
import com.ronvel.farztev.admin.controller.dto.FileDetailDto;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.MediaDao;
import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.enums.MediaType;
import com.ronvel.farztev.admin.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AlbumDao albumDao;

  @Autowired
  private MediaDao mediaDao;

  @Autowired
  private CdnRepository cdnRepository;

  @Override
  public Optional<Media> findMediaById(Long id) {
    Optional<Media> optional;
    MediaModel mediaModel = mediaDao.findOne(id);
    if (mediaModel == null) {
      optional = Optional.empty();
    } else {
      Media media = mapMedia(mediaModel);
      optional = Optional.of(media);
    }
    return optional;
  }

  @Override
  public List<ListMedia> listMedias() {
    List<ListMedia> listMedias = new ArrayList<>();
    Iterable<MediaModel> medias = mediaDao.findAll();
    medias.forEach(media -> listMedias.add(mapListMedia(media)));
    return listMedias;
  }

  @Override
  public List<ListMedia> listAlbumMedias(Long albumId, MediaType type) {
    List<MediaModel> medias = mediaDao.findByAlbumIdAndType(albumId, type);
    return medias.stream().map(media -> mapListMedia(media)).collect(Collectors.toList());
  }

  @Override
  public Media addMedia(Media media) {
    MediaModel mediaModel = mapper.map(media, MediaModel.class);
    mediaModel.setAlbum(albumDao.findOne(mediaModel.getAlbum().getId()));
    MediaModel resultMediaModel = mediaDao.save(mediaModel);
    return mapMedia(resultMediaModel);
  }

  @Override
  public void updateMedia(Long id, Media media) {
    media.setId(id);
    MediaModel mediaModel = mapper.map(media, MediaModel.class);
    mediaModel.setAlbum(albumDao.findOne(media.getAlbumId()));
    mediaDao.save(mediaModel);
  }

  private Media mapMedia(MediaModel resultMediaModel) {
    Media resultMedia = mapper.map(resultMediaModel, Media.class);
    resultMedia.setAlbumId(resultMediaModel.getAlbum().getId());
    resultMedia.setAlbumName(resultMediaModel.getAlbum().getName());
    return resultMedia;
  }

  private ListMedia mapListMedia(MediaModel resultMediaModel) {
    ListMedia listMedia = mapper.map(resultMediaModel, ListMedia.class);
    return listMedia;
  }

  @Override
  public void deleteMedia(Long id) {
    mediaDao.delete(id);
  }

  @Override
  public List<FileDetailDto> listFiles(String folderPath) {
    return cdnRepository.list(folderPath).stream()
        .map(f -> FileDetailDto.builder().name(f.getName()).path(f.getPath())
            .isDirectory(f.isDirectory()).build())
        .collect(Collectors.toList());
  }

}
