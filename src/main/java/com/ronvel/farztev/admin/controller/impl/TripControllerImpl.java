package com.ronvel.farztev.admin.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<TripDto> listTrips() {
		return tripService.listTrips();
	}


	@GetMapping(value = "/api/trip/{mediaId}", produces = { "application/json" })
	public TripDto getTrip(@PathVariable("tripId") Long tripId) {
		return tripService.getTrip(tripId);
	}

	@PostMapping(value = "/api/trip", produces = { "application/json" })
	public TripDto addTrip(@RequestBody TripDto trip) {
		return tripService.addTrip(trip);
	}

	@PutMapping(value = "/api/trip/{mediaId}", produces = { "application/json" })
	public void updateTrip(@PathVariable("tripId") Long tripId, @RequestBody TripDto trip) {
		tripService.updateTrip(tripId, trip);
	}

	@DeleteMapping(value = "/api/trip")
	public void deleteTrip(@PathVariable("tripId") Long tripId) {
		tripService.deleteTrip(tripId);
	}
}
