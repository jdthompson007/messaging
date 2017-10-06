package producer.domain;

import org.junit.Test;

public class PhoneMessageTest {

	@Test
	public void testMessageHasRandomId() {
		
		PhoneMessage message = new PhoneMessage("+44123456789");
		
		System.out.println(message.getId());		
	}
	
}
