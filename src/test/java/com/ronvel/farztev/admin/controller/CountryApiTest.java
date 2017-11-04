package com.ronvel.farztev.admin.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronvel.farztev.admin.controller.model.Country;
import com.ronvel.farztev.admin.controller.model.ListCountry;

public class CountryApiTest extends BaseControllerTest {

	@Autowired
	private CountryController countryController;
	
	@Test
	public void countriesGet() {
		List<ListCountry> countries = countryController.countriesGet().getBody();
		assertEquals(4,countries.size());
	}

	@Test
	public void countriesCountryIdGet() {
		Country country = countryController.countriesCountryIdGet(1L).getBody();
		
	}

}
