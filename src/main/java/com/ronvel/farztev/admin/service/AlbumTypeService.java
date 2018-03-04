package com.ronvel.farztev.admin.service;

import java.util.List;
import java.util.Optional;
import com.ronvel.farztev.admin.controller.dto.AlbumType;
import com.ronvel.farztev.admin.controller.dto.ListAlbumType;

/**
 * Service for managing albums types.
 * 
 * @author mronvel
 *
 */
public interface AlbumTypeService {
	/**
	 * Find an article type by id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<AlbumType> findAlbumTypeById(Long id);

	/**
	 * List all the articles.
	 * 
	 * @return
	 */
	List<ListAlbumType> listAlbumTypes();

	/**
	 * Add a new album.
	 * @param album
	 * @return
	 */
	AlbumType addAlbumType(AlbumType albumType);

	/**
	 * Update a album.
	 * @param id
	 * @param album
	 */
	void updateAlbumType(Long id, AlbumType albumType);
	
	/**Delete a album type.
	 * 
	 * @param id
	 */
	void deleteAlbumType(Long id);
}
