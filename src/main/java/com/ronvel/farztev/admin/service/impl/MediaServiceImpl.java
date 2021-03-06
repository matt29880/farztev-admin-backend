package com.ronvel.farztev.admin.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
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

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

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
    if(media.getAlbumId() != null) {
        mediaModel.setAlbum(albumDao.findOne(media.getAlbumId()));
    }
    mediaModel.setCreated(new Date());
    mediaModel.setUpdated(new Date());
    MediaModel resultMediaModel = mediaDao.save(mediaModel);
    return mapMedia(resultMediaModel);
  }

  @Override
  public void updateMedia(Long id, Media media) {
    media.setId(id);
    MediaModel mediaModel = mapper.map(media, MediaModel.class);
    if(media.getAlbumId() != null) {
        mediaModel.setAlbum(albumDao.findOne(media.getAlbumId()));
    }
    mediaModel.setUpdated(new Date());
    mediaDao.save(mediaModel);
  }

  private Media mapMedia(MediaModel resultMediaModel) {
    Media resultMedia = mapper.map(resultMediaModel, Media.class);
    if (resultMediaModel.getAlbum() != null) {
        resultMedia.setAlbumId(resultMediaModel.getAlbum().getId());
        resultMedia.setAlbumName(resultMediaModel.getAlbum().getName());
    }
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
  public List<FileDetailDto> listFiles(String folderPath) throws IOException {
    return cdnRepository.list(folderPath).stream()
        .map(f -> FileDetailDto.builder().name(f.getName()).path(f.getPath())
            .isDirectory(f.isDirectory()).build())
        .collect(Collectors.toList());
  }
  
  @Override
  public File scale(File file, String folderPath, int width, int height) throws IOException {
	  InputStream in = new FileInputStream(file);
	  String path = file.getAbsolutePath();
	  String generatedFilePath = path.replace("/images", "/images/thumbnail");
	  File generatedFile = new File(generatedFilePath);
	  FileOutputStream out = new FileOutputStream(generatedFile);
	    Thumbnails.of(in)
		    .size(width, height)
		    .crop(Positions.CENTER)
		    .toOutputStream(out);
	  return generatedFile;
  }

}
