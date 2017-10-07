package consumer.integration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import consumer.Application;
import consumer.Receiver;
import consumer.domain.PhoneMessage;
import consumer.services.JsonService;
import consumer.services.StatisticsService;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes=consumer.Application.class)
public class ApplicationTest {

	@Autowired
	private JsonService jsonService;	
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private StatisticsService statisticsService;    
    @Autowired
    private Receiver receiver;

    /*
     * This tests that a phone message on the queue in JSON format will be
     * dequeued correctly and the count will be incremented correctly for the country code
     * 
     * NB Do not run the producer or leave messages in the queue or the test will be affected  
     */
    @Test
    public void testMessageIsDequeuedCorrectly() throws Exception {
    	// GIVEN we have a phone message 
    	PhoneMessage phoneMessage = new PhoneMessage("+451204111222");
    	String json = jsonService.convertMessageToJson(phoneMessage);    	        
    	
    	// WHEN the message is added to the queue
    	rabbitTemplate.convertAndSend(Application.queueName, json);                
        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        
        // THEN it will be dequeued by the receiver and the country code count will be correct 
        assertThat(statisticsService.getCount("+45"), equalTo(1));
    }
}
