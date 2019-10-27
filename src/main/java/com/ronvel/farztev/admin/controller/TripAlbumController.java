package com.ronvel.farztev.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ronvel.farztev.admin.controller.dto.Album;

public interface TripAlbumController {

	@GetMapping(value = "/api/tripalbum/{tripId}", produces = { "application/json" })
	ResponseEntity<List<Album>> listAlbumsByTrip(@PathVariable("tripId") Long tripId);

	@PostMapping(value = "/api/tripalbum/{tripId}/{albumId}", produces = { "application/json" })
	ResponseEntity<Void> addTrip(@PathVariable("tripId") Long tripId, @PathVariable("albumId") Long albumId);

	@DeleteMapping(value = "/api/tripalbum/{tripId}")
	ResponseEntity<Void> deleteTrip(@PathVariable("tripId") Long tripId);
}
