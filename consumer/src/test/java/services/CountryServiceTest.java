package services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import consumer.domain.Country;
import consumer.services.CountryService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=consumer.Application.class)
public class CountryServiceTest {

	@Autowired
	private CountryService countryService;
	
	@Test
	public void testGetCountries() throws Exception {
		
		// GIVEN the countries are in a JSON file
		// WHEN we read them into the domain objects
		List<Country> countries = countryService.getCountries();		
		System.out.println("countries: " + countries.size());	

		// THEN the UK should be present
		boolean found = false;
		for (Country country: countries) {
			if ("+44".equals(country.getDialCode())) {
				found = true;
			}
		}
 
		assertThat("The countries have failed to load from the JSON file", found, is(true));
	}	
}
