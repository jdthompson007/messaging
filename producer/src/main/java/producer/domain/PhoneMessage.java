package producer.domain;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PhoneMessage {

	private Long id;
	private String telephoneNumber;

	public PhoneMessage() {	
	}

	public PhoneMessage(Long id, String telephoneNumber) {	
		this.id = id; 
		this.telephoneNumber = telephoneNumber;
	}

	public PhoneMessage(String telephoneNumber) {	
		this.id = getRandomId(); 
		this.telephoneNumber = telephoneNumber;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	
	@JsonIgnore
	private Long getRandomId() {
		Random random = new Random();		
		String s = "";
		for (int i = 0; i < 10; i++) {
			s = s + random.nextInt(10);
		}
		
		return new Long(s);		
	}	
}
