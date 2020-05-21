package dz.shopy.loginservice.model;

import lombok.Data;

@Data
public class LoginResponse extends GenericResponse {

	private String token;

	public LoginResponse(int code, String message, String token) {
		super(code, message);
		this.token = token;
	}

}
