package com.lab.shop.encode;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;


public class UserPasswordEncoder implements PasswordEncoder {
	
	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return StringUtils.equals(encodedPassword,rawPassword.toString());
	}
	
}
