package com.lab.lab.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.lab.dto.ResponseDto;
import com.lab.lab.utils.ResponseUtil;

@RequestMapping("/test")
@RestController
public class TestController {
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@GetMapping("/test")
	public ResponseDto test(HttpServletResponse response) throws Exception {
		return responseUtil.genDto("", "","經過filter");
	}
	
}
