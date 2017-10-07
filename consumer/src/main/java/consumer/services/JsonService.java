package consumer.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import consumer.domain.PhoneMessage;

@Service
public class JsonService {

	public String convertMessageToJson(PhoneMessage phoneMessage) throws Exception {				
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(phoneMessage);
	}
	
	public PhoneMessage getPhoneMessage(String phoneMessage) throws Exception {
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.readValue(phoneMessage, PhoneMessage.class);
	}	
}
