package services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import consumer.domain.Country;
import consumer.services.CountryService;
import consumer.services.StatisticsService;

public class StatisticsServiceTest {

	@Mock
	private CountryService mockCountryService;
	
	private StatisticsService statisticsService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);		
		
		statisticsService = new StatisticsService();		
		statisticsService.setCountryService(mockCountryService);
	}	
	
	@Test
	public void testIncrementCount() throws Exception {
		// GIVEN we have the UK code
		Country country = new Country();
		country.setDialCode("+44");
		
		List<Country> countries = new ArrayList<>();
		countries.add(country);
		
		when(mockCountryService.getCountries()).thenReturn(countries);		
		
		// WHEN the count is incremented for a number
		statisticsService.incrementCount("+4401204666777");
		
		// THEN the count will be correct
		assertThat(statisticsService.getCount("+44"), equalTo(1L));
		statisticsService.incrementCount("+4401204666888");
		assertThat(statisticsService.getCount("+44"), equalTo(2L));		
	}
}
