package com.lab.shop.enums;

public enum ResponseEnum {
	
	LOGIN_FAIL("400","登入失敗"),
	LOGIN_SUC("200","登入成功"),
	
	LOGOUT_FAIL("400","登出失敗"),
	LOGOUT_SUC("200","登出成功"),
	
	AUTH_FAIL("400","驗證失敗"),
	AUTH_SUC("200","驗證成功"),

	ACCESS_DENIED("400","訪問失敗");
	
	private final String code;
	private final String msg;

	ResponseEnum(String code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return this.code;
	}

	public String getMsg() {
		return msg;
	}
}
