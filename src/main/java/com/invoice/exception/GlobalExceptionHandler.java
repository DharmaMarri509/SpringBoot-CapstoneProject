package com.invoice.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	
}
