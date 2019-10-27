package com.ronvel.farztev.admin.dao.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trip_album")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripAlbumModel {
	
	@EmbeddedId
	private TripAlbumModelId tripAlbumId;
	
}
