package consumer.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PhoneMessageTest {

	private PhoneMessage phoneMessage;
	
	@Before
	public void setup() {
		phoneMessage = new PhoneMessage();
	}
	
	@Test
	public void testValidPhoneNumber() {
		// GIVEN we have a valid phone number
		phoneMessage.setTelephoneNumber("+441204666777");
		
		// WHEN the number is validated
		// THEN the number is valid
		assertThat(phoneMessage.isValidNumber(), equalTo(true));		
	}

	@Test
	public void testInValidPhoneNumber() {
		// GIVEN we have a valid phone number
		phoneMessage.setTelephoneNumber("+0441204666777");
		
		// WHEN the number is validated
		// THEN the number is valid
		assertThat(phoneMessage.isValidNumber(), equalTo(false));		
	}
	
	@Test
	public void testInValidPhoneNumberNoPlus() {
		// GIVEN we have a valid phone number
		phoneMessage.setTelephoneNumber("441204666777");
		
		// WHEN the number is validated
		// THEN the number is valid
		assertThat(phoneMessage.isValidNumber(), equalTo(false));		
	}	
}
