package com.ronvel.farztev.admin.dao;

import com.ronvel.farztev.admin.dao.model.CountryModel;
import org.springframework.data.repository.CrudRepository;

public interface CountryDao extends CrudRepository<CountryModel, Long> {
	
}
