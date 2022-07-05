package com.lab.lab.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.lab.lab.annotation.JwtAuth;
import com.lab.lab.enums.ResponseEnum;
import com.lab.lab.utils.ResponseUtil;

@Component
public class Interceptor implements HandlerInterceptor {
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		JwtAuth jwtAuth = ((HandlerMethod)handler).getMethodAnnotation(JwtAuth.class);
		if (jwtAuth == null) {
			return true;			
		} else {
			responseUtil.writeResponse(response,responseUtil.genDto(ResponseEnum.FAIL.getCode(), ResponseEnum.FAIL.getMsg(),"fail"));
			return false;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}
}
