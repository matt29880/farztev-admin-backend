package com.ronvel.farztev.admin.controller.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.AlbumController;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.service.AlbumService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2017-09-07T23:10:06.754+02:00")

@Controller
public class AlbumControllerImpl implements AlbumController {

  @Autowired
  private AlbumService albumService;

  @Override
  public ResponseEntity<Void> apiAlbumAlbumIdDelete(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId) {
    albumService.deleteAlbum(albumId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Album> apiAlbumAlbumIdGet(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId) {
    ResponseEntity<Album> response;

    Optional<Album> optionalAlbum = albumService.findAlbumById(albumId);
    if (optionalAlbum.isPresent()) {
      response = new ResponseEntity<Album>(optionalAlbum.get(),HttpStatus.OK);
    } else {
      response = new ResponseEntity<Album>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  @Override
  public ResponseEntity<Album> apiAlbumAlbumIdPut(
      @ApiParam(value = "Album ID", required = true) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Album data.", required = true) @RequestBody Album album) {
    albumService.updateAlbum(albumId, album);
    return new ResponseEntity<Album>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ListAlbum>> apiAlbumGet() {
    List<ListAlbum> listAlbums = albumService.listAlbums();
    return new ResponseEntity<List<ListAlbum>>(listAlbums,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Album> apiAlbumPost(
      @ApiParam(value = "Album data.", required = true) @RequestBody Album album) {
    Album newAlbum = albumService.addAlbum(album);
    return new ResponseEntity<Album>(newAlbum,HttpStatus.OK);
  }

}
