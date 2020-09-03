package com.api.socioTorcedor.exception;

import org.springframework.http.HttpStatus;

public class SocioTorcedorException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	private Object obj;

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public Object getObj() {
		return obj;
	}

	public SocioTorcedorException(String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public SocioTorcedorException(String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
	}
	
	public SocioTorcedorException(String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
	}

	public SocioTorcedorException(Object o, String msg) {
		super(msg);
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.obj = o;
	}

	public SocioTorcedorException(Object o, String msg, HttpStatus status) {
		super(msg);
		this.httpStatus = status;
		this.obj = o;
	}

	public SocioTorcedorException(Object o, String msg, Throwable ex, HttpStatus status) {
		super(msg, ex);
		this.httpStatus = status;
		this.obj = o;
	}

}
