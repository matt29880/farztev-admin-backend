package com.ronvel.farztev.admin.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.ronvel.farztev.admin.config.SpringConfiguration;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.ArticleDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.MediaDao;
import com.ronvel.farztev.admin.dao.model.AlbumModel;
import com.ronvel.farztev.admin.dao.model.AlbumTypeModel;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.dao.model.MediaModel;
import com.ronvel.farztev.admin.enums.Continent;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles = {"test"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    FlywayTestExecutionListener.class})
@Import(SpringConfiguration.class)
@FlywayTest(locationsForMigrate = {"db/test"})
public abstract class BaseServiceTest {

  @Autowired
  protected ArticleDao articleDao;

  @Autowired
  protected CountryDao countryDao;

  @Autowired
  protected AlbumTypeDao albumTypeDao;

  @Autowired
  protected AlbumDao albumDao;

  @Autowired
  protected MediaDao mediaDao;

  public static List<CountryModel> createDummyCountriesForTest() {
    List<CountryModel> countries = new ArrayList<>();

    countries.add(createCountryModel(1L, "Switzerland"));
    countries.add(createCountryModel(2L, "Spain"));

    return countries;
  }

  public static List<AlbumTypeModel> createDummyAlbumTypesForTest() {
    List<AlbumTypeModel> albumTypes = new ArrayList<>();

    albumTypes.add(createAlbumTypeModel(1L, "Zug canton", 1L));
    albumTypes.add(createAlbumTypeModel(2L, "Barcelona region", 2L));

    return albumTypes;
  }

  public static List<AlbumModel> createDummyAlbumsForTest() {
    List<AlbumModel> album = new ArrayList<>();

    album.add(createAlbumModel(1L, "Zug beach", 1L));
    album.add(createAlbumModel(2L, "Barcelona beach", 2L));

    return album;
  }

  private static CountryModel createCountryModel(Long id, String name) {
    String continent = Continent.EUROPE.toString();
    CountryModel country;
    country = new CountryModel();
    country.setId(id);
    country.setName(name);
    country.setCreated(new Date());
    country.setUpdated(new Date());
    country.setContinent(continent);
    country.setOnline(true);
    return country;
  }

  private static AlbumTypeModel createAlbumTypeModel(Long id, String name, Long countryId) {
    AlbumTypeModel albumTypeModel = new AlbumTypeModel();
    albumTypeModel.setId(id);
    albumTypeModel.setName(name);
    CountryModel country = new CountryModel();
    country.setId(countryId);
    albumTypeModel.setCountry(country);
    return albumTypeModel;
  }

  private static AlbumModel createAlbumModel(Long id, String name, Long albumTypeId) {
    AlbumTypeModel albumTypeModel = new AlbumTypeModel();
    albumTypeModel.setId(albumTypeId);
    AlbumModel albumModel = new AlbumModel();
    albumModel.setId(albumTypeId);
    albumModel.setName(name);
    albumModel.setAlbumType(albumTypeModel);
    albumModel.setCreated(new Date());
    albumModel.setUpdated(new Date());
    albumModel.setDescription("");
    albumModel.setOnline(true);
    return albumModel;
  }

  protected void clear() {
    clear(mediaDao, albumDao, articleDao, albumTypeDao, countryDao);
  }

  public static void clear(CrudRepository<?, ?>... daos) {
    for (CrudRepository<?, ?> dao : daos) {
      dao.deleteAll();
      assertEquals(0L, dao.count());
    }
  }

}
