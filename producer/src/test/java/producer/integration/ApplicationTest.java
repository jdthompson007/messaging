package producer.integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import producer.Application;
import producer.Runner;
import producer.domain.PhoneMessage;
import producer.services.JsonService;
import producer.services.ProducerService;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes=producer.Application.class)
public class ApplicationTest {
	
	private static final long TIMEOUT = 5000; // 5 seconds

	@Autowired
	private ProducerService producerService;	
	@Autowired
	private JsonService jsonService;
	
    @MockBean
    private Runner runner;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*
     * This is an integration test that requires RabbitMQ to be running
     * the consumer application should NOT be running when this test is run as it
     * may cause it to break. Also, there should be no existing messages in the queue
     * This test queues the phone message and takes it off the queue and checks the phone number
     */    
    @Test
    public void testMessageIsEnqueuedCorrectly() throws Exception {
    	// GIVEN we have a phone number to send
    	String phoneNumber = "+441204777888";
        
    	// WHEN the message is sent on the queue
    	producerService.sendMessage(phoneNumber);
        
    	// THEN the message can be taken off the queue and read back to ensure it was sent
        String json = (String) rabbitTemplate.receiveAndConvert(Application.queueName, TIMEOUT);        
        PhoneMessage phoneMessage = jsonService.getPhoneMessage(json);
        // AND it has the correct phone number
        assertThat(phoneMessage.getTelephoneNumber(), equalTo(phoneNumber));        
    }
}
