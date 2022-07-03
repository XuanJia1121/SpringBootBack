package com.lab.shop.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.AuthRequest;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.utils.ResponseUtil;

@Service
public class GoogleLoginSuccessService implements AuthenticationSuccessHandler {
	
	@Autowired
	private JWTService jWTService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		OAuth2AuthenticationToken oauthentication = (OAuth2AuthenticationToken) authentication;
		Map<String,Object> authMap = oauthentication.getPrincipal().getAttributes();
		AuthRequest authRequest = new AuthRequest();
		authRequest.setUsername((String)authMap.get("name"));
		authRequest.setToken(jWTService.generateToken(authRequest));
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(ResponseEnum.LOGIN_SUC.getCode());
		responseDto.setMsg(ResponseEnum.LOGIN_SUC.getMsg());
		responseDto.setData(objectMapper.writeValueAsString(authRequest));
		String dataStr = objectMapper.writeValueAsString(responseDto);
		ResponseUtil.writeResponse(response,dataStr);
		request.getSession().setAttribute(String.format("%s_USER_INFO",authRequest.getUsername()), objectMapper.writeValueAsString(dataStr));
	}
	
}
