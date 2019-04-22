package com.ronvel.farztev.admin.service;

import com.ronvel.farztev.admin.controller.dto.FileDetailDto;
import java.util.List;
import java.util.Optional;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;

/**
 * Service for selecting medias.
 *
 * @author mronvel
 *
 */
public interface MediaService {
  /**
   * Find a media by id.
   *
   * @param id
   * @return
   */
  Optional<Media> findMediaById(Long id);

  /**
   * List all the medias.
   *
   * @return
   */
  List<ListMedia> listMedias();

  /**
   * List all the medias of an album.
   *
   * @return
   */
  List<ListMedia> listAlbumMedias(Long albumId);

  /**
   * Add a new media.
   * @param album
   * @return
   */
  Media addMedia(Media media);

  /**
   * Update a media.
   * @param id
   * @param album
   */
  void updateMedia(Long id, Media media);

  /**Delete a media.
   *
   * @param id
   */
  void deleteMedia(Long id);

	List<FileDetailDto> listFiles(String folderPath);
}
