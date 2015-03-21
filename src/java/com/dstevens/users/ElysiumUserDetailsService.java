package com.dstevens.users;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dstevens.suppliers.IdSupplier;

@Service
public class ElysiumUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	private static final Map<String, String> authorizedUsers = new HashMap<String, String>();
	
	@Autowired
	public ElysiumUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findWithEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user with email " + email);
		}
		return user;
	}
	
	public String authorize(String username) {
		long currentTimeMillis = System.currentTimeMillis();
		long duration = (24 * 60 * 60 * 1000);
		String token = username + ":" + new IdSupplier().get().replace(":", "-") + ":" + (currentTimeMillis + duration);
		authorizedUsers.put(username, token);
		System.out.println(authorizedUsers);
		return token;
	}
	
	public boolean isUserAuthorized(String username, String actualToken) {
		String expectedToken = authorizedUsers.get(username);
		String[] expectedTokens = expectedToken.split(":");
		String expectedUsername = expectedTokens[0];
		String expectedKey = expectedTokens[1];
		String expectedExpiration = expectedTokens[2];
		
		String[] actualTokens = actualToken.split(":");
		String actualUsername = actualTokens[0];
		String actualKey = actualTokens[1];
		String actualExpiration = actualTokens[2];
		
		if(!expectedUsername.equals(actualUsername) || !expectedKey.equals(actualKey) || !expectedExpiration.equals(actualExpiration)) {
			return false;
		}
		if(Long.valueOf(actualExpiration) < System.currentTimeMillis()) {
			return false;
		}
		
		return true;
	}
}
