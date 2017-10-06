package producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import producer.domain.PhoneMessage;
import producer.services.JsonService;

@Component
public class Runner implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final ConfigurableApplicationContext context;

    private static List<String> dialCodes = new ArrayList<>();
    static {
    	dialCodes.add("+31");
    	dialCodes.add("+33");
    	dialCodes.add("+44");
    }
    
    @Autowired
    private JsonService jsonService;
    
    public Runner(RabbitTemplate rabbitTemplate, ConfigurableApplicationContext context) {
        this.rabbitTemplate = rabbitTemplate;
        this.context = context;
    }   
            
    @Override
    public void run(String... args) throws Exception {
        
    	List<PhoneMessage> phoneMessages = new ArrayList<>();
    	
//    	if (args.length > 0) {
//    		PhoneMessage phoneMessage = new PhoneMessage(args[0]);
//    		phoneMessages.add(phoneMessage);
//    	} else {
//    		phoneMessages = getMessages();
//    	}

    	phoneMessages = getMessages();
    	
    	for (PhoneMessage phoneMessage: phoneMessages) {
        	String json = jsonService.convertMessageToJson(phoneMessage);
        	System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(Application.queueName, json);
    	}
    	
        context.close();
    }
    
    // produces 40 random phone numbers with three different dial codes 
    private List<PhoneMessage> getMessages() {
    	
    	List<PhoneMessage> phoneMessages = new ArrayList<>();
    	
    	for (int i = 1; i <= 40; i++) {
    		
    		Random random = new Random();
    		int index = random.nextInt(3);
    		
    		phoneMessages.add(new PhoneMessage(dialCodes.get(index) + "1204666777"));
    	}
    	
    	return phoneMessages;
    }
}
