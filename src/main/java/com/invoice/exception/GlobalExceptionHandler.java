package com.invoice.exception;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RecordNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleRecordNotFoundException(RecordNotFoundException re){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), re.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserAlreadyExistException.class)
	public ResponseEntity<ErrorDetails> handleUserAlreadyExistException(UserAlreadyExistException ue){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ue.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = InvoiceAmountException.class)
	public ResponseEntity<ErrorDetails> handleInvoiceAmountException(InvoiceAmountException ue){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ue.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = BlankNameException.class)
	public ResponseEntity<ErrorDetails> handleBlankNameException(BlankNameException ue){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ue.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException ue){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ue.getMessage());
		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorDetails> handleHttpMessageNotReadableException(HttpMessageNotReadableException ue){
		ErrorDetails details = new ErrorDetails(LocalDate.now(), ue.getMessage());
		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}
	
	
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
		Map<String,String> errors = new HashMap<>();
		 ex. getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError)error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
