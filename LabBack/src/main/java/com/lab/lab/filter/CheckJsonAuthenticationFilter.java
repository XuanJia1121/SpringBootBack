package com.lab.lab.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CheckJsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";

	public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
	
	private boolean postOnly = true;
	
	private final String method = "POST";
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (this.postOnly && !StringUtils.equals(request.getMethod(),method)) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}
		ObjectMapper mapper = new ObjectMapper();
		UsernamePasswordAuthenticationToken authRequest = null;
		try (InputStream in = request.getInputStream()){
			@SuppressWarnings("unchecked")
			Map<String,String> authenticationJsonMap = mapper.readValue(in,Map.class);
			authRequest = new UsernamePasswordAuthenticationToken
					(authenticationJsonMap.get(SPRING_SECURITY_FORM_USERNAME_KEY),authenticationJsonMap.get(SPRING_SECURITY_FORM_PASSWORD_KEY));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setDetails(request, authRequest);
		return this.getAuthenticationManager().authenticate(authRequest);
	}
}
