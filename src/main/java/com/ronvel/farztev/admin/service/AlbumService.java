package com.ronvel.farztev.admin.service;

import java.util.List;
import java.util.Optional;
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;

/**
 * Service for selecting albums.
 * 
 * @author mronvel
 *
 */
public interface AlbumService {
	/**
	 * Find an article by id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Album> findAlbumById(Long id);

	/**
	 * List all the articles.
	 * 
	 * @return
	 */
	List<ListAlbum> listAlbums();

	/**
	 * Add a new album.
	 * @param album
	 * @return
	 */
	Album addAlbum(Album album);

	/**
	 * Update a album.
	 * @param id
	 * @param album
	 */
	void updateAlbum(Long id, Album album);
	
	/**Delete a album.
	 * 
	 * @param id
	 */
	void deleteAlbum(Long id);
}
