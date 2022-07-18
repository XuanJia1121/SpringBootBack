package com.lab.lab.Interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lab.lab.dto.ResponseDto;
import com.lab.lab.enums.ResponseEnum;
import com.lab.lab.service.JWTService;
import com.lab.lab.utils.ResponseUtil;

@Component
public class AuthorizationCheckFilter extends OncePerRequestFilter {
	
	@Autowired
	private JWTService jWTService;
	@Autowired
	private ResponseUtil responseUtil;
	
	final String AUTH = "AUTHORIZATION";
	final String PATH = "/lab/api/login";
	
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws ServletException, IOException {
		//如果不是登入就攔截
		if (!StringUtils.equals(PATH,req.getServletPath())) {
			String authorHeader = req.getHeader(AUTH);
			String bearer = "Bearer ";
			// 以jjwt驗證token，只要驗證成功就放行
			if (authorHeader != null && authorHeader.startsWith(bearer)) {
				String token = StringUtils.substring(authorHeader,bearer.length());
				try {
					jWTService.validateToken(token);
					chain.doFilter(req,res);
				} catch (Exception e) {
					ResponseDto responseDto = responseUtil.genDto(ResponseEnum.AUTH_FAIL.getCode(),ResponseEnum.AUTH_FAIL.getMsg(),e.getMessage());
					responseUtil.writeResponse(res,responseDto);
				}
			} else {
				ResponseDto responseDto = responseUtil.genDto(ResponseEnum.AUTH_FAIL.getCode(),ResponseEnum.AUTH_FAIL.getMsg(),ResponseEnum.AUTH_FAIL.getMsg());
				responseUtil.writeResponse(res,responseDto);
			}
		} else {
			chain.doFilter(req,res);
		}
	}
}
