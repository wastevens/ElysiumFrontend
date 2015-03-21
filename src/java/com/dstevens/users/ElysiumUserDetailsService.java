package com.dstevens.users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dstevens.config.AuthorizationToken;
import com.dstevens.suppliers.IdSupplier;

import static com.dstevens.collections.Maps.map;

@Service
public class ElysiumUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	private static final Map<AuthorizationToken, Authentication> authorizationTokens = map();
	
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
	
	public AuthorizationToken authorize(Authentication username) {
		long currentTimeMillis = System.currentTimeMillis();
		long duration = (24 * 60 * 60 * 1000);
		AuthorizationToken token = new AuthorizationToken(username.getName(), new IdSupplier().get().replace(":", "-"), String.valueOf(currentTimeMillis + duration));
		authorizationTokens.put(token, username);
		System.out.println(authorizationTokens);
		return token;
	}
	
	public boolean isUserAuthorized(AuthorizationToken token) {
		return (authorizationTokens.containsKey(token) &&
				Long.valueOf(token.expiration()) > System.currentTimeMillis());
	}
	
	public Authentication authenticationFor(AuthorizationToken token) {
		return authorizationTokens.get(token);
	}
}
