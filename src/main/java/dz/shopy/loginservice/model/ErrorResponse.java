package dz.shopy.loginservice.model;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse extends GenericResponse {

	private List<String> errors;

	public ErrorResponse(int code, String message, List<String> errors) {
		super(code, message);
		this.errors = errors;
	}
	
}
