package com.ronvel.farztev.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.enums.MediaType;

public interface MediaDao extends CrudRepository<MediaModel, Long> {

  List<MediaModel> findByAlbumId(Long albumId);
  List<MediaModel> findByAlbumIdAndType(Long albumId, MediaType type);
	
}
