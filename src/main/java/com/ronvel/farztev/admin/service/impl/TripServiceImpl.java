package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.dao.TripDao;
import com.ronvel.farztev.admin.dao.model.TripModel;
import com.ronvel.farztev.admin.service.MediaService;
import com.ronvel.farztev.admin.service.TripService;

@Service
public class TripServiceImpl implements TripService {

	private static final ModelMapper mapper = new ModelMapper();
	
	private final TripDao tripDao;
	private final MediaService mediaService;
	
	@Autowired
	public TripServiceImpl(TripDao tripDao, MediaService mediaService) {
		this.tripDao = tripDao;
		this.mediaService = mediaService;
	}

	@Override
	public List<TripDto> listTrips(@Nullable Boolean online) {
		Iterable<TripModel> trips;
		if (online == null) {
			trips = tripDao.findAll();
		} else {
			trips = tripDao.findAllByOnlineOrderByStartDesc(online);
		}
		List<TripDto> tripDtos = new ArrayList<TripDto>();
		for(TripModel trip : trips) {
			TripDto tripDto = mapper.map(trip, TripDto.class);
			if (tripDto.getThumbnailId() != null) {
				Optional<Media> media = mediaService.findMediaById(tripDto.getThumbnailId());
				if (media.isPresent()) {
					tripDto.setThumbnailUrl(media.get().getUrl());
				}
			}
			tripDtos.add(tripDto);
		}
		return tripDtos;
	}

	@Override
	public TripDto getTrip(Long tripId) {
		TripModel tripModel = tripDao.findOne(tripId);
		TripDto tripDto = mapper.map(tripModel, TripDto.class);
		if (tripDto.getThumbnailId() != null) {
			Optional<Media> media = mediaService.findMediaById(tripDto.getThumbnailId());
			if (media.isPresent()) {
				tripDto.setThumbnailUrl(media.get().getUrl());
			}
		}
		return tripDto;
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
