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
	
	public AuthorizationToken authorize(Authentication authentication) {
		if(authorizationTokens.containsValue(authentication)) {
			return findAuthorizationFor(authentication);
		}
		long currentTimeMillis = System.currentTimeMillis();
		long duration = (24 * 60 * 60 * 1000);
		AuthorizationToken token = new AuthorizationToken(authentication.getName(), new IdSupplier().get().replace(":", "-"), String.valueOf(currentTimeMillis + duration));
		authorizationTokens.put(token, authentication);
		System.out.println(authorizationTokens);
		return token;
	}

	private AuthorizationToken findAuthorizationFor(Authentication authentication) {
		Optional<Entry<AuthorizationToken, Authentication>> filter = authorizationTokens.entrySet().stream().findFirst().filter(predicate(authentication));
		if(filter.isPresent()) {
			return filter.get().getKey();
		}
		return null;
	}

	private Predicate<Entry<AuthorizationToken, Authentication>> predicate(final Authentication authentication) {
		return new Predicate<Map.Entry<AuthorizationToken,Authentication>>() {

			@Override
			public boolean test(Entry<AuthorizationToken, Authentication> t) {
				Authentication value = t.getValue();
				return value.getName().equals(authentication.getName()) && 
					   value.getCredentials().equals(authentication.getCredentials());
			}
		};
	}
	
	public boolean isUserAuthorized(AuthorizationToken token) {
		if(!authorizationTokens.containsKey(token)) {
			return false;
		}
		if(!(Long.valueOf(token.expiration()) > System.currentTimeMillis())) {
			authorizationTokens.remove(token);
			return false;
		}
		return true;
	}
	
	public Authentication authenticationFor(AuthorizationToken token) {
		return authorizationTokens.get(token);
	}

	public void removeAuthorizationFor(AuthorizationToken authorization) {
		authorizationTokens.remove(authorization);
	}
}
