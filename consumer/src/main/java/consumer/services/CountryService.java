package consumer.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import consumer.domain.Country;

@Service
public class CountryService {

	@Autowired
	private ResourceLoader resourceLoader;
	
	private List<Country> countries;
	
	public List<Country> getCountries() throws Exception {		
		
		if (countries == null) {		
			ObjectMapper mapper = new ObjectMapper();
			
			Resource resource = resourceLoader.getResource("classpath:countrycodes.json");
			InputStream jsonStream = resource.getInputStream();
					
			countries =  mapper.readValue(jsonStream, new TypeReference<List<Country>>(){});
		}
		
		return countries;
	}
}
