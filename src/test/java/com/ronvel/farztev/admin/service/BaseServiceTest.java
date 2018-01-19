package com.ronvel.farztev.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import com.ronvel.farztev.admin.config.SpringConfiguration;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.enums.Continent;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles(profiles={"test"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
@Import(SpringConfiguration.class)
public abstract class BaseServiceTest {

  public static List<CountryModel> createDummyCountriesForTest(){
    List<CountryModel> countries = new ArrayList<>();
    
    countries.add(createCountryModel(1L,"Switzerland"));
    countries.add(createCountryModel(2L,"Spain"));
    
    return countries;
  }
  
  private static CountryModel createCountryModel(Long id,String name) {    
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
  
}
