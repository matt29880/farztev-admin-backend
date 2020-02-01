package com.ronvel.farztev.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ronvel.farztev.admin.dao.model.TripModel;

public interface TripDao extends CrudRepository<TripModel, Long> {
	
	List<TripModel> findAllByOnlineOrderByStartDesc(boolean online);
	
}
