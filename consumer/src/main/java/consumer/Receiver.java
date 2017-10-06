package consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import consumer.domain.PhoneMessage;
import consumer.services.ConsumerService;
import consumer.services.JsonService;

@Component
public class Receiver {

	private static Logger logger = Logger.getLogger(Receiver.class);	
	
	@Autowired
	private ConsumerService consumerService;
	@Autowired	
	private JsonService jsonService;
	
    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        
    	try {
    		System.out.println("Received <" + message + ">");
    		PhoneMessage phoneMessage = jsonService.getPhoneMessage(message);	        
    		consumerService.processMessage(phoneMessage);
    		
    	} catch (Exception e) {
    		System.out.println("Error: " + e.getMessage());
    		logger.error("error in consumer: " + e.getMessage(), e);
    	}
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
