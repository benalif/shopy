package dz.shopy.loginservice.service;

import static dz.shopy.loginservice.model.GenericResponse.ALREADY_EXIST_USER;
import static dz.shopy.loginservice.model.GenericResponse.ERROR_MESSAGE;
import static dz.shopy.loginservice.model.GenericResponse.SUCCESS_MESSAGE;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import dz.shopy.loginservice.entity.User;
import dz.shopy.loginservice.model.GenericResponse;
import dz.shopy.loginservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder encoder;

	public ResponseEntity<?> addUser(final User user) {

		if (!Objects.isNull(userRepository.findByUsername(user.getUsername()))) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(0, ALREADY_EXIST_USER), HttpStatus.OK);
		}

		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreationDate(new Date());
		user.setEnabled(true);

		if (Objects.isNull(userRepository.save(user))) {
			return new ResponseEntity<GenericResponse>(new GenericResponse(0, ERROR_MESSAGE), HttpStatus.OK);
		} else {
			return new ResponseEntity<GenericResponse>(new GenericResponse(0, SUCCESS_MESSAGE), HttpStatus.OK);
		}
	}

}
