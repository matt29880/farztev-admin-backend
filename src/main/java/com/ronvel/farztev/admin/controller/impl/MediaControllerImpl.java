package com.ronvel.farztev.admin.controller.impl;

import com.ronvel.farztev.admin.controller.dto.FileDetailDto;
import java.io.File;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.MediaController;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.service.MediaService;
import io.swagger.annotations.ApiParam;

@Controller
public class MediaControllerImpl implements MediaController {

  @Autowired
  MediaService mediaService;

  public ResponseEntity<List<ListMedia>> apiAlbumAlbumIdMediaGet(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId) {
    List<ListMedia> medias = mediaService.listMedias();
    return new ResponseEntity<List<ListMedia>>(medias, HttpStatus.OK);
  }

  public ResponseEntity<Void> apiAlbumAlbumIdMediaMediaIdDelete(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    mediaService.deleteMedia(mediaId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdGet(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    Optional<Media> mediaOptional = mediaService.findMediaById(mediaId);
    return mediaOptional.map(media -> new ResponseEntity<Media>(media, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<Media>(HttpStatus.NOT_FOUND));
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdPut(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId,
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    mediaService.updateMedia(mediaId, media);
    return new ResponseEntity<Media>(media, HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaPost(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    Media mediaResult = mediaService.addMedia(media);
    return new ResponseEntity<Media>(mediaResult, HttpStatus.OK);
  }


  public ResponseEntity<List<FileDetailDto>> listRootFiles() {
    return new ResponseEntity<List<FileDetailDto>>(mediaService.listFiles("/"), HttpStatus.OK);
  }

  public ResponseEntity<List<FileDetailDto>> listFiles(@ApiParam(value = "Path",required=true ) @PathVariable("folderPath") String folderPath) {
    return new ResponseEntity<List<FileDetailDto>>(mediaService.listFiles(File.separator + folderPath), HttpStatus.OK);
  }
}
