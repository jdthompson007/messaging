package producer;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ConfigurableApplicationContext;

import producer.services.ProducerService;

public class RunnerTest {

	@Mock
	private ConfigurableApplicationContext mockContext;	
	@Mock
	private ProducerService mockProducerService;
	
	private Runner runner;
		
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		runner = new Runner(mockContext);
		runner.setProducerService(mockProducerService);
	}
	
	@Test
	public void testRun() throws Exception {
		// GIVEN we launch the program with no command line arguments		
		// WHEN it runs
		runner.run();
		
		// THEN several messages will be sent
		verify(mockProducerService).sendMessages();
		verify(mockContext).close();
	}
	
	@Test
	public void testRunSingleMessage() throws Exception {
		// GIVEN we launch the program with one phone number to send		
		// WHEN it runs
		String phoneNumber = "+441204666777";
		runner.run("spring.rabbitmq.host=localhost", phoneNumber);
		
		// THEN one message will be sent
		verify(mockProducerService).sendMessage(phoneNumber);
	}
}
