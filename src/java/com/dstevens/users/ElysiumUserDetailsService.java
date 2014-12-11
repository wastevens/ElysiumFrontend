package com.dstevens.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ElysiumUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	@Autowired
	public ElysiumUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findWithName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user named " + username);
		}
		return user;
	}
}
