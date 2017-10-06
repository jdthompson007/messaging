package consumer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

	private String name;
	private String dialCode;
	private String code;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@JsonProperty("dial_code")
	public String getDialCode() {
		return dialCode;
	}
	
	public void setDialCode(String dialCode) {
		this.dialCode = dialCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
