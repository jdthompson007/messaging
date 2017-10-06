package producer.domain;

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
		this.id = System.currentTimeMillis(); 
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
}
