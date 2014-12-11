package com.dstevens.config;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dstevens.users.Role;
import com.dstevens.users.User;
import com.dstevens.users.UserDao;

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
		return new ElysiumUserDetails(user);
	}
	
	private static class ElysiumUserDetails implements UserDetails {

		private static final long serialVersionUID = -1323621777446828404L;
		private final User user;

		public ElysiumUserDetails(User user) {
			this.user = user;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.user.getRoles().stream().map((Role r) -> new SimpleGrantedAuthority("ROLE_" + r.name())).collect(Collectors.toSet());
		}

		@Override
		public String getPassword() {
			return "password";
		}

		@Override
		public String getUsername() {
			return this.user.getName();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}

}
