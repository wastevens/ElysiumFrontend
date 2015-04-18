package com.dstevens.config.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Bad request")
public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = -2784575011780728266L;

	public BadRequestException(String message) {
		super(message);
	}
	
	public BadRequestException(Throwable e) {
		super(e);
	}
	
	public BadRequestException(String message, Throwable e) {
		super(message, e);
	}
	
}
