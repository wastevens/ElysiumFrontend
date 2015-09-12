package com.dstevens.user.guards;

public class UserInvalidException extends Exception {

	private static final long serialVersionUID = -6514499465578328846L;

	public UserInvalidException(String emailAddress) {
		super(emailAddress);
	}
	
}
