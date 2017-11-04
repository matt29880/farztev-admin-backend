package com.ronvel.farztev.admin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-09-11T22:32:17.694+02:00")

@Api(value = "countries", description = "the countries API")
public interface CountryController {

    @ApiOperation(value = "", notes = "", response = Country.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Country.class),
        @ApiResponse(code = 404, message = "Country not found", response = Country.class) })
    @RequestMapping(value = "/countries/{countryId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Country> countriesCountryIdGet(@ApiParam(value = "ID of the country to return",required=true ) @PathVariable("countryId") Long countryId);


    @ApiOperation(value = "", notes = "", response = ListCountry.class, responseContainer = "List", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = ListCountry.class) })
    @RequestMapping(value = "/countries",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ListCountry>> countriesGet();

}
