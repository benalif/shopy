package dz.shopy.loginservice.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String BAD_CREDENTIALS = "bad credentials";

	private String message;

	public InvalidCredentialsException(String message) {
		super();
		this.message = message;
	}
}
