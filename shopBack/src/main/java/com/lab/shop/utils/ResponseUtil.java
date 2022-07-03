package com.lab.shop.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	public static void writeResponse(HttpServletResponse response,String msg) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setHeader("Access-Control-Allow-Credentials","true");
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json;charset=utf-8");
		httpResponse.getWriter().print(msg);
	}
}
