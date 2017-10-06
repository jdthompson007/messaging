package consumer.services;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import consumer.domain.PhoneMessage;

@Service
public class JsonService {

	public PhoneMessage getPhoneMessage(String phoneMessage) throws Exception {
		ObjectMapper mapper = new ObjectMapper();		
		return mapper.readValue(phoneMessage, PhoneMessage.class);
	}	
}
