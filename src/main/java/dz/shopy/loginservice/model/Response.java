package dz.shopy.loginservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Response<T> {

	private int code;
	private String message;
	private T extraInfo;

	public Response(int code, String message, T extraInfo) {
		super();
		this.code = code;
		this.message = message;
		this.extraInfo = extraInfo;
	}

	public Response(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	

}
