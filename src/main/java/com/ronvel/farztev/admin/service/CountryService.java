package com.ronvel.farztev.admin.service;

import java.util.List;
import java.util.Optional;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;

/**
 * Service for selecting countries.
 * 
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
	Optional<Country> findCountryById(Long id);

	/**
	 * List all the countries.
	 * 
	 * @return
	 */
	List<ListCountry> listCountries();

	/**
	 * Add a new country.
	 * @param country
	 * @return
	 */
	Country addCountry(Country country);

	/**
	 * Update a country.
	 * @param id
	 * @param country
	 */
	void updateCountry(Long id, Country country);
	
	/**Delete a country.
	 * 
	 * @param id
	 */
	void deleteCountry(Long id);
}
