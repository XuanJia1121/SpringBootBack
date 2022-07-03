package com.lab.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
	
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	public void setByKey(String key,String val) {
		stringRedisTemplate.opsForValue().set(key,val);
	}
	
	public String getByKey(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	
}
