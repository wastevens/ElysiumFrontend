package com.dstevens.user.guards;

public class UsernameRequiredException extends UserInvalidException {

	private static final long serialVersionUID = 7972591843364860389L;

	public UsernameRequiredException() {
		super("Users are required to have an email address!");
	}
	
}
