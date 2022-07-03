package com.lab.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.shop.service.RedisService;

@RequestMapping("/test")
@RestController
public class TestController {
	
	@Autowired
	private RedisService redisService;
	
	@GetMapping("/rSet")
	public String set() {
		redisService.setByKey("test","123");
		return "succ";
	}
	
	@GetMapping("/rGet")
	public String get() {
		return redisService.getByKey("test");
	}
	
}
