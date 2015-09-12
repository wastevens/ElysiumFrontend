package com.dstevens.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsernameUniquenessGuard {

	private UserDao userDao;

	@Autowired
	public UsernameUniquenessGuard(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void validateThatUsernameIsUnique(String username) throws UserInvalidException {
		if(userDao.findWithEmail(username) != null) {
			throw new UsernameAlreadyExistsException(username);
		}
	}
	
}
