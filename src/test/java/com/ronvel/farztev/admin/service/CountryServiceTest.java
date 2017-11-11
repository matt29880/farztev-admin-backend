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
import com.ronvel.farztev.admin.controller.dto.Country;
import com.ronvel.farztev.admin.controller.dto.ListCountry;
import com.ronvel.farztev.admin.dao.CountryDao;
import com.ronvel.farztev.admin.dao.model.CountryModel;
import com.ronvel.farztev.admin.enums.Continent;

public class CountryServiceTest extends BaseServiceTest {

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private ModelMapper mapper;

	@Before
	public void before() {
		countryDao.deleteAll();
		assertEquals(0L,countryDao.count());
	}

	@Test
	public void findCountry() {
		countryDao.save(createSwissCountry());
		assertEquals(1L,countryDao.count());
		Optional<Country> optionalCountry = countryService.findCountryById(1L);
		assertTrue(optionalCountry.isPresent());
		testSwissCountry(optionalCountry.get());
	}

    @Test
    public void findCountry_notFound() {
        Optional<Country> optionalCountry = countryService.findCountryById(1L);
        assertFalse(optionalCountry.isPresent());
    }
    
	@Test
	public void listCountries() {
		countryDao.save(createSwissCountry());
		assertEquals(1L,countryDao.count());
		List<ListCountry> countries = countryService.listCountries();
		testListCountries(countries);
	}
	
    @Test
    public void listCountries_empty() {
        List<ListCountry> countries = countryService.listCountries();
        assertNotNull(countries);
        assertTrue(countries.isEmpty());
    }
	
	@Test
	public void addCountry() {
		assertEquals(0L,countryDao.count());
		Country newCountry = mapper.map(createSwissCountry(),Country.class);
		Country country = countryService.addCountry(newCountry);
		testSwissCountry(country);
		assertEquals(1L,countryDao.count());
	}
	
	@Test
	public void updateCountry() {
		countryDao.save(createSwissCountry());
		assertEquals(1L,countryDao.count());
		Country updateCountry = createUpdateSwissCountry();
		countryService.updateCountry(1L,updateCountry);
		Optional<Country> optionalCountry = countryService.findCountryById(1L);
        assertTrue(optionalCountry.isPresent());
		testUpdatedSwissCountry(optionalCountry.get());
	}

	@Test
	public void deleteCountry() {
		countryDao.save(createSwissCountry());
		assertEquals(1L,countryDao.count());
		countryService.deleteCountry(1L);
		assertEquals(0L,countryDao.count());
	}
	
	public static void testSwissCountry(Country country) {
		assertNotNull(country);
		assertEquals(1L,country.getId().longValue());
		assertEquals("CH", country.getAbbreviation());
		assertEquals(new Date(1234567910L), country.getBeginning());
		assertEquals("The swiss conclusion", country.getConclusion());
		assertEquals(Continent.EUROPE.toString(), country.getContinent());
		assertEquals(new Date(1234567911L), country.getCreated());
		assertEquals("The swiss description", country.getDescription());
		assertEquals(new Date(1234567912L), country.getEnding());
		assertEquals("The swiss favorite", country.getFavorite());
		assertEquals("Switzerland", country.getName());
		assertTrue(country.getOnline());
		assertEquals("The swiss trip", country.getTrip());
		assertEquals(new Date(1234567913L), country.getUpdated());
		assertEquals("The swiss why", country.getWhy());
	}
	
	public static void testListCountries(List<ListCountry> countries) {
		assertNotNull(countries);
		assertEquals(1, countries.size());
		ListCountry country = countries.get(0);
		assertEquals(1L,country.getId().longValue());
		assertEquals("CH", country.getAbbreviation());
		assertEquals(new Date(1234567910L), country.getBeginning());
		assertEquals(Continent.EUROPE.toString(), country.getContinent());
		assertEquals(new Date(1234567911L), country.getCreated());
		assertEquals(new Date(1234567912L), country.getEnding());
		assertEquals("Switzerland", country.getName());
		assertTrue(country.getOnline());
		assertEquals(new Date(1234567913L), country.getUpdated());
	}

	public static CountryModel createSwissCountry() {
		CountryModel country = new CountryModel();
		country.setId(1L);
		country.setAbbreviation("CH");
		country.setBeginning(new Date(1234567910L));
		country.setConclusion("The swiss conclusion");
		country.setContinent(Continent.EUROPE.toString());
		country.setCreated(new Date(1234567911L));
		country.setDescription("The swiss description");
		country.setEnding(new Date(1234567912L));
		country.setFavorite("The swiss favorite");
		country.setName("Switzerland");
		country.setOnline(true);
		country.setTrip("The swiss trip");
		country.setUpdated(new Date(1234567913L));
		country.setWhy("The swiss why");
		return country;
	}
	public static Country createUpdateSwissCountry() {
		Country country = new Country();
		country.setId(2L);
		country.setAbbreviation("CH2");
		country.setBeginning(new Date(12345679102L));
		country.setConclusion("The swiss conclusion2");
		country.setContinent(Continent.AFRICA.toString());
		country.setCreated(new Date(12345679112L));
		country.setDescription("The swiss description2");
		country.setEnding(new Date(12345679122L));
		country.setFavorite("The swiss favorite2");
		country.setName("Switzerland2");
		country.setOnline(false);
		country.setTrip("The swiss trip2");
		country.setUpdated(new Date(12345679132L));
		country.setWhy("The swiss why2");
		return country;
	}
	public static void testUpdatedSwissCountry(Country country) {
		assertNotNull(country);
		assertEquals(1L,country.getId().longValue());
		assertEquals("CH2", country.getAbbreviation());
		assertEquals(new Date(12345679102L), country.getBeginning());
		assertEquals("The swiss conclusion2", country.getConclusion());
		assertEquals(Continent.AFRICA.toString(), country.getContinent());
		assertEquals(new Date(12345679112L), country.getCreated());
		assertEquals("The swiss description2", country.getDescription());
		assertEquals(new Date(12345679122L), country.getEnding());
		assertEquals("The swiss favorite2", country.getFavorite());
		assertEquals("Switzerland2", country.getName());
		assertFalse(country.getOnline());
		assertEquals("The swiss trip2", country.getTrip());
		assertEquals(new Date(12345679132L), country.getUpdated());
		assertEquals("The swiss why2", country.getWhy());
	}

}