package dz.shopy.loginservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dz.shopy.loginservice.entity.User;
import dz.shopy.loginservice.payload.LoginRequest;
import dz.shopy.loginservice.service.LoginService;
import dz.shopy.loginservice.service.UserService;

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	@Autowired
	UserService userService;

	@PostMapping("/auth/sign-up")
	public ResponseEntity<?> signUp(@RequestBody @Valid User user) {
		return userService.addUser(user);
	}

	@PostMapping("/auth/generate-token")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid LoginRequest authenticationRequest)
			throws Exception {
		return loginService.createAuthenticationToken(authenticationRequest);
	}

	// roles in db table table must be saved as ROLE_roleName
	@PreAuthorize("hasRole('CUSTOMER') or hasRole('ROLE_ADMIN')")
	@PostMapping("/api/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		return loginService.logout(request);
	}
}
