package dz.shopy.loginservice.model;

import lombok.Data;

@Data
public class GenericResponse {

	public final static String SUCCESS_MESSAGE = "success";
	public final static String ERROR_MESSAGE = "error";
	public final static String ALREADY_EXIST_USER = "User already exists";

	private int code;
	private String message;

	public GenericResponse(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
