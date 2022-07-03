package com.lab.shop.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.utils.ResponseUtil;

@Component
public class AuthFailService implements AuthenticationFailureHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(ResponseEnum.LOGIN_FAIL.getCode());
		responseDto.setMsg(ResponseEnum.LOGIN_FAIL.getMsg());
		responseDto.setData(null);
		ResponseUtil.writeResponse(response,objectMapper.writeValueAsString(responseDto));
	}
}
