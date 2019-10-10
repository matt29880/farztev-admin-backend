package com.ronvel.farztev.admin.service;

import java.util.List;

import javax.annotation.Nullable;

import com.ronvel.farztev.admin.controller.dto.TripDto;

public interface TripService {

	List<TripDto> listTrips(@Nullable Boolean online);
	
	TripDto getTrip(Long tripId);
	
	TripDto addTrip(TripDto trip);
	
	void updateTrip(Long tripId, TripDto trip);
	
	void deleteTrip(Long tripId);
	
}
