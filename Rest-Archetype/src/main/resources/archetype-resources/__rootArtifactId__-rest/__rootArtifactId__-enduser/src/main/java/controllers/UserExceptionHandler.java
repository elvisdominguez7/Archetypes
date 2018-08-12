#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ${package}.exception.InvalidInputException;
import ${package}.exception.InvoiceAllreadyExistException;
import ${package}.exception.InvoiceNotFoundException;
import ${package}.exception.UserNotFoundException;
import ${package}.exception.UserAllreadyExistException;

@RestControllerAdvice
public class UserExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserExceptionHandler.class);

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<String> handleInvalidInput(InvalidInputException ie) {
		LOGGER.info(ie.getMessage());

		return new ResponseEntity<String>(ie.getMessage(), HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(UserAllreadyExistException.class)
	public ResponseEntity<String> handleUserAllreadyExist(UserAllreadyExistException cae) {
		LOGGER.info(cae.getMessage());
		
		return new ResponseEntity<String>(cae.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException cnfe) {
		LOGGER.info(cnfe.getMessage());

		return new ResponseEntity<String>(cnfe.getMessage(), HttpStatus.NOT_FOUND);

	}
	@ExceptionHandler(InvoiceAllreadyExistException.class)
	public ResponseEntity<String> handleInvoiceAllreadyExist(InvoiceAllreadyExistException cae) {
		LOGGER.info(cae.getMessage());
		
		return new ResponseEntity<String>(cae.getMessage(), HttpStatus.CONFLICT);
	}

	@ExceptionHandler(InvoiceNotFoundException.class)
	public ResponseEntity<String> handleInvoiceNotFound(InvoiceNotFoundException infe) {
		LOGGER.info(infe.getMessage());

		return new ResponseEntity<String>(infe.getMessage(), HttpStatus.NOT_FOUND);

	}

}
