package com.ronvel.farztev.admin.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.ronvel.farztev.admin.dao.model.MediaModel;

public interface MediaDao extends CrudRepository<MediaModel, Long> {

  List<MediaModel> findByAlbumId(Long albumId);
	
}
