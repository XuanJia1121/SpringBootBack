package com.lab.lab.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.lab.dto.ResponseDto;

@Component
public class ResponseUtil {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public void writeResponse(HttpServletResponse response,ResponseDto data) throws IOException {
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(objectMapper.writeValueAsString(data));
	}
	
	public ResponseDto genDto(String code,String msg,Object data) throws JsonProcessingException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(code);
		responseDto.setMsg(msg);
		responseDto.setData(objectMapper.writeValueAsString(data));
		return responseDto;
	}
}
