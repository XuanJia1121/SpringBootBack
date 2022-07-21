package com.lab.lab.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.lab.lab.dto.AuthUser;
import com.lab.lab.dto.ResponseDto;
import com.lab.lab.enums.ResponseEnum;
import com.lab.lab.utils.ResponseUtil;

@Service
public class AuthSuccessService implements AuthenticationSuccessHandler {
	
	@Autowired
	private ResponseUtil responseUtil;
	@Autowired
	private JWTService jWTService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		AuthUser authUser = new AuthUser();
		authUser.setUsername(user.getUsername());
		authUser.setToken(jWTService.generateToken(authUser));
		ResponseDto responseDto = responseUtil.genDto(ResponseEnum.LOGIN_SUC.getCode(),ResponseEnum.LOGIN_SUC.getMsg(),authUser);
		responseUtil.writeResponse(response,responseDto);
	}
}
