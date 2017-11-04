package com.ronvel.farztev.admin.service;

import java.util.List;

import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;

/**
 * Service for selecting countries.
 * @author mronvel
 *
 */
public interface CountryService {
	/**
	 * Find a country by id.
	 * 
	 * @param id
	 * @return
	 */
	Country findCountryById(Long id);

	/**
	 * List all the countries.
	 * 
	 * @return
	 */
	List<ListCountry> listCountries();
}
