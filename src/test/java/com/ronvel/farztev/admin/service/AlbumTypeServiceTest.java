package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.ronvel.farztev.admin.controller.dto.AlbumType;
import com.ronvel.farztev.admin.controller.dto.ListAlbumType;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;

public class AlbumTypeServiceTest extends BaseServiceTest {

  @Autowired
  private AlbumTypeService albumTypeService;

  @Autowired
  private AlbumTypeDao albumTypeDao;
  
  @Autowired
  private CountryDao countryDao;

  @Autowired
  private ModelMapper mapper;

  @Before
  public void before() {
    clear();
    assertEquals(0L, albumTypeDao.count());
    countryDao.save(createDummyCountriesForTest());
  }

  @Test
  public void findAlbumTypeById() {
    albumTypeDao.save(createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    Optional<AlbumType> optionalAlbumType = albumTypeService.findAlbumTypeById(1L);
    assertTrue(optionalAlbumType.isPresent());
    testSwissAlbumType(optionalAlbumType.get());
  }

  @Test
  public void findAlbumTypeById_notFound() {
    Optional<AlbumType> optionalAlbumType = albumTypeService.findAlbumTypeById(1L);
    assertFalse(optionalAlbumType.isPresent());
  }

  @Test
  public void listAlbumTypes() {
    albumTypeDao.save(createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    List<ListAlbumType> albumTypes = albumTypeService.listAlbumTypes();
    testListAlbumTypes(albumTypes);
  }

  @Test
  public void listAlbumTypes_empty() {
    List<ListAlbumType> albumTypes = albumTypeService.listAlbumTypes();
    assertNotNull(albumTypes);
    assertTrue(albumTypes.isEmpty());
  }
  
  @Test
  public void listAlbumTypesByCountry() {
    albumTypeDao.save(createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    List<ListAlbumType> albumTypes = albumTypeService.listAlbumTypesByCountry(1L);
    testListAlbumTypes(albumTypes);
  }

  @Test
  public void addAlbumType() {
    assertEquals(0L, albumTypeDao.count());
    AlbumType newAlbumType = mapper.map(createSwissAlbumType(countryDao), AlbumType.class);
    AlbumType albumType = albumTypeService.addAlbumType(newAlbumType);
    testSwissAlbumType(albumType);
    assertEquals(1L, albumTypeDao.count());
  }

  @Test
  public void updateAlbumType() {
    albumTypeDao.save(createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    AlbumType updateAlbumType = createUpdateSpanishAlbumType();
    albumTypeService.updateAlbumType(1L, updateAlbumType);
    Optional<AlbumType> optionalAlbumType = albumTypeService.findAlbumTypeById(1L);
    assertTrue(optionalAlbumType.isPresent());
    testUpdatedSpanishAlbumType(optionalAlbumType.get());
  }

  @Test
  public void deleteAlbumType() {
    albumTypeDao.save(createSwissAlbumType(countryDao));
    assertEquals(1L, albumTypeDao.count());
    albumTypeService.deleteAlbumType(1L);
    assertEquals(0L, albumTypeDao.count());
  }

  public static void testSwissAlbumType(AlbumType albumType) {
    assertNotNull(albumType);
    assertEquals(1L, albumType.getId().longValue());
    assertEquals(1L, albumType.getCountryId().longValue());
    assertEquals("Switzerland", albumType.getCountryName());
    assertEquals("Zug canton", albumType.getName());
  }

  public static void testListAlbumTypes(List<ListAlbumType> albumTypes) {
    assertNotNull(albumTypes);
    assertEquals(1, albumTypes.size());
    ListAlbumType albumType = albumTypes.get(0);
    assertEquals(1L, albumType.getId().longValue());
    assertEquals(1L, albumType.getCountryId().longValue());
    assertEquals("Switzerland", albumType.getCountryName());
    assertEquals("Zug canton", albumType.getName());
  }

  public static AlbumTypeModel createSwissAlbumType(CountryDao countryDao) {
    AlbumTypeModel albumType = new AlbumTypeModel();
    albumType.setId(1L);
    albumType.setName("Zug canton");
    albumType.setCountry(countryDao.findOne(1L));
    return albumType;
  }
  
  public static AlbumType createUpdateSpanishAlbumType() {
    AlbumType albumType = new AlbumType();
    albumType.setId(1L);
    albumType.setName("Barcelona region");
    albumType.setCountryId(2L);
    albumType.setCountryName("Spain");
    return albumType;
  }

  public static void testUpdatedSpanishAlbumType(AlbumType albumType) {
    assertNotNull(albumType);
    assertEquals(1L, albumType.getId().longValue());
    assertEquals(2L, albumType.getCountryId().longValue());
    assertEquals("Spain", albumType.getCountryName());
    assertEquals("Barcelona region", albumType.getName());
  }

}
