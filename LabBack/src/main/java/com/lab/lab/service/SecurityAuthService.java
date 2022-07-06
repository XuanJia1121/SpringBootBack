package com.lab.lab.service;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lab.lab.domain.UserDomain;
import com.lab.lab.enums.ResponseEnum;
 
@Service
public class SecurityAuthService implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isNotBlank(username)) {
			UserDomain user = userService.selectByName(username);
			return new User(user.getUsername(),user.getPassword(),Collections.emptyList());
		} else {
			throw new UsernameNotFoundException(ResponseEnum.AUTH_FAIL.getMsg());
		}
	} 
}
