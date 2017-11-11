package com.ronvel.farztev.admin.controller.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.ronvel.farztev.admin.controller.CountryController;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-11T22:32:17.694+02:00")

@Controller
public class CountriesController implements CountryController {

    public ResponseEntity<Void> countriesCountryIdDelete(@ApiParam(value = "Country ID",required=true ) @PathVariable("countryId") Long countryId) {
        // do some magic!
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Country> countriesCountryIdGet(@ApiParam(value = "Country ID",required=true ) @PathVariable("countryId") Long countryId) {
        // do some magic!
        return new ResponseEntity<Country>(HttpStatus.OK);
    }

    public ResponseEntity<Country> countriesCountryIdPut(@ApiParam(value = "Country ID",required=true ) @PathVariable("countryId") Long countryId,
        @ApiParam(value = "Country data." ,required=true ) @RequestBody Country country) {
        // do some magic!
        return new ResponseEntity<Country>(HttpStatus.OK);
    }

    public ResponseEntity<List<ListCountry>> countriesGet() {
        // do some magic!
        return new ResponseEntity<List<ListCountry>>(HttpStatus.OK);
    }

    public ResponseEntity<Country> countriesPost(@ApiParam(value = "Country data." ,required=true ) @RequestBody Country country) {
        // do some magic!
        return new ResponseEntity<Country>(HttpStatus.OK);
    }

}
