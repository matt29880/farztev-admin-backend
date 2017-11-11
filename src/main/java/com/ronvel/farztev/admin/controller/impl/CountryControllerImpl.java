package com.ronvel.farztev.admin.controller.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import com.ronvel.farztev.admin.controller.CountryController;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;
import com.ronvel.farztev.admin.service.CountryService;
import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen",
    date = "2017-09-11T22:32:17.694+02:00")

@Controller
public class CountryControllerImpl implements CountryController {

  @Autowired
  private CountryService countryService;

  public ResponseEntity<Void> apiCountryCountryIdDelete(
      @ApiParam(value = "Country ID", required = true) @PathVariable("countryId") Long countryId) {
    countryService.deleteCountry(countryId);
    return new ResponseEntity<Void>(HttpStatus.OK);
  }

  public ResponseEntity<Country> apiCountryCountryIdGet(
      @ApiParam(value = "Country ID", required = true) @PathVariable("countryId") Long countryId) {
    ResponseEntity<Country> response;

    Optional<Country> optionalCountry = countryService.findCountryById(countryId);
    if (optionalCountry.isPresent()) {
      response = new ResponseEntity<Country>(optionalCountry.get(),HttpStatus.OK);
    } else {
      response = new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
    }

    return response;
  }

  public ResponseEntity<Country> apiCountryCountryIdPut(
      @ApiParam(value = "Country ID", required = true) @PathVariable("countryId") Long countryId,
      @ApiParam(value = "Country data.", required = true) @RequestBody Country country) {
    countryService.updateCountry(countryId, country);
    return new ResponseEntity<Country>(HttpStatus.OK);
  }

  public ResponseEntity<List<ListCountry>> apiCountryGet() {
    List<ListCountry> countries = countryService.listCountries();
    return new ResponseEntity<List<ListCountry>>(countries,HttpStatus.OK);
  }

  public ResponseEntity<Country> apiCountryPost(
      @ApiParam(value = "Country data.", required = true) @RequestBody Country country) {
    Country addedCountry = countryService.addCountry(country);
    return new ResponseEntity<Country>(addedCountry,HttpStatus.OK);
  }

}
