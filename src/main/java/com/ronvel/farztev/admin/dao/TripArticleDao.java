package com.ronvel.farztev.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ronvel.farztev.admin.dao.model.TripArticleModel;
import com.ronvel.farztev.admin.dao.model.TripArticleModelId;

public interface TripArticleDao extends CrudRepository<TripArticleModel, TripArticleModelId> {
	
	List<TripArticleModel> getByTripArticleIdTripId(Long tripId);
	
}
