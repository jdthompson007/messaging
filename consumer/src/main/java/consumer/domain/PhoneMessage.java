package consumer.domain;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PhoneMessage {
	// must begin with a plus symbol followed by 1 to 9, then 1 to 14 digits (0-9)
	private static final String VALID_PHONE_NUMBER_REGEX = "\\+[1-9]\\d{1,14}";
	
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
	public boolean isValidNumber() {
		return telephoneNumber.matches(VALID_PHONE_NUMBER_REGEX);
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
