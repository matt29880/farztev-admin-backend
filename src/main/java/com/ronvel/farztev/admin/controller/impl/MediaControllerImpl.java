package com.ronvel.farztev.admin.controller.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ronvel.farztev.admin.controller.MediaController;
import com.ronvel.farztev.admin.controller.dto.FileDetailDto;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.enums.MediaType;
import com.ronvel.farztev.admin.service.MediaService;

import io.swagger.annotations.ApiParam;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller
public class MediaControllerImpl implements MediaController {

  @Autowired
  MediaService mediaService;
  
  @Value("${application.media.photo.location}")
  String mediaFolder;

  public ResponseEntity<List<ListMedia>> apiAlbumAlbumIdMediaGet(
	      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
	      @ApiParam(value = "Album type", required = true) @PathVariable("albumType") MediaType albumType) {
    List<ListMedia> medias = mediaService.listAlbumMedias(albumId, albumType);
    return new ResponseEntity<List<ListMedia>>(medias, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Void> deleteMedia(
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    mediaService.deleteMedia(mediaId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Media> apiMediaIdGet(
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    Optional<Media> mediaOptional = mediaService.findMediaById(mediaId);
    return mediaOptional.map(media -> new ResponseEntity<Media>(media, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<Media>(HttpStatus.NOT_FOUND));
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdPut(
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId,
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    mediaService.updateMedia(mediaId, media);
    return new ResponseEntity<Media>(media, HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaPost(
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    Media mediaResult = mediaService.addMedia(media);
    return new ResponseEntity<Media>(mediaResult, HttpStatus.OK);
  }

/*
  public ResponseEntity<List<FileDetailDto>> listRootFiles() {
    return new ResponseEntity<List<FileDetailDto>>(mediaService.listFiles("/"), HttpStatus.OK);
  }
*/

  @Override
  public ResponseEntity<List<FileDetailDto>> listFiles(HttpServletRequest request) throws IOException {
    String folderPath  = request.getRequestURI().replaceAll("/api/files/", "");
    return new ResponseEntity<List<FileDetailDto>>(mediaService.listFiles(File.separator + folderPath), HttpStatus.OK);
  }

  public void getFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String folderPath  = request.getRequestURI().replaceAll("/api/image/", "");
//    ClassLoader classLoader = getClass().getClassLoader();
//    InputStream in = classLoader.getResourceAsStream("photos/" + folderPath);
    InputStream in = new FileInputStream(mediaFolder + File.separator + folderPath);
    
    String[] widths = request.getParameterValues("width");
    String[] heights = request.getParameterValues("height");
    if (widths == null || heights == null) {
      IOUtils.copy(in, response.getOutputStream());
    } else {
        int width = Integer.valueOf(widths[0]);
        int height  = Integer.valueOf(heights[0]);
        
        Thumbnails.of(in)
    	    .size(width, height)
    	    .crop(Positions.CENTER)
    	    .toOutputStream(response.getOutputStream());
    }
  }
}
