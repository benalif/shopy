package dz.shopy.loginservice.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class LoginRequest {

	@NotNull
	@NotEmpty(message = "username should not be empty")
	private String username;
	
	@NotNull
	@NotEmpty(message = "password should not be empty")
	private String password;
}
