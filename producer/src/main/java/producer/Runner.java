package producer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import producer.services.ProducerService;

@Component
public class Runner implements CommandLineRunner {

	private static Logger logger = Logger.getLogger(Runner.class);
	
    private final ConfigurableApplicationContext context;
       
    @Autowired 
    private ProducerService producerService;

	public Runner(ConfigurableApplicationContext context) {
        this.context = context;
    }   
            
    @Override
    public void run(String... args) {
    	
    	try {
        	printParameters(args);
    		
    		if (args.length > 1) {
	    		// phone number passed on the command line
	    		producerService.sendMessage(args[1]);   		
	    	} else {
	    		// no numbers on command line send random messages
	    		producerService.sendMessages();
	    	}
    	} catch (Throwable t) {
    		System.out.println("Error: " + t.getMessage());
	    	logger.error("Error: " + t.getMessage(), t);
    		
	    } finally {
	    	if (context != null) {
	    		context.close();	
	    	}
	    }
    }
    
    private void printParameters(String[] args) {    	
    	for (int i = 0; i < args.length; i++) {
    		System.out.println("parameter:" + i + ", value: " + args[i]);
    	}    	
    }
    
    public void setProducerService(ProducerService producerService) {
		this.producerService = producerService;
	}    
}
