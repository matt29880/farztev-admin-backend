package com.ronvel.farztev.admin.service;

import java.util.List;

import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.dao.model.TripAlbumModel;
import com.ronvel.farztev.admin.dao.model.TripModel;

public interface TripAlbumService {

	List<Album> listTripAlbum(Long tripId);
	
	void addTripAlbum(Long tripId, Long albumId);

	void deleteTripAlbum(Long tripId, Long albumId);

	TripDto getTripByAlbum(Long albumId);
	
}
