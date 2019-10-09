package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.dao.TripDao;
import com.ronvel.farztev.admin.dao.model.TripModel;
import com.ronvel.farztev.admin.service.TripService;

@Service
public class TripServiceImpl implements TripService {

	private static final ModelMapper mapper = new ModelMapper();
	
	private final TripDao tripDao;
	
	@Autowired
	public TripServiceImpl(TripDao tripDao) {
		this.tripDao = tripDao;
	}

	@Override
	public List<TripDto> listTrips() {
		Iterable<TripModel> trips = tripDao.findAll();
		List<TripDto> tripDtos = new ArrayList<TripDto>();
		for(TripModel trip : trips) {
			tripDtos.add(mapper.map(trip, TripDto.class));
		}
		return tripDtos;
	}

	@Override
	public TripDto getTrip(Long tripId) {
		TripModel tripModel = tripDao.findOne(tripId);
		return mapper.map(tripModel, TripDto.class);
	}

	@Override
	public TripDto addTrip(TripDto tripDto) {
		TripModel tripModel = mapper.map(tripDto, TripModel.class);
		tripModel = tripDao.save(tripModel);
		return mapper.map(tripModel, TripDto.class);
	}

	@Override
	public void updateTrip(Long tripId, TripDto tripDto) {
		TripModel tripModel = mapper.map(tripDto, TripModel.class);
		tripModel.setId(tripId);
		tripModel = tripDao.save(tripModel);
	}

	@Override
	public void deleteTrip(Long tripId) {
		tripDao.delete(tripId);
	}
	
}
