package com.ronvel.farztev.admin.controller.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.AlbumTypeController;
import com.ronvel.farztev.admin.controller.dto.AlbumType;
import com.ronvel.farztev.admin.controller.dto.ListAlbumType;
import com.ronvel.farztev.admin.service.AlbumTypeService;
import io.swagger.annotations.ApiParam;

@Controller
public class AlbumTypeControllerImpl implements AlbumTypeController {

  @Autowired
  private AlbumTypeService albumTypeService;

  public ResponseEntity<Void> apiAlbumAlbumIdAlbumtypeAlbumTypeIdDelete(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Album Type ID",required=true ) @PathVariable("albumTypeId") Long albumTypeId) {
    albumTypeService.deleteAlbumType(albumId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<AlbumType> apiAlbumAlbumIdAlbumtypeAlbumTypeIdGet(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Album Type ID",required=true ) @PathVariable("albumTypeId") Long albumTypeId) {
    ResponseEntity<AlbumType> response;

    Optional<AlbumType> optionalAlbumType = albumTypeService.findAlbumTypeById(albumTypeId);
    if (optionalAlbumType.isPresent()) {
      response = new ResponseEntity<AlbumType>(optionalAlbumType.get(),HttpStatus.OK);
    } else {
      response = new ResponseEntity<AlbumType>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  public ResponseEntity<AlbumType> apiAlbumAlbumIdAlbumtypeAlbumTypeIdPut(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Album Type ID",required=true ) @PathVariable("albumTypeId") Long albumTypeId,
      @ApiParam(value = "Album Type data." ,required=true ) @RequestBody AlbumType albumType) {
    albumTypeService.updateAlbumType(albumId, albumType);
    return new ResponseEntity<AlbumType>(HttpStatus.OK);
  }

  public ResponseEntity<List<ListAlbumType>> apiAlbumAlbumIdAlbumtypeGet(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId) {
    List<ListAlbumType> listAlbumTypes = albumTypeService.listAlbumTypes();
    return new ResponseEntity<List<ListAlbumType>>(listAlbumTypes,HttpStatus.OK);
  }

  public ResponseEntity<AlbumType> apiAlbumAlbumIdAlbumtypePost(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Album Type data." ,required=true ) @RequestBody AlbumType albumType) {
    AlbumType newAlbumType = albumTypeService.addAlbumType(albumType);
    return new ResponseEntity<AlbumType>(newAlbumType,HttpStatus.OK);
  }

}
