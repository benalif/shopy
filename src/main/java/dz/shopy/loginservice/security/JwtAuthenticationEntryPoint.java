package dz.shopy.loginservice.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"sorry you are not
		// authorized to access to this resource!");

		this.writeResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

	}

	private void writeResponse(HttpServletResponse httpResponse, int code, String message) throws IOException {
		httpResponse.setContentType("application/json");
		httpResponse.setStatus(code);
		httpResponse.getOutputStream().write(("{\"code\":" + code + ",").getBytes());
		httpResponse.getOutputStream().write(("\"message\":\"" + message + "\"}").getBytes());
		httpResponse.getOutputStream().flush();
	}
}
