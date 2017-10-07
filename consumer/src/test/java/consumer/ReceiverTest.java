package consumer;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import consumer.domain.PhoneMessage;
import consumer.services.ConsumerService;
import consumer.services.JsonService;

public class ReceiverTest {

	@Mock
	private JsonService mockJsonService;
	@Mock
	private ConsumerService mockConsumerService;
	
	private Receiver receiver;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		receiver = new Receiver();
		
		receiver.setJsonService(mockJsonService);
		receiver.setConsumerService(mockConsumerService);
	}
	
	@Test
	public void testReceiveMessage() throws Exception {		
		// GIVEN a JSON message has been received
		String message = "{\"id\":123456789,\"telephoneNumber\":\"+441204666777\"}";
		PhoneMessage phoneMessage = new PhoneMessage();		
		
		when(mockJsonService.getPhoneMessage(message)).thenReturn(phoneMessage);
		
		// WHEN the message is processed by the receiver
		receiver.receiveMessage(message);
		
		// THEN the consumer service will process the domain object
		verify(mockConsumerService).processMessage(phoneMessage);		
	}
	
}
