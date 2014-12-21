package com.dstevens.users;

public class UnknownUserException extends RuntimeException {

	private static final long serialVersionUID = 5103478191033944748L;

	public UnknownUserException(String message) {
		super(message);
	}

}
