package com.ronvel.farztev.admin.controller;

import org.flywaydb.test.annotation.FlywayTest;
import org.flywaydb.test.junit.FlywayTestExecutionListener;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.ronvel.farztev.admin.config.SpringConfiguration;
import com.ronvel.farztev.admin.dao.AlbumDao;
import com.ronvel.farztev.admin.dao.AlbumTypeDao;
import com.ronvel.farztev.admin.dao.ArticleDao;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.service.BaseServiceTest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles={"test"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
@Import(SpringConfiguration.class)
@FlywayTest(locationsForMigrate = {"db"})
public abstract class BaseControllerTest {
  @Autowired
  protected ArticleDao articleDao;
  
  @Autowired
  protected CountryDao countryDao;

  @Autowired
  protected AlbumTypeDao albumTypeDao;

  @Autowired
  protected AlbumDao albumDao;
  
  protected void clear() {
    BaseServiceTest.clear(countryDao, articleDao, albumTypeDao, albumDao);
  }

}
