package com.lab.lab.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lab.lab.dto.ResponseDto;
import com.lab.lab.enums.ResponseEnum;
import com.lab.lab.utils.ResponseUtil;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	
	@Autowired
	private ResponseUtil responseUtil;
	
	@ExceptionHandler(Exception.class)
	public ResponseDto exceptionHandle(Exception e) throws Exception {
		return responseUtil.genDto(ResponseEnum.FAIL.getCode(),ResponseEnum.FAIL.getMsg(),e.getMessage());
	}
	
}
