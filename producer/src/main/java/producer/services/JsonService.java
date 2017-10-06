package producer.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import producer.domain.PhoneMessage;

@Service
public class JsonService {

	public String convertMessageToJson(PhoneMessage phoneMessage) throws Exception {				
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(phoneMessage);
	}	
}

