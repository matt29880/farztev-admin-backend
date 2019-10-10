package com.ronvel.farztev.admin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ronvel.farztev.admin.controller.TripController;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.service.TripService;

@Controller
public class TripControllerImpl implements TripController {
	
	private final TripService tripService;
	
	@Autowired
	public TripControllerImpl(TripService tripService) {
		this.tripService = tripService;
	}
	

	@GetMapping(value = "/api/trip", produces = { "application/json" })
	public ResponseEntity<List<TripDto>> listTrips(Boolean online) {
		List<TripDto> trips = tripService.listTrips(online);
		return new ResponseEntity<List<TripDto>>(trips, HttpStatus.OK);
	}


	@GetMapping(value = "/api/trip/{tripId}", produces = { "application/json" })
	public ResponseEntity<TripDto> getTrip(@PathVariable("tripId") Long tripId) {
		TripDto tripDto = tripService.getTrip(tripId);
		return new ResponseEntity<TripDto>(tripDto, HttpStatus.OK);
	}

	@PostMapping(value = "/api/trip", produces = { "application/json" })
	public ResponseEntity<TripDto> addTrip(@RequestBody TripDto trip) {
		TripDto tripDto = tripService.addTrip(trip);
		return new ResponseEntity<TripDto>(tripDto, HttpStatus.OK);
	}

	@PutMapping(value = "/api/trip/{tripId}", produces = { "application/json" })
	public ResponseEntity<Void> updateTrip(@PathVariable("tripId") Long tripId, @RequestBody TripDto trip) {
		tripService.updateTrip(tripId, trip);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping(value = "/api/trip/{tripId}")
	public ResponseEntity<Void> deleteTrip(@PathVariable("tripId") Long tripId) {
		tripService.deleteTrip(tripId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
