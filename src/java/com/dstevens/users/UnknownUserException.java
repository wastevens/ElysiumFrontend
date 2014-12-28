package com.dstevens.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason = "No such User")
public class UnknownUserException extends RuntimeException {

	private static final long serialVersionUID = 5103478191033944748L;

	public UnknownUserException(String message) {
		super(message);
	}

}
