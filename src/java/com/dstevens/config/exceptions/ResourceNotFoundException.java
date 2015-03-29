package com.dstevens.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Resource not found")
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3332988206131661686L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
	
	public ResourceNotFoundException(Throwable e) {
		super(e);
	}
	
	public ResourceNotFoundException(String message, Throwable e) {
		super(message, e);
	}
	
}
