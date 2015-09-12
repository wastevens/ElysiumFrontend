package com.dstevens.user.guards;

public class UsernameAlreadyExistsException extends UserInvalidException {

	private static final long serialVersionUID = 5691631989649850157L;

	public UsernameAlreadyExistsException(String emailAddress) {
		super("User with email address " + emailAddress + " already exists!");
	}
	
}
