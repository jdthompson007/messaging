package consumer.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consumer.domain.Country;

@Service
public class StatisticsService {

	@Autowired
	private CountryService countryService; 

	private Map<String, Long> countMap = new HashMap<>();
	
	public void incrementCount(String phoneNumber) throws Exception {
		
		List<Country> countries = countryService.getCountries();
		
		for (Country country: countries) {	
			String dialCode = country.getDialCode();
			if (phoneNumber.startsWith(dialCode)) {
				
				if (countMap.containsKey(dialCode)) {
					long count = countMap.get(dialCode) + 1;
					countMap.put(dialCode, count);
				} else {
					countMap.put(dialCode, 1L);
				}
				
				break;
			}
		}
	}
	
	public Long getCount(String dialCode) {
		return  countMap.get(dialCode);
	}
	
	public void printCounts() {
		System.out.println("");
		System.out.println("Country counts");
		System.out.println("==============");
		for (String dialCode: countMap.keySet()) {
			System.out.println(dialCode + "\t" + countMap.get(dialCode));
		}				
	}
	
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}	
}
