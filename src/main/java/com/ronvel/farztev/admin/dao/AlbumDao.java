package com.ronvel.farztev.admin.dao;

import org.springframework.data.repository.CrudRepository;
import com.ronvel.farztev.admin.dao.model.AlbumModel;

public interface AlbumDao extends CrudRepository<AlbumModel, Long> {
	
}
