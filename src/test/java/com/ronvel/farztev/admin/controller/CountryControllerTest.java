package com.ronvel.farztev.admin.controller;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.service.CountryServiceTest;

public class CountryControllerTest extends BaseControllerTest {

  @Autowired
  private CountryDao countryDao;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    clear();
    assertEquals(0L, countryDao.count());
  }

  @Test
  public void countriesGet() {
    countryDao.save(CountryServiceTest.createSwissCountry());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListCountry>> countriesResponse = this.restTemplate.exchange("/api/country",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListCountry>>() {});
    assertTrue(countriesResponse.getStatusCode().is2xxSuccessful());

    CountryServiceTest.testListCountries(countriesResponse.getBody());
  }

  @Test
  public void countriesGet_empty() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListCountry>> countriesResponse = this.restTemplate.exchange("/api/country",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListCountry>>() {});
    assertTrue(countriesResponse.getStatusCode().is2xxSuccessful());
    assertTrue(countriesResponse.getBody().isEmpty());
  }

  @Test
  public void countriesCountryIdGet() {
    countryDao.save(CountryServiceTest.createSwissCountry());
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Country> countryResponse = this.restTemplate.exchange("/api/country/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Country>() {});
    assertTrue(countryResponse.getStatusCode().is2xxSuccessful());
    CountryServiceTest.testSwissCountry(countryResponse.getBody());
  }

  @Test
  public void countriesCountryIdGet_notExisting() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Country> countryResponse = this.restTemplate.exchange("/api/country/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Country>() {});
    assertTrue(countryResponse.getStatusCode().is4xxClientError());
  }

  @Test
  public void countriesPost() {
    Country newCountry = mapper.map(CountryServiceTest.createSwissCountry(), Country.class);

    HttpEntity<Country> request = new HttpEntity<Country>(newCountry);

    ResponseEntity<Country> countryResponse = this.restTemplate.exchange("/api/country",
        HttpMethod.POST, request, new ParameterizedTypeReference<Country>() {});
    assertTrue(countryResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, countryDao.count());
    CountryServiceTest.testSwissCountry(countryResponse.getBody());
  }

  @Test
  public void countriesCountryIdPut() {
    countryDao.save(CountryServiceTest.createSwissCountry());
    assertEquals(1L, countryDao.count());
    Country updateCountry =
        mapper.map(CountryServiceTest.createUpdateSwissCountry(), Country.class);

    HttpEntity<Country> request = new HttpEntity<Country>(updateCountry);

    ResponseEntity<Void> updateResponse = this.restTemplate.exchange("/api/country/1",
        HttpMethod.PUT, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, countryDao.count());

    ResponseEntity<Country> countryResponse = this.restTemplate.exchange("/api/country/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Country>() {});
    assertTrue(countryResponse.getStatusCode().is2xxSuccessful());
    CountryServiceTest.testUpdatedSwissCountry(countryResponse.getBody());
  }

  @Test
  public void countriesCountryIdDelete() {
    countryDao.save(CountryServiceTest.createSwissCountry());
    assertEquals(1L, countryDao.count());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Void> deleteResponse = this.restTemplate.exchange("/api/country/1",
        HttpMethod.DELETE, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(deleteResponse.getStatusCode().is2xxSuccessful());
    assertEquals(0L, countryDao.count());
  }

}
