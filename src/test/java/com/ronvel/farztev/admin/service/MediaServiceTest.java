package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.ronvel.farztev.admin.controller.dto.ListMedia;
import com.ronvel.farztev.admin.controller.dto.Media;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.enums.MediaType;

public class MediaServiceTest extends BaseServiceTest {

  @Autowired
  private MediaService mediaService;

  @Autowired
  private ModelMapper mapper;


  @Before
  public void before() {
    clear();
    assertEquals(0L, mediaDao.count());
    countryDao.save(createDummyCountriesForTest());
    albumTypeDao.save(createDummyAlbumTypesForTest());
    albumDao.save(createDummyAlbumsForTest());
  }

  @Test
  public void findMedia() {
    MediaModel media = mediaDao.save(createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    Optional<Media> optionalMedia = mediaService.findMediaById(media.getId());
    assertTrue(optionalMedia.isPresent());
    testMedia(media.getId(), optionalMedia.get());
  }

  @Test
  public void findMedia_notFound() {
    Optional<Media> optionalMedia = mediaService.findMediaById(1L);
    assertFalse(optionalMedia.isPresent());
  }

  @Test
  public void listMedias() {
    MediaModel media = mediaDao.save(createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    List<ListMedia> medias = mediaService.listMedias();
    testListMedias(media.getId(), medias);
  }

  @Test
  public void listMedias_empty() {
    List<ListMedia> medias = mediaService.listMedias();
    assertNotNull(medias);
    assertTrue(medias.isEmpty());
  }

  @Test
  public void listAlbumMedias() {
    MediaModel media = mediaDao.save(createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    List<ListMedia> medias = mediaService.listAlbumMedias(media.getAlbum().getId(), MediaType.PHOTO);
    testListMedias(media.getId(), medias);
  }

  @Test
  public void addMedia() {
    Media newMedia = mapper.map(createMedia(albumDao), Media.class);
    Media media = mediaService.addMedia(newMedia);
    testMedia(media.getId(), media);
    assertEquals(1L, mediaDao.count());
  }

  @Test
  public void updateMedia() {
    MediaModel media = mediaDao.save(createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    Media updateMedia = createUpdateMedia();
    mediaService.updateMedia(media.getId(), updateMedia);
    Optional<Media> optionalMedia = mediaService.findMediaById(media.getId());
    assertTrue(optionalMedia.isPresent());
    testUpdatedMedia(media.getId(), optionalMedia.get());
  }

  @Test
  public void deleteMedia() {
    MediaModel media = mediaDao.save(createMedia(albumDao));
    assertEquals(1L, mediaDao.count());
    mediaService.deleteMedia(media.getId());
    assertEquals(0L, mediaDao.count());
  }

  public static void testMedia(Long id, Media media) {
    assertNotNull(media);
    assertEquals(id, media.getId());
    assertEquals(1L, media.getAlbumId().longValue());
    assertEquals("Zug beach", media.getAlbumName());
    assertEquals(new Date(1234567911L).getTime(), media.getCreated().getTime());
    assertEquals("Main place of Zug", media.getName());
    assertTrue(media.getOnline());
    assertEquals(MediaType.PHOTO.name(), media.getType());
    assertEquals(new Date(1234567913L).getTime(), media.getUpdated().getTime());
    assertEquals("the url zug", media.getUrl());
  }

  public static void testListMedias(Long id, List<ListMedia> medias) {
    assertNotNull(medias);
    ListMedia media = medias.get(0);
    assertEquals(id, media.getId());
    assertEquals(new Date(1234567911L).getTime(), media.getCreated().getTime());
    assertEquals("Main place of Zug", media.getName());
    assertTrue(media.getOnline());
    assertEquals(MediaType.PHOTO.name(), media.getType());
    assertEquals(new Date(1234567913L).getTime(), media.getUpdated().getTime());
    assertEquals("the url zug", media.getUrl());
  }

  public static MediaModel createMedia(AlbumDao albumDao) {
    MediaModel media = new MediaModel();
    media.setAlbum(albumDao.findOne(1L));
    media.setCreated(new Date(1234567911L));
    media.setName("Main place of Zug");
    media.setOnline(true);
    media.setType(MediaType.PHOTO);
    media.setUpdated(new Date(1234567913L));
    media.setUrl("the url zug");
    return media;
  }

  public static MediaModel createMediaWithoutAlbum() {
    MediaModel media = new MediaModel();
    media.setCreated(new Date(1234567911L));
    media.setName("Main place of Zug");
    media.setOnline(true);
    media.setType(MediaType.PHOTO);
    media.setUpdated(new Date(1234567913L));
    media.setUrl("the url zug");
    return media;
  }

  public static Media createUpdateMedia() {
    Media media = new Media();
    media.setAlbumId(2L);
    media.setCreated(new Date(1234567912L));
    media.setName("Main place of Barcelona");
    media.setOnline(false);
    media.setType(MediaType.VIDEO.name());
    media.setUpdated(new Date(1234567914L));
    media.setUrl("the url barcelona");
    return media;
  }

  public static void testUpdatedMedia(Long id, Media media) {
    assertNotNull(media);
    assertEquals(id, media.getId());
    assertEquals(2L, media.getAlbumId().longValue());
    assertEquals("Barcelona beach", media.getAlbumName());
    assertEquals(new Date(1234567912L).getTime(), media.getCreated().getTime());
    assertEquals("Main place of Barcelona", media.getName());
    assertFalse(media.getOnline());
    assertEquals(MediaType.VIDEO.name(), media.getType());
    assertEquals(new Date(1234567914L).getTime(), media.getUpdated().getTime());
    assertEquals("the url barcelona", media.getUrl());
  }

}
