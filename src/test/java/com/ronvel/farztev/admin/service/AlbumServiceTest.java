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
import com.ronvel.farztev.admin.controller.dto.Album;
import com.ronvel.farztev.admin.controller.dto.ListAlbum;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;

public class AlbumServiceTest extends BaseServiceTest {

  @Autowired
  private AlbumService albumService;

  @Autowired
  private AlbumDao albumDao;

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private AlbumTypeDao albumTypeDao;
  
  @Autowired
  private CountryDao countryDao;


  @Before
  public void before() {
    clear();
    assertEquals(0L, albumDao.count());
    countryDao.save(createDummyCountriesForTest());
    albumTypeDao.save(createDummyAlbumTypesForTest());
  }

  @Test
  public void findAlbum() {
    AlbumModel album = albumDao.save(createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());
    Optional<Album> optionalAlbum = albumService.findAlbumById(album.getId());
    assertTrue(optionalAlbum.isPresent());
    testSwissAlbum(optionalAlbum.get());
  }

  @Test
  public void findAlbum_notFound() {
    Optional<Album> optionalAlbum = albumService.findAlbumById(1L);
    assertFalse(optionalAlbum.isPresent());
  }

  @Test
  public void listAlbums() {
    AlbumModel album = albumDao.save(createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());
    List<ListAlbum> albums = albumService.listAlbums();
    testListAlbums(album.getId(),albums);
  }

  @Test
  public void listAlbums_empty() {
    List<ListAlbum> albums = albumService.listAlbums();
    assertNotNull(albums);
    assertTrue(albums.isEmpty());
  }

  @Test
  public void addAlbum() {
    assertEquals(0L, albumDao.count());
    Album newAlbum = mapper.map(createSwissAlbum(albumTypeDao), Album.class);
    Album album = albumService.addAlbum(newAlbum);
    testSwissAlbum(album);
    assertEquals(1L, albumDao.count());
  }

  @Test
  public void updateAlbum() {
    AlbumModel album = albumDao.save(createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());
    Album updateAlbum = createUpdateSwissAlbum();
    albumService.updateAlbum(album.getId(), updateAlbum);
    Optional<Album> optionalAlbum = albumService.findAlbumById(album.getId());
    assertTrue(optionalAlbum.isPresent());
    testUpdatedSwissAlbum(album.getId(),optionalAlbum.get());
  }

  @Test
  public void deleteAlbum() {
    AlbumModel album = albumDao.save(createSwissAlbum(albumTypeDao));
    assertEquals(1L, albumDao.count());
    albumService.deleteAlbum(album.getId());
    assertEquals(0L, albumDao.count());
  }

  public static void testSwissAlbum(Album album) {
    assertNotNull(album);
    assertNotNull(album.getId());
    assertEquals(1L, album.getCountryId().longValue());
    assertEquals("Switzerland", album.getCountryName());
    assertEquals(new Date(1234567911L).getTime(), album.getCreated().getTime());
    assertEquals("The zug description", album.getDescription());
    assertEquals("Zug, the place to be", album.getName());
    assertTrue(album.getOnline());
    assertEquals(new Date(1234567913L).getTime(), album.getUpdated().getTime());
  }

  public static void testListAlbums(Long id,List<ListAlbum> albums) {
    assertNotNull(albums);
    assertEquals(1, albums.size());
    ListAlbum album = albums.get(0);
    assertEquals(id, album.getId());
    assertEquals(1L, album.getCountryId().longValue());
    assertEquals("Switzerland", album.getCountryName());
    assertEquals(new Date(1234567911L).getTime(), album.getCreated().getTime());
    assertEquals("Zug, the place to be", album.getName());
    assertTrue(album.getOnline());
    assertEquals(new Date(1234567913L).getTime(), album.getUpdated().getTime());
  }

  public static AlbumModel createSwissAlbum(AlbumTypeDao albumTypeDao) {
    AlbumModel album = new AlbumModel();
    album.setAlbumType(albumTypeDao.findOne(1L));
    album.setCreated(new Date(1234567911L));
    album.setDescription("The zug description");
    album.setName("Zug, the place to be");
    album.setOnline(true);
    album.setUpdated(new Date(1234567913L));
    return album;
  }

  public static Album createUpdateSwissAlbum() {
    Album album = new Album();
    album.setId(1L);
    album.setCountryId(2L);
    album.setCountryName("Spain");
    album.setCreated(new Date(12345679112L));
    album.setDescription("The zug description2");
    album.setName("Zug, the place to be2");
    album.setOnline(false);
    album.setUpdated(new Date(12345679132L));
    album.setAlbumTypeId(2L);
    return album;
  }

  public static void testUpdatedSwissAlbum(Long id,Album album) {
    assertNotNull(album);
    assertEquals(id, album.getId());
    assertEquals(2L, album.getCountryId().longValue());
    assertEquals("Spain", album.getCountryName());
    assertEquals(new Date(12345679112L).getTime(), album.getCreated().getTime());
    assertEquals("The zug description2", album.getDescription());
    assertEquals("Zug, the place to be2", album.getName());
    assertFalse(album.getOnline());
    assertEquals(new Date(12345679132L).getTime(), album.getUpdated().getTime());
  }

}
