package com.lab.shop.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.AuthRequest;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.utils.ResponseUtil;

@Service
public class AuthSuccessService implements AuthenticationSuccessHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private JWTService jWTService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		User user = (User) authentication.getPrincipal();
		AuthRequest req = new AuthRequest();
		req.setUsername(user.getUsername());
		req.setPassword(user.getPassword());
		req.setToken(jWTService.generateToken(req));
		//rtn
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(ResponseEnum.LOGIN_SUC.getCode());
		responseDto.setMsg(ResponseEnum.LOGIN_SUC.getMsg());
		responseDto.setData(objectMapper.writeValueAsString(req));
		String dataStr = objectMapper.writeValueAsString(responseDto);
		ResponseUtil.writeResponse(response,dataStr);
		request.getSession().setAttribute(String.format("%s_USER_INFO",user.getUsername()),dataStr);
	}
}
