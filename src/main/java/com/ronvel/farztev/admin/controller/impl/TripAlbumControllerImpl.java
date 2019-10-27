package com.ronvel.farztev.admin.controller.impl;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ronvel.farztev.admin.controller.TripAlbumController;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.service.TripAlbumService;

@Controller
public class TripAlbumControllerImpl implements TripAlbumController {

	private final TripAlbumService tripAlbumService;

	@Autowired
	public TripAlbumControllerImpl(TripAlbumService tripAlbumService) {
		this.tripAlbumService = tripAlbumService;
	}

	@GetMapping(value = "/api/tripalbum/{tripId}", produces = { "application/json" })
	public ResponseEntity<List<Album>> listAlbumsByTrip(@PathVariable("tripId") Long tripId) {
		List<Album> albums = tripAlbumService.listTripAlbum(tripId);
		return new ResponseEntity<List<Album>>(albums, HttpStatus.OK);
	}

	@PostMapping(value = "/api/tripalbum/{tripId}/{albumId}", produces = { "application/json" })
	public ResponseEntity<Void> addTrip(@PathVariable("tripId") Long tripId, @PathVariable("albumId") Long albumId) {
		tripAlbumService.addTripAlbum(tripId, albumId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/api/tripalbum/{tripId}")
	public ResponseEntity<Void> deleteTrip(@PathVariable("tripId") Long tripId) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
