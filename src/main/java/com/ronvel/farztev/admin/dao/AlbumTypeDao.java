package com.ronvel.farztev.admin.dao;

import org.springframework.data.repository.CrudRepository;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;

public interface AlbumTypeDao extends CrudRepository<AlbumTypeModel, Long> {
	
}
