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
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.service.AlbumServiceTest;
import com.ronvel.farztev.admin.service.BaseServiceTest;

public class AlbumTypeControllerTest extends BaseControllerTest {

  @Autowired
  private AlbumDao albumDao;
  
  @Autowired
  private CountryDao countryDao;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private AlbumTypeDao albumTypeDao;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    clear();
    assertEquals(0L, albumDao.count());
    countryDao.save(AlbumServiceTest.createDummyCountriesForTest());
    albumTypeDao.save(AlbumServiceTest.createDummyAlbumTypesForTest());
  }

  @Test
  public void albumsGet() {
    AlbumModel album = albumDao.save(AlbumServiceTest.createSwissAlbum(albumTypeDao));

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListAlbum>> albumsResponse = this.restTemplate.exchange("/api/album",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListAlbum>>() {});
    assertTrue(albumsResponse.getStatusCode().is2xxSuccessful());

    AlbumServiceTest.testListAlbums(album.getId(),albumsResponse.getBody());
  }

  @Test
  public void albumsGet_empty() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListAlbum>> albumsResponse = this.restTemplate.exchange("/api/album",
        HttpMethod.GET, request, new ParameterizedTypeReference<List<ListAlbum>>() {});
    assertTrue(albumsResponse.getStatusCode().is2xxSuccessful());
    assertTrue(albumsResponse.getBody().isEmpty());
  }

  @Test
  public void albumsAlbumIdGet() {
    AlbumModel album = albumDao.save(AlbumServiceTest.createSwissAlbum(albumTypeDao));
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Album> albumResponse = this.restTemplate.exchange("/api/album/"+album.getId(),
        HttpMethod.GET, request, new ParameterizedTypeReference<Album>() {});
    assertTrue(albumResponse.getStatusCode().is2xxSuccessful());
    AlbumServiceTest.testSwissAlbum(albumResponse.getBody());
  }

  @Test
  public void albumsAlbumIdGet_notExisting() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Album> albumResponse = this.restTemplate.exchange("/api/album/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Album>() {});
    assertTrue(albumResponse.getStatusCode().is4xxClientError());
  }

  @Test
  public void albumsPost() {
    Album newAlbum = mapper.map(AlbumServiceTest.createSwissAlbum(albumTypeDao), Album.class);

    HttpEntity<Album> request = new HttpEntity<Album>(newAlbum);

    ResponseEntity<Album> albumResponse = this.restTemplate.exchange("/api/album",
        HttpMethod.POST, request, new ParameterizedTypeReference<Album>() {});
    assertTrue(albumResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, albumDao.count());
    AlbumServiceTest.testSwissAlbum(albumResponse.getBody());
  }

  @Test
  public void albumsAlbumIdPut() {
    AlbumModel album = albumDao.save(AlbumServiceTest.createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());
    Album updateAlbum =
        mapper.map(AlbumServiceTest.createUpdateSwissAlbum(), Album.class);

    HttpEntity<Album> request = new HttpEntity<Album>(updateAlbum);

    ResponseEntity<Void> updateResponse = this.restTemplate.exchange("/api/album/"+album.getId(),
        HttpMethod.PUT, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, albumDao.count());

    ResponseEntity<Album> albumResponse = this.restTemplate.exchange("/api/album/"+album.getId(),
        HttpMethod.GET, request, new ParameterizedTypeReference<Album>() {});
    assertTrue(albumResponse.getStatusCode().is2xxSuccessful());
    AlbumServiceTest.testUpdatedSwissAlbum(album.getId(),albumResponse.getBody());
  }

  @Test
  public void albumsAlbumIdDelete() {
    AlbumModel album = albumDao.save(AlbumServiceTest.createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Void> deleteResponse = this.restTemplate.exchange("/api/album/"+album.getId(),
        HttpMethod.DELETE, request, new ParameterizedTypeReference<Void>() {});

    assertTrue(deleteResponse.getStatusCode().is2xxSuccessful());
    assertEquals(0L, albumDao.count());
  }

}
