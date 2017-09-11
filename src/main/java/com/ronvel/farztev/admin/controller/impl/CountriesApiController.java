package com.ronvel.farztev.admin.controller.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.ronvel.farztev.admin.controller.CountriesApi;
import com.ronvel.farztev.admin.controller.model.Country;
import com.ronvel.farztev.admin.controller.model.ListCountry;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-11T22:32:17.694+02:00")

@Controller
public class CountriesApiController implements CountriesApi {

	public ResponseEntity<Country> countriesCountryIdGet(
			@ApiParam(value = "ID of the country to return", required = true) @PathVariable("countryId") Long countryId) {
		Country country = new Country();
		country.setId(1L);
		country.setName("Germany");
		country.setDescription("blabladescription");
		country.setAbbreviation("de");
		country.setTrip("blablatrip");
		country.setFavorite("blablafavorite");
		country.setWhy("blablawhy");
		country.setConclusion("blablaconclusion");
		country.setBeginning(new Date());
		country.setEnding(new Date());
		country.setCreated(new Date());
		country.setUpdated(new Date());
		country.setOnline(true);
		country.setContinent("EUROPE");
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}

	public ResponseEntity<List<ListCountry>> countriesGet() {
		ListCountry country = new ListCountry();
		country.setId(1L);
		country.setName("Germany");
		country.setAbbreviation("de");
		country.setBeginning(new Date());
		country.setEnding(new Date());
		country.setCreated(new Date());
		country.setUpdated(new Date());
		country.setOnline(true);
		country.setContinent("EUROPE");
		return new ResponseEntity<List<ListCountry>>(Arrays.asList(country, country, country, country), HttpStatus.OK);
	}

}
