package services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import producer.domain.PhoneMessage;
import producer.services.JsonService;

public class JsonServiceTest {

	private JsonService jsonService;
	
	@Before
	public void setup() {
		jsonService = new JsonService();
	}
	
	@Test
	public void testConvertMessageToJson() throws Exception {
		// GIVEN we have a phone message domain instance
		long id = 123456789;
		String phoneNumber = "+441204666777";
		PhoneMessage phoneMessage = new PhoneMessage(id, phoneNumber);

		// WHEN the message is converted to JSON
		String json = jsonService.convertMessageToJson(phoneMessage);
		
		// THEN the json will be correct
		assertThat(json, equalTo("{\"id\":"+ id + ",\"telephoneNumber\":\"" + phoneNumber + "\"}"));
	}	
}
