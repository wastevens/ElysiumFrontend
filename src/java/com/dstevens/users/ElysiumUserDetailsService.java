package com.dstevens.users;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dstevens.config.Authorization;
import com.dstevens.suppliers.IdSupplier;

import static com.dstevens.collections.Maps.map;

@Service
public class ElysiumUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	private static final Map<Authorization, Authentication> authorizationTokens = map();
	
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
	
	public Authorization authorize(Authentication authentication) {
		if(authorizationTokens.containsValue(authentication)) {
			return findAuthorizationFor(authentication);
		}
		long currentTimeMillis = System.currentTimeMillis();
		long duration = (24 * 60 * 60 * 1000);
		Authorization token = new Authorization(authentication.getName(), new IdSupplier().get().replace(":", "-"), String.valueOf(currentTimeMillis + duration));
		authorizationTokens.put(token, authentication);
		System.out.println(authorizationTokens);
		return token;
	}

	private Authorization findAuthorizationFor(Authentication authentication) {
		Optional<Entry<Authorization, Authentication>> filter = authorizationTokens.entrySet().stream().findFirst().filter(predicate(authentication));
		if(filter.isPresent()) {
			return filter.get().getKey();
		}
		return null;
	}

	private Predicate<Entry<Authorization, Authentication>> predicate(final Authentication authentication) {
		return new Predicate<Map.Entry<Authorization,Authentication>>() {

			@Override
			public boolean test(Entry<Authorization, Authentication> t) {
				Authentication value = t.getValue();
				return value.getName().equals(authentication.getName()) && 
					   value.getCredentials().equals(authentication.getCredentials());
			}
		};
	}
	
	public boolean isUserAuthorized(Authorization token) {
		if(!authorizationTokens.containsKey(token)) {
			return false;
		}
		if(!(Long.valueOf(token.expiration()) > System.currentTimeMillis())) {
			authorizationTokens.remove(token);
			return false;
		}
		return true;
	}
	
	public Authentication authenticationFor(Authorization token) {
		return authorizationTokens.get(token);
	}

	public void removeAuthorizationFor(Authorization authorization) {
		authorizationTokens.remove(authorization);
	}
}
