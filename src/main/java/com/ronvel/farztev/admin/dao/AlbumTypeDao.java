package com.ronvel.farztev.admin.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;
import com.ronvel.farztev.admin.dao.model.MediaModel;

public interface AlbumTypeDao extends CrudRepository<AlbumTypeModel, Long> {

	  List<AlbumTypeModel> findAllByCountryId(Long countryId);
}
