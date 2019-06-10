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

@Controller
public class AlbumTypeControllerImpl implements AlbumTypeController {

  @Autowired
  private AlbumTypeService albumTypeService;

  @Override
  public ResponseEntity<Void> apiAlbumtypeAlbumTypeIdDelete(@PathVariable("albumTypeId") Long albumTypeId) {
    albumTypeService.deleteAlbumType(albumTypeId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<AlbumType> apiAlbumtypeAlbumTypeIdGet(@PathVariable("albumTypeId") Long albumTypeId) {
    ResponseEntity<AlbumType> response;

    Optional<AlbumType> optionalAlbumType = albumTypeService.findAlbumTypeById(albumTypeId);
    if (optionalAlbumType.isPresent()) {
      response = new ResponseEntity<AlbumType>(optionalAlbumType.get(),HttpStatus.OK);
    } else {
      response = new ResponseEntity<AlbumType>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  @Override
  public ResponseEntity<AlbumType> apiAlbumtypeAlbumTypeIdPut(@PathVariable("albumTypeId") Long albumTypeId,
      @RequestBody AlbumType albumType) {
    albumTypeService.updateAlbumType(albumTypeId, albumType);
    return new ResponseEntity<AlbumType>(albumType,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ListAlbumType>> apiAlbumtypeGet() {
    List<ListAlbumType> listAlbumTypes = albumTypeService.listAlbumTypes();
    return new ResponseEntity<List<ListAlbumType>>(listAlbumTypes,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<ListAlbumType>> apiAlbumtypeByCountryGet(@PathVariable("countryId") Long countryId) {
    List<ListAlbumType> listAlbumTypes = albumTypeService.listAlbumTypesByCountry(countryId);
    return new ResponseEntity<List<ListAlbumType>>(listAlbumTypes,HttpStatus.OK);
  }

  @Override
  public ResponseEntity<AlbumType> apiAlbumtypePost(@RequestBody AlbumType albumType) {
    AlbumType newAlbumType = albumTypeService.addAlbumType(albumType);
    return new ResponseEntity<AlbumType>(newAlbumType,HttpStatus.OK);
  }

}
