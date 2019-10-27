package com.ronvel.farztev.admin.service;

import java.util.List;

import com.ronvel.farztev.admin.controller.dto.Album;

public interface TripAlbumService {

	List<Album> listTripAlbum(Long tripId);
	
	void addTripAlbum(Long tripId, Long albumId);

	void deleteTripAlbum(Long tripId, Long albumId);
	
}
