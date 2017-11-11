package com.ronvel.farztev.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

  @Autowired
  private ModelMapper mapper;

  @Autowired
  private CountryDao countryDao;

  @Override
  public Optional<Country> findCountryById(Long id) {
    Optional<Country> optional;
    CountryModel countryModel = countryDao.findOne(id);
    if (countryModel == null) {
      optional = Optional.empty();
    } else {
      Country country = mapper.map(countryModel, Country.class);
      optional = Optional.of(country);
    }
    return optional;
  }

  @Override
  public List<ListCountry> listCountries() {
    List<ListCountry> listCountries = new ArrayList<>();
    Iterable<CountryModel> countries = countryDao.findAll();
    countries.forEach(country -> listCountries.add(mapper.map(country, ListCountry.class)));
    return listCountries;
  }

  @Override
  public Country addCountry(Country country) {
    CountryModel countryModel = mapper.map(country, CountryModel.class);
    CountryModel resultCountryModel = countryDao.save(countryModel);
    Country resultCountry = mapper.map(resultCountryModel, Country.class);
    return resultCountry;
  }

  @Override
  public void updateCountry(Long id, Country country) {
    country.setId(id);
    CountryModel countryModel = mapper.map(country, CountryModel.class);
    countryDao.save(countryModel);
  }

  @Override
  public void deleteCountry(Long id) {
    countryDao.delete(id);
  }

}
