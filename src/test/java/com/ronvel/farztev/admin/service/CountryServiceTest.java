package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.CountryModel;

public class CountryServiceTest extends BaseServiceTest {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryDao countryDao;

	@Before
	public void before() {
		countryDao.deleteAll();
		CountryModel country = createCountry("Switzerland");
		countryDao.save(country);
	}

	@Test
	public void findCountry() {
		Country country = countryService.findCountryById(1L);
		assertNotNull(country);
	}

	@Test
	public void listCountries() {
		List<ListCountry> countries = countryService.listCountries();
		assertNotNull(countries);
		assertEquals(1, countries.size());
	}

	private CountryModel createCountry(String name) {
		CountryModel country = new CountryModel();
		country.setName(name);
		return country;
	}

}