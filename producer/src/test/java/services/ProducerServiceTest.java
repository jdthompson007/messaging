package services;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import producer.Application;
import producer.domain.PhoneMessage;
import producer.services.JsonService;
import producer.services.ProducerService;

public class ProducerServiceTest {

	@Mock
	private JsonService mockJsonService;
	@Mock
	private RabbitTemplate mockRabbitTemplate;
	
	private ProducerService producerService;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		producerService = new ProducerService();
		
		producerService.setJsonService(mockJsonService);
		producerService.setRabbitTemplate(mockRabbitTemplate);
	}
	
	@Test
	public void testSendMessage() throws Exception {
		// GIVEN we have a phone number to send		
		String phoneNumber = "+441204666777";
		String json = "{}";
		
		when(mockJsonService.convertMessageToJson(isA(PhoneMessage.class))).thenReturn(json);
		
		// WHEN the message is sent
		producerService.sendMessage(phoneNumber);
		
		// THEN the message will have been sent correctly
		verify(mockRabbitTemplate).convertAndSend(Application.queueName, json);
	}
	
	@Test
	public void testSendMessages() throws Exception {
		// GIVEN we have phone numbers to send		
		
		// WHEN the messages are sent
		producerService.sendMessages();
		
		// THEN the messages will have been sent correctly
		verify(mockRabbitTemplate, times(ProducerService.NUMBER_OF_MESSAGES)).convertAndSend(eq(Application.queueName), anyString());
	}
}
