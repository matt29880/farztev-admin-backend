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
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.service.MediaServiceTest;

public class MediaControllerTest extends BaseControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    clear();
    assertEquals(0L, mediaDao.count());
    countryDao.save(MediaServiceTest.createDummyCountriesForTest());
    albumTypeDao.save(MediaServiceTest.createDummyAlbumTypesForTest());
    albumDao.save(MediaServiceTest.createDummyAlbumsForTest());
  }

  @Test
  public void mediasGet() {
    MediaModel media = mediaDao.save(MediaServiceTest.createMedia(albumDao));

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListMedia>> mediasResponse =
        this.restTemplate.exchange("/api/album/1/media/type/PHOTO", HttpMethod.GET, request,
            new ParameterizedTypeReference<List<ListMedia>>() {});
    assertTrue(mediasResponse.getStatusCode().is2xxSuccessful());

    MediaServiceTest.testListMedias(media.getId(), mediasResponse.getBody());
  }

  @Test
  public void mediasGet_empty() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<List<ListMedia>> mediasResponse =
        this.restTemplate.exchange("/api/album/1/media/type/PHOTO", HttpMethod.GET, request,
            new ParameterizedTypeReference<List<ListMedia>>() {});
    assertTrue(mediasResponse.getStatusCode().is2xxSuccessful());
    assertTrue(mediasResponse.getBody().isEmpty());
  }

  @Test
  public void mediasMediaIdGet() {
    MediaModel media = mediaDao.save(MediaServiceTest.createMedia(albumDao));
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Media> mediaResponse =
        this.restTemplate.exchange("/api/album/1/media/" + media.getId(), HttpMethod.GET, request,
            new ParameterizedTypeReference<Media>() {});
    assertTrue(mediaResponse.getStatusCode().is2xxSuccessful());
    MediaServiceTest.testMedia(media.getId(), mediaResponse.getBody());
  }

  @Test
  public void mediasMediaIdGet_notExisting() {
    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Media> mediaResponse = this.restTemplate.exchange("/api/album/1/media/1",
        HttpMethod.GET, request, new ParameterizedTypeReference<Media>() {});
    assertTrue(mediaResponse.getStatusCode().is4xxClientError());
  }

  @Test
  public void mediasPost() {
    Media newMedia = mapper.map(MediaServiceTest.createMedia(albumDao), Media.class);

    HttpEntity<Media> request = new HttpEntity<Media>(newMedia);

    ResponseEntity<Media> mediaResponse = this.restTemplate.exchange("/api/album/1/media",
        HttpMethod.POST, request, new ParameterizedTypeReference<Media>() {});
    assertTrue(mediaResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, mediaDao.count());
    MediaServiceTest.testMedia(mediaResponse.getBody().getId(), mediaResponse.getBody());
  }

  @Test
  public void mediasMediaIdPut() {
    MediaModel media = mediaDao.save(MediaServiceTest.createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    Media updateMedia = mapper.map(MediaServiceTest.createUpdateMedia(), Media.class);

    HttpEntity<Media> request = new HttpEntity<Media>(updateMedia);

    ResponseEntity<Void> updateResponse =
        this.restTemplate.exchange("/api/album/1/media/" + media.getId(), HttpMethod.PUT, request,
            new ParameterizedTypeReference<Void>() {});

    assertTrue(updateResponse.getStatusCode().is2xxSuccessful());

    assertEquals(1L, mediaDao.count());

    ResponseEntity<Media> mediaResponse =
        this.restTemplate.exchange("/api/album/1/media/" + media.getId(), HttpMethod.GET, request,
            new ParameterizedTypeReference<Media>() {});
    assertTrue(mediaResponse.getStatusCode().is2xxSuccessful());
    MediaServiceTest.testUpdatedMedia(media.getId(), mediaResponse.getBody());
  }

  @Test
  public void mediasMediaIdDelete() {
    MediaModel media = mediaDao.save(MediaServiceTest.createMedia(albumDao));
    assertEquals(1L, mediaDao.count());

    HttpHeaders headers = new HttpHeaders();
    HttpEntity<String> request = new HttpEntity<String>(headers);

    ResponseEntity<Void> deleteResponse =
        this.restTemplate.exchange("/api/album/1/media/" + media.getId(), HttpMethod.DELETE,
            request, new ParameterizedTypeReference<Void>() {});

    assertTrue(deleteResponse.getStatusCode().is2xxSuccessful());
    assertEquals(0L, mediaDao.count());
  }

}
