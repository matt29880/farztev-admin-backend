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
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.enums.Continent;

public class AlbumServiceTest extends BaseServiceTest {

  @Autowired
  private AlbumService albumService;

  @Autowired
  private AlbumDao albumDao;
  
  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    albumDao.deleteAll();
    countryDao.deleteAll();
    assertEquals(0L, albumDao.count());
    countryDao.save(createDummyCountriesForTest());
  }

  @Test
  public void findAlbum() {
    albumDao.save(createSwissAlbum());
    assertEquals(1L, albumDao.count());
    Optional<Album> optionalAlbum = albumService.findAlbumById(1L);
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
    albumDao.save(createSwissAlbum());
    assertEquals(1L, albumDao.count());
    List<ListAlbum> albums = albumService.listAlbums();
    testListAlbums(albums);
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
    Album newAlbum = mapper.map(createSwissAlbum(), Album.class);
    Album album = albumService.addAlbum(newAlbum);
    testSwissAlbum(album);
    assertEquals(1L, albumDao.count());
  }

  @Test
  public void updateAlbum() {
    albumDao.save(createSwissAlbum());
    assertEquals(1L, albumDao.count());
    Album updateAlbum = createUpdateSwissAlbum();
    albumService.updateAlbum(1L, updateAlbum);
    Optional<Album> optionalAlbum = albumService.findAlbumById(1L);
    assertTrue(optionalAlbum.isPresent());
    testUpdatedSwissAlbum(optionalAlbum.get());
  }

  @Test
  public void deleteAlbum() {
    albumDao.save(createSwissAlbum());
    assertEquals(1L, albumDao.count());
    albumService.deleteAlbum(1L);
    assertEquals(0L, albumDao.count());
  }

  public static void testSwissAlbum(Album album) {
    assertNotNull(album);
    assertEquals(1L, album.getId().longValue());
    assertEquals(1L, album.getCountryId().longValue());
    assertEquals("Switzerland", album.getCountryName());
    assertEquals(new Date(1234567911L).getTime(), album.getCreated().getTime());
    assertEquals("The zug description", album.getDescription());
    assertEquals("Zug, the place to be", album.getName());
    assertTrue(album.getOnline());
    assertEquals(new Date(1234567913L).getTime(), album.getUpdated().getTime());
  }

  public static void testListAlbums(List<ListAlbum> albums) {
    assertNotNull(albums);
    assertEquals(1, albums.size());
    ListAlbum album = albums.get(0);
    assertEquals(1L, album.getId().longValue());
    assertEquals(1L, album.getCountryId().longValue());
    assertEquals("Switzerland", album.getCountryName());
    assertEquals(new Date(1234567911L).getTime(), album.getCreated().getTime());
    assertEquals("Zug, the place to be", album.getName());
    assertTrue(album.getOnline());
    assertEquals(new Date(1234567913L).getTime(), album.getUpdated().getTime());
  }

  public static AlbumModel createSwissAlbum() {
    AlbumModel album = new AlbumModel();
    album.setId(1L);
    CountryModel country = new CountryModel();
    country.setId(1L);
    country.setContinent(Continent.EUROPE.name());
    album.setCountry(country);
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
    return album;
  }

  public static void testUpdatedSwissAlbum(Album album) {
    assertNotNull(album);
    assertEquals(1L, album.getId().longValue());
    assertEquals(2L, album.getCountryId().longValue());
    assertEquals("Spain", album.getCountryName());
    assertEquals(new Date(12345679112L).getTime(), album.getCreated().getTime());
    assertEquals("The zug description2", album.getDescription());
    assertEquals("Zug, the place to be2", album.getName());
    assertFalse(album.getOnline());
    assertEquals(new Date(12345679132L).getTime(), album.getUpdated().getTime());
  }

}
