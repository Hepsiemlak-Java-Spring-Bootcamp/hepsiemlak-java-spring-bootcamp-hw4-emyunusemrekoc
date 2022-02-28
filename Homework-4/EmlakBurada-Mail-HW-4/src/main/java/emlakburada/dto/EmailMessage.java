package main.java.emlakburada.dto;

import lombok.Data;

@Data
public class EmailMessage {

	private String email;



	public EmailMessage(String email) {
		this.email = email;
	}


//	@Override
//	public String toString(
//
//	) {
//		return "EmailMessage [email=" + email + "]";
//	}

}
