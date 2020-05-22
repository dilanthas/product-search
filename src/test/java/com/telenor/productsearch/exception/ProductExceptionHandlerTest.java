package com.telenor.productsearch.exception;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

public class ProductExceptionHandlerTest {

	@InjectMocks
	private ProductExceptionHandler exceptionHandler;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldHandleInvalidMethodArguments() {

		// Given
		MethodArgumentTypeMismatchException exception = Mockito.mock(MethodArgumentTypeMismatchException.class);
		HttpHeaders headers = Mockito.mock(HttpHeaders.class);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		WebRequest request = Mockito.mock(WebRequest.class);

		// When
		ResponseEntity<Object> responseEntity = exceptionHandler.handleTypeMismatch(exception, headers, status, request);

		// Then
		Assert.assertTrue(responseEntity.getBody() instanceof ErrorMessage);
		Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.BAD_REQUEST);
	}

	@Test
	public void shouldIncludeExtraInformation() {

		// Given
		MethodArgumentTypeMismatchException exception = Mockito.mock(MethodArgumentTypeMismatchException.class);
		Mockito.when(exception.getName()).thenReturn("min_price");
		Mockito.when(exception.getValue()).thenReturn("abc");

		HttpHeaders headers = Mockito.mock(HttpHeaders.class);
		HttpStatus status = HttpStatus.BAD_REQUEST;
		WebRequest request = Mockito.mock(WebRequest.class);

		// When
		ResponseEntity<Object> responseEntity = exceptionHandler.handleTypeMismatch(exception, headers, status, request);

		// Then
		Assert.assertTrue(responseEntity.getBody() instanceof ErrorMessage);
		ErrorMessage response = (ErrorMessage) responseEntity.getBody();
		Map<String, Object> data = response.getAdditionalData();
		Assertions.assertEquals(data.get("min_price"), "abc");
	}
}
