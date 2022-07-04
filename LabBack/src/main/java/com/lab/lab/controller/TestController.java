package com.lab.lab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<?> test() throws Exception {
		ResponseDto res = new ResponseDto();
		return responseUtil.suc(res);
	}
	
}
