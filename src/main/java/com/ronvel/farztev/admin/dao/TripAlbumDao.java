package com.ronvel.farztev.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ronvel.farztev.admin.dao.model.TripAlbumModel;
import com.ronvel.farztev.admin.dao.model.TripAlbumModelId;

public interface TripAlbumDao extends CrudRepository<TripAlbumModel, TripAlbumModelId> {
	
	List<TripAlbumModel> getByTripAlbumIdTripId(Long tripId);
	
}
