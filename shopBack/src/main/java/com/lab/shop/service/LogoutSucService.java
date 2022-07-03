package com.lab.shop.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.shop.dto.ResponseDto;
import com.lab.shop.enums.ResponseEnum;
import com.lab.shop.utils.ResponseUtil;

@Service
public class LogoutSucService implements LogoutSuccessHandler{
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//rtn
		ResponseDto responseDto = new ResponseDto();
		responseDto.setCode(ResponseEnum.LOGOUT_SUC.getCode());
		responseDto.setMsg(ResponseEnum.LOGOUT_SUC.getMsg());
		responseDto.setData(null);
		ResponseUtil.writeResponse(response,objectMapper.writeValueAsString(responseDto));
	}
}
