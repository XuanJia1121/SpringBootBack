package com.lab.shop.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.utils.ResponseUtil;

@Service
public class AccessDeniedService implements AccessDeniedHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		//rtn
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(ResponseEnum.ACCESS_DENIED.getCode());
		responseDto.setMsg(ResponseEnum.ACCESS_DENIED.getMsg());
		responseDto.setData(accessDeniedException.getMessage());
		ResponseUtil.writeResponse(response,objectMapper.writeValueAsString(responseDto));
	}
}
