package consumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import consumer.domain.PhoneMessage;

@Service
public class ConsumerService {

	@Autowired
	private StatisticsService statisticsService;

	public void processMessage(PhoneMessage phoneMessage) throws Exception  {
        
		if (phoneMessage.isValidNumber()) {		
			statisticsService.incrementCount(phoneMessage.getTelephoneNumber());
	        statisticsService.printCounts();
		} else {
			System.out.println("invalid phone number: " + phoneMessage.getTelephoneNumber() + 
				", id: " + phoneMessage.getId());
		}
	}
	
	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}	
}
