package com.dstevens.user.guards;

import javax.persistence.NonUniqueResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;

import com.dstevens.user.User;
import com.dstevens.user.UserDao;

@Service
public class UsernameUniquenessGuard implements UserCreationGuard {

	private UserDao userDao;

	@Autowired
	public UsernameUniquenessGuard(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void validate(User user) throws UserInvalidException {
		try {
			if(userDao.findWithEmail(user.getEmail()) != null) {
				throw new UsernameAlreadyExistsException(user.getEmail());
			}
		} catch(NonUniqueResultException | IncorrectResultSizeDataAccessException e) {
			throw new UsernameAlreadyExistsException(user.getEmail());
		}
	}
	
}
