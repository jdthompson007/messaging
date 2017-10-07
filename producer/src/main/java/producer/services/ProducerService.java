package producer.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import producer.Application;
import producer.domain.PhoneMessage;

@Service
public class ProducerService {

	public static final int NUMBER_OF_MESSAGES = 40;
	
	@Autowired
    private RabbitTemplate rabbitTemplate;	
	@Autowired
	private JsonService jsonService;
	
	private static Logger logger = Logger.getLogger(ProducerService.class);
	
    private static List<String> dialCodes = new ArrayList<>();
    static {
    	dialCodes.add("+31");
    	dialCodes.add("+33");
    	dialCodes.add("+44");
    }
	
	public void sendMessage(String phoneNumber) throws Exception  {
		sendMessage(new PhoneMessage(phoneNumber));
	}
	
	// will send 40 messages with 3 random dial codes
	public void sendMessages() throws Exception {
		
		List<PhoneMessage> phoneMessages = getMessages();
    	
    	for (PhoneMessage phoneMessage: phoneMessages) {
    		sendMessage(phoneMessage);
    	}
	}

	private void sendMessage(PhoneMessage phoneMessage) throws Exception {
    	String json = jsonService.convertMessageToJson(phoneMessage);
    	
    	System.out.println("Sending message:  " + json);
    	logger.debug("sending message:" + json);
    	
        rabbitTemplate.convertAndSend(Application.queueName, json);	
	}
	
    // produces 40 random phone numbers with three different dial codes 
    private List<PhoneMessage> getMessages() {
    	
    	List<PhoneMessage> phoneMessages = new ArrayList<>();
    	
    	for (int i = 1; i <= NUMBER_OF_MESSAGES; i++) {
    		
    		Random random = new Random();
    		int index = random.nextInt(3);
    		
    		phoneMessages.add(new PhoneMessage(dialCodes.get(index) + "1204666777"));
    	}
    	
    	return phoneMessages;
    }
    
	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void setJsonService(JsonService jsonService) {
		this.jsonService = jsonService;
	}    
}
