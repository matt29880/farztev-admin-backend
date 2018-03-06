package com.ronvel.farztev.admin.controller.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import io.swagger.annotations.ApiParam;

@Controller
public class MediaControllerImpl {
  public ResponseEntity<List<ListMedia>> apiAlbumAlbumIdMediaGet(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId) {
    // do some magic!
    return new ResponseEntity<List<ListMedia>>(HttpStatus.OK);
  }

  public ResponseEntity<Void> apiAlbumAlbumIdMediaMediaIdDelete(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    // do some magic!
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdGet(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId) {
    // do some magic!
    return new ResponseEntity<Media>(HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdPut(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID", required = true) @PathVariable("mediaId") Long mediaId,
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    // do some magic!
    return new ResponseEntity<Media>(HttpStatus.OK);
  }

  public ResponseEntity<Media> apiAlbumAlbumIdMediaPost(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media data.", required = true) @RequestBody Media media) {
    // do some magic!
    return new ResponseEntity<Media>(HttpStatus.OK);
  }
}
