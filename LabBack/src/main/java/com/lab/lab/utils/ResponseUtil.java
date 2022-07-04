package com.lab.lab.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ResponseUtil {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void writeResponse(HttpServletResponse response,ResponseEntity<?> resEntity) throws IOException {
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(resEntity);
	}
	
	public ResponseEntity<?> suc(Object obj) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.OK).body(objectMapper.writeValueAsString(obj));
	}
	
	public ResponseEntity<?> fail(Object obj) throws JsonProcessingException {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(objectMapper.writeValueAsString(obj));
	}
}
