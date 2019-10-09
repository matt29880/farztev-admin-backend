package com.ronvel.farztev.admin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.ronvel.farztev.admin.controller.dto.TripDto;

public interface TripController {

	@GetMapping(value = "/api/trip", produces = { "application/json" })
	List<TripDto> listTrips();

	@GetMapping(value = "/api/trip/{mediaId}", produces = { "application/json" })
	TripDto getTrip(@PathVariable("tripId") Long tripId);

	@PostMapping(value = "/api/trip", produces = { "application/json" })
	TripDto addTrip(TripDto trip);

	@PutMapping(value = "/api/trip/{mediaId}", produces = { "application/json" })
	void updateTrip(@PathVariable("tripId") Long tripId, TripDto trip);

	@DeleteMapping(value = "/api/trip")
	void deleteTrip(@PathVariable("tripId") Long tripId);
}
