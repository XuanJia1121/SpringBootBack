package com.lab.lab.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.lab.lab.dto.ResponseDto;
import com.lab.lab.enums.ResponseEnum;
import com.lab.lab.utils.ResponseUtil;

@Component
public class AuthFailService implements AuthenticationFailureHandler {
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {
		ResponseDto responseDto = responseUtil.genDto(ResponseEnum.LOGIN_FAIL.getCode(),ResponseEnum.LOGIN_FAIL.getMsg(),exception.getMessage());
		responseUtil.writeResponse(response,responseDto);
	}
}
