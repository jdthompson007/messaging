package services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import consumer.domain.PhoneMessage;
import consumer.services.JsonService;

public class JsonServiceTest {
	
	private JsonService jsonService;

	@Before
	public void setup() {
		jsonService = new JsonService();
	}
	
	@Test
	public void testGetPhoneMessage() throws Exception {
		// GIVEN we have an incoming JSON message
		Long id = 123456789L;
	    String phoneNumber = "+4401204666777";
		String message = "{ \"id\":" + id + ", \"telephoneNumber\": \"+4401204666777\" }";
		
		// WHEN the message is deserialized
		PhoneMessage phoneMessage = jsonService.getPhoneMessage(message);

		// THEN the domain object should have the correct values
		assertThat(phoneMessage.getId(), equalTo(id));		
		assertThat(phoneMessage.getTelephoneNumber(), equalTo(phoneNumber));		
	}	
}
