package dz.shopy.loginservice.service;

import static dz.shopy.loginservice.helper.ConstantHelper.AUTHORIZATION;
import static dz.shopy.loginservice.helper.ConstantHelper.BEARER_PREFIX;
import static dz.shopy.loginservice.helper.ConstantHelper.TOKEN_PREFIX;
import static dz.shopy.loginservice.helper.ConstantHelper.UUID;
import static dz.shopy.loginservice.model.GenericResponse.SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.OK;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import dz.shopy.loginservice.helper.Helper;
import dz.shopy.loginservice.model.GenericResponse;
import dz.shopy.loginservice.model.Response;
import dz.shopy.loginservice.payload.LoginRequest;
import dz.shopy.loginservice.repository.UserRepository;
import dz.shopy.loginservice.security.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Service
public class LoginService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenService jwtTokenService;

	@Autowired
	UserRepository userRepository;

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {

		Authentication auth = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		SecurityContextHolder.getContext().setAuthentication(auth);
		String token = jwtTokenService.generateToken(auth);

		return new ResponseEntity<Response<?>>(new Response<String>(0, SUCCESS_MESSAGE, token), OK);
		// return new ResponseEntity<LoginResponse>(new LoginResponse(0,
		// SUCCESS_MESSAGE, token), HttpStatus.OK);
	}

	public ResponseEntity<?> logout(HttpServletRequest request) {
		String authorization = request.getHeader(AUTHORIZATION).replace(BEARER_PREFIX, "");

		Jws<Claims> claim = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization);
		String uuid = claim.getBody().get(UUID).toString();

		redisTemplate.opsForHash().put(TOKEN_PREFIX + uuid, uuid, authorization);
		redisTemplate.expireAt(TOKEN_PREFIX + uuid, Helper.addDays(7));

		return new ResponseEntity<GenericResponse>(new GenericResponse(0, SUCCESS_MESSAGE), OK);
	}

	private Authentication authenticate(String username, String password) throws Exception {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
