package com.ronvel.farztev.admin.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.AlbumType;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.controller.dto.ListAlbumType;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;
import com.ronvel.farztev.admin.service.AlbumServiceTest;
import com.ronvel.farztev.admin.service.AlbumTypeService;
import com.ronvel.farztev.admin.service.AlbumTypeServiceTest;

public class AlbumTypeControllerTest extends BaseControllerTest {
  
  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    clear();
    countryDao.save(AlbumServiceTest.createDummyCountriesForTest());
  }

  @Test
  public void albumTypesGet() {
    AlbumTypeModel albumType = albumTypeDao.save(AlbumTypeServiceTest.createSwissAlbumType(countryDao));

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListAlbumType>> albumTypesResponse = this.restTemplate.exchange("/api/albumtype",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListAlbumType>>() {});
    assertTrue(albumTypesResponse.getStatusCode().is2xxSuccessful());

    AlbumTypeServiceTest.testListAlbumTypes(albumTypesResponse.getBody());
  }

  @Test
  public void albumTypesGet_empty() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListAlbumType>> albumsResponse = this.restTemplate.exchange("/api/albumtype",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListAlbumType>>() {});
    assertTrue(albumsResponse.getStatusCode().is2xxSuccessful());
    assertTrue(albumsResponse.getBody().isEmpty());
  }

  @Test
  public void albumTypesAlbumIdGet() {
    AlbumTypeModel albumType = albumTypeDao.save(AlbumTypeServiceTest.createSwissAlbumType(countryDao));
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<AlbumType> albumTypeResponse = this.restTemplate.exchange("/api/albumtype/"+albumType.getId(),
        HttpMethod.GET, request, new ParameterizedTypeReference<AlbumType>() {});
    assertTrue(albumTypeResponse.getStatusCode().is2xxSuccessful());
    AlbumTypeServiceTest.testSwissAlbumType(albumTypeResponse.getBody());
  }

  @Test
  public void albumTypesAlbumIdGet_notExisting() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<AlbumType> albumResponse = this.restTemplate.exchange("/api/albumtype/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<AlbumType>() {});
    assertTrue(albumResponse.getStatusCode().is4xxClientError());
  }

  @Test
  public void albumTypesPost() {
    AlbumTypeModel albumTypeModel = AlbumTypeServiceTest.createSwissAlbumType(countryDao);
    AlbumType newAlbumType = mapper.map(albumTypeModel, AlbumType.class);
    newAlbumType.setCountryId(albumTypeModel.getCountry().getId());
    newAlbumType.setCountryName(albumTypeModel.getCountry().getName());

    HttpEntity<AlbumType> request = new HttpEntity<AlbumType>(newAlbumType);

    ResponseEntity<AlbumType> albumTypeResponse = this.restTemplate.exchange("/api/albumtype",
        HttpMethod.POST, request, new ParameterizedTypeReference<AlbumType>() {});
    assertTrue(albumTypeResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, albumTypeDao.count());
    AlbumTypeServiceTest.testSwissAlbumType(albumTypeResponse.getBody());
  }

  @Test
  public void albumsAlbumTypeIdPut() {
    AlbumTypeModel albumType = albumTypeDao.save(AlbumTypeServiceTest.createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    AlbumType updateAlbumType =
        mapper.map(AlbumTypeServiceTest.createUpdateSpanishAlbumType(), AlbumType.class);

    HttpEntity<AlbumType> request = new HttpEntity<AlbumType>(updateAlbumType);

    ResponseEntity<Void> updateResponse = this.restTemplate.exchange("/api/albumtype/"+albumType.getId(),
        HttpMethod.PUT, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, albumTypeDao.count());

    ResponseEntity<AlbumType> albumTypeResponse = this.restTemplate.exchange("/api/albumtype/"+albumType.getId(),
        HttpMethod.GET, request, new ParameterizedTypeReference<AlbumType>() {});
    assertTrue(albumTypeResponse.getStatusCode().is2xxSuccessful());
    AlbumTypeServiceTest.testUpdatedSpanishAlbumType(albumTypeResponse.getBody());
  }

  @Test
  public void albumsAlbumTypeIdDelete() {
    AlbumTypeModel albumType = albumTypeDao.save(AlbumTypeServiceTest.createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Void> deleteResponse = this.restTemplate.exchange("/api/albumtype/"+albumType.getId(),
        HttpMethod.DELETE, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(deleteResponse.getStatusCode().is2xxSuccessful());
    assertEquals(0L, albumTypeDao.count());
  }

}
