package producer.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class PhoneMessageTest {

	@Test
	public void testMessageHasRandomId() {
		// GIVEN we have a phone number
		// WHEN a message is created
		PhoneMessage message = new PhoneMessage("+44123456789");
		// THEN it will have an id value
		assertThat(message.getId() > 0, equalTo(true));		
	}
}
