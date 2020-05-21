package dz.shopy.loginservice.exception;

import static dz.shopy.loginservice.model.GenericResponse.ERROR_MESSAGE;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dz.shopy.loginservice.model.ErrorResponse;
import dz.shopy.loginservice.model.GenericResponse;

@ControllerAdvice
public class ExceptionAdvicer extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		final List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(new ErrorResponse(1, ERROR_MESSAGE, errors),
				BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> constraintViolationException(ConstraintViolationException ex) {

		return new ResponseEntity<>(new GenericResponse(1, ex.getMessage()), BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<?> badCredentialsException(InvalidCredentialsException ex) {
		return new ResponseEntity<>(new GenericResponse(1, ex.getMessage()), UNAUTHORIZED);
	}

}
