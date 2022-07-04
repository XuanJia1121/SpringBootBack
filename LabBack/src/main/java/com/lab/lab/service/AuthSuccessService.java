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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.lab.dto.AuthRequest;

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
		//return

	}
}
