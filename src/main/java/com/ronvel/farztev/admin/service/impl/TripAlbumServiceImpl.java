package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.TripDto;
import com.ronvel.farztev.admin.dao.TripAlbumDao;
import com.ronvel.farztev.admin.dao.model.TripAlbumModel;
import com.ronvel.farztev.admin.dao.model.TripAlbumModelId;
import com.ronvel.farztev.admin.service.AlbumService;
import com.ronvel.farztev.admin.service.TripAlbumService;
import com.ronvel.farztev.admin.service.TripService;

@Service
public class TripAlbumServiceImpl implements TripAlbumService {

	private final TripAlbumDao tripAlbumDao;
	private final AlbumService albumService;
	private final TripService tripService;

	@Autowired
	public TripAlbumServiceImpl(TripAlbumDao tripAlbumDao, AlbumService albumService, TripService tripService) {
		this.tripAlbumDao = tripAlbumDao;
		this.albumService = albumService;
		this.tripService = tripService;
	}

	@Override
	public List<Album> listTripAlbum(Long tripId) {
		List<Album> albums = new ArrayList<Album>();
		Iterable<TripAlbumModel> tripAlbums = tripAlbumDao.getByTripAlbumIdTripId(tripId);
		for(TripAlbumModel t : tripAlbums) {
			Album album = albumService.findAlbumById(t.getTripAlbumId().getAlbumId()).get();
			albums.add(album);
		}
		return albums;
	}

	@Override
	public void addTripAlbum(Long tripId, Long albumId) {
		TripAlbumModel tripAlbumModel = new TripAlbumModel(
				new TripAlbumModelId(tripId, albumId));
		tripAlbumDao.save(tripAlbumModel);
	}

	@Override
	public void deleteTripAlbum(Long tripId, Long albumId) {
		tripAlbumDao.delete(new TripAlbumModelId(tripId, albumId));
	}

	@Override
	public TripDto getTripByAlbum(Long albumId) {
		List<TripAlbumModel> tripAlbums = tripAlbumDao.getByTripAlbumIdAlbumId(albumId);
		return tripService.getTrip(tripAlbums.get(0).getTripAlbumId().getTripId());
	}

}
