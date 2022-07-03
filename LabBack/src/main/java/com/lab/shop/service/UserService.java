package com.lab.shop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lab.shop.domain.UserDomain;
import com.lab.shop.mapper.UserMapper;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public UserDomain selectByName(String username) {
		QueryWrapper<UserDomain> userWrapper = new QueryWrapper<>();
		userWrapper.eq("username", username);
		Optional<UserDomain> authUser = Optional.ofNullable(userMapper.selectOne(userWrapper));
		if (authUser.isPresent()) {
			return authUser.get();
		} else {
			throw new UsernameNotFoundException("Username is wrong.");
		}
	}
}
