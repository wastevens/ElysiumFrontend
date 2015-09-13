package com.dstevens.config;

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

import com.dstevens.suppliers.IdSupplier;
import com.dstevens.user.User;
import com.dstevens.user.UserDao;

import static com.dstevens.collections.Maps.map;

@Service
public class ElysiumUserDetailsService implements UserDetailsService {

	private final UserDao userDao;

	private static final Map<Authorization, Authentication> authorizations = map();
	
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
		if(authorizations.containsValue(authentication)) {
			return findAuthorizationFor(authentication);
		}
		long currentTimeMillis = System.currentTimeMillis();
		long duration = (24 * 60 * 60 * 1000);
		Authorization token = new Authorization(authentication.getName(), new IdSupplier().get().replace(":", "-"), String.valueOf(currentTimeMillis + duration));
		authorizations.put(token, authentication);
		return token;
	}

	private Authorization findAuthorizationFor(Authentication authentication) {
		Optional<Entry<Authorization, Authentication>> filter = authorizations.entrySet().stream().filter(predicate(authentication)).findFirst();
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
				return value.getName().equals(authentication.getName()) && value.getCredentials().equals(authentication.getCredentials());
			}
		};
	}
	
	public boolean isUserAuthorized(Authorization token) {
		if(!authorizations.containsKey(token)) {
			return false;
		}
		if(!(Long.valueOf(token.expiration()) > System.currentTimeMillis())) {
			authorizations.remove(token);
			return false;
		}
		return true;
	}
	
	public Authentication authenticationFor(Authorization token) {
		return authorizations.get(token);
	}

	public void removeAuthorizationFor(Authorization authorization) {
		authorizations.remove(authorization);
	}
}
