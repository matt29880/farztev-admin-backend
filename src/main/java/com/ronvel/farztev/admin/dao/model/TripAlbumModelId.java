package com.ronvel.farztev.admin.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class TripAlbumModelId implements Serializable {
	@Column(name = "trip_id")
	private Long tripId;
	@Column(name = "album_id")
	private Long albumId;
}
