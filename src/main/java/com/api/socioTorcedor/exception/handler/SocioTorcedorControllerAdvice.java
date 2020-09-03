package com.api.socioTorcedor.exception.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.api.socioTorcedor.exception.ExceptionResponse;
import com.api.socioTorcedor.exception.SocioTorcedorException;

@RestControllerAdvice
public class SocioTorcedorControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(SocioTorcedorException.class)
	public final ResponseEntity<ExceptionResponse> socioTorcedorException(SocioTorcedorException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, ex.getHttpStatus());
	}
	
}
