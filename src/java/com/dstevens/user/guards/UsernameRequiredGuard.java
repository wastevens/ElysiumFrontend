package com.dstevens.user.guards;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dstevens.user.User;

@Service
public class UsernameRequiredGuard implements UserCreationGuard {

	@Override
	public void validate(User user) throws UserInvalidException {
		if(StringUtils.isBlank(user.getEmail())) {
			throw new UsernameRequiredException();
		}
	}
	
}
