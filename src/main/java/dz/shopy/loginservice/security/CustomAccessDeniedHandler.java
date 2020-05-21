package dz.shopy.loginservice.security;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		this.writeResponse(response, SC_UNAUTHORIZED, "Forbidden");

	}

	private void writeResponse(HttpServletResponse httpResponse, int code, String message) throws IOException {
		httpResponse.setContentType("application/json");
		httpResponse.setStatus(code);
		httpResponse.getOutputStream().write(("{\"code\":" + code + ",").getBytes());
		httpResponse.getOutputStream().write(("\"message\":\"" + message + "\"}").getBytes());
		httpResponse.getOutputStream().flush();
	}

}
