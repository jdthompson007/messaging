package services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import consumer.domain.PhoneMessage;
import consumer.services.ConsumerService;
import consumer.services.StatisticsService;

public class ConsumerServiceTest {

	@Mock
	private StatisticsService mockStatisticsService;
	
	private ConsumerService consumerService;
		
	@Before
	public void setup() {	
		MockitoAnnotations.initMocks(this);
		
		consumerService = new ConsumerService();		
		consumerService.setStatisticsService(mockStatisticsService);
	}

	@Test
	public void testProcessMessage() throws Exception {		
		// GIVEN we have a message
		PhoneMessage phoneMessage = new PhoneMessage();
		phoneMessage.setTelephoneNumber("+441204666777");
		
		// WHEN the message is processed
		consumerService.processMessage(phoneMessage);
		
		// THEN the count will be incremented
		verify(mockStatisticsService).incrementCount(phoneMessage.getTelephoneNumber());
		// AND the statistics will be printed to the console
		verify(mockStatisticsService).printCounts();
	}	
	
	@Test
	public void testProcessInvalidMessage() throws Exception {		
		// GIVEN we have an invalid phone number in the message
		PhoneMessage phoneMessage = new PhoneMessage("+0441204666777");
		
		// WHEN the message is processed
		consumerService.processMessage(phoneMessage);
		
		// THEN the count will not be incremented
		verify(mockStatisticsService, times(0)).incrementCount(phoneMessage.getTelephoneNumber());
		// AND the statistics will be not printed to the console
		verify(mockStatisticsService, times(0)).printCounts();
	}
}
