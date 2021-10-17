package breakfastcontrolserver.web.exceptionhandler;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import breakfastcontrolserver.domain.exceptions.CpfAlreadeyRegisteredException;
import breakfastcontrolserver.domain.exceptions.ErrorSavingEntityException;
import breakfastcontrolserver.domain.exceptions.FoodAlreadyRegisteredException;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(FoodAlreadyRegisteredException.class)
	public ResponseEntity<?> handleEntityNotFoundException(FoodAlreadyRegisteredException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem body = createProblem(status.value(), e.getMessage());
		
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problem body = createProblem(status.value(), e.getMessage());
		
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(CpfAlreadeyRegisteredException.class)
	public ResponseEntity<?> handleCpfAlreadeyRegisteredException(CpfAlreadeyRegisteredException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem body = createProblem(status.value(), e.getMessage());
		
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ErrorSavingEntityException.class)
	public ResponseEntity<?> handleErrorSavingEntityException(ErrorSavingEntityException e, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problem body = createProblem(status.value(), e.getMessage());
		
		return handleExceptionInternal(e, body, new HttpHeaders(), status, request);
	}
	
	private Problem createProblem(Integer status, String message) {
		return new Problem(status, message);
	}
}
