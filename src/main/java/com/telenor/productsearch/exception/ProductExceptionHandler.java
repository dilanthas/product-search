package com.telenor.productsearch.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Exception handler for the product search
 */
@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		String errorMessage = ex.getMessage();
		Map<String, Object> extraInfo = new HashMap<>();

		if (ex instanceof MethodArgumentTypeMismatchException) {
			MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
			extraInfo.put(exception.getName(), ex.getValue());

		}
		logger.debug(errorMessage);
		return new ResponseEntity<>(buildErrorMessage(new Date(), errorMessage, extraInfo), HttpStatus.BAD_REQUEST);
	}

	private ErrorMessage buildErrorMessage(Date currentDate, String message, Map<String, Object> extraInfo) {
		return ErrorMessage.builder().timestamp(currentDate).errorMessage(message).additionalData(extraInfo).build();

	}
}
