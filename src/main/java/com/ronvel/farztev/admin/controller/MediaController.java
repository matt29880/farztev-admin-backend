package com.ronvel.farztev.admin.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface MediaController {

  @ApiOperation(value = "", notes = "", response = ListMedia.class, responseContainer = "List", tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "successful operation", response = ListMedia.class) })
  @RequestMapping(value = "/api/album/{albumId}/media",
      produces = { "application/json" }, 
      method = RequestMethod.GET)
  ResponseEntity<List<ListMedia>> apiAlbumAlbumIdMediaGet(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId);


  @ApiOperation(value = "", notes = "", response = Void.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "successful operation", response = Void.class),
      @ApiResponse(code = 400, message = "Invalid status value", response = Void.class) })
  @RequestMapping(value = "/api/album/{albumId}/media/{mediaId}",
      produces = { "application/json" }, 
      method = RequestMethod.DELETE)
  ResponseEntity<Void> apiAlbumAlbumIdMediaMediaIdDelete(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID",required=true ) @PathVariable("mediaId") Long mediaId);


  @ApiOperation(value = "", notes = "", response = Media.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "successful operation", response = Media.class),
      @ApiResponse(code = 404, message = "Country not found", response = Media.class) })
  @RequestMapping(value = "/api/album/{albumId}/media/{mediaId}",
      produces = { "application/json" }, 
      method = RequestMethod.GET)
  ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdGet(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID",required=true ) @PathVariable("mediaId") Long mediaId);


  @ApiOperation(value = "", notes = "", response = Media.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "successful operation", response = Media.class),
      @ApiResponse(code = 400, message = "Invalid status value", response = Media.class) })
  @RequestMapping(value = "/api/album/{albumId}/media/{mediaId}",
      produces = { "application/json" }, 
      method = RequestMethod.PUT)
  ResponseEntity<Media> apiAlbumAlbumIdMediaMediaIdPut(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media ID",required=true ) @PathVariable("mediaId") Long mediaId,
      @ApiParam(value = "Media data." ,required=true ) @RequestBody Media media);


  @ApiOperation(value = "", notes = "", response = Media.class, tags={  })
  @ApiResponses(value = { 
      @ApiResponse(code = 200, message = "successful operation", response = Media.class),
      @ApiResponse(code = 400, message = "Invalid status value", response = Media.class) })
  @RequestMapping(value = "/api/album/{albumId}/media",
      produces = { "application/json" }, 
      method = RequestMethod.POST)
  ResponseEntity<Media> apiAlbumAlbumIdMediaPost(@ApiParam(value = "Album ID",required=true ) @PathVariable("albumId") Long albumId,
      @ApiParam(value = "Media data." ,required=true ) @RequestBody Media media);


}
