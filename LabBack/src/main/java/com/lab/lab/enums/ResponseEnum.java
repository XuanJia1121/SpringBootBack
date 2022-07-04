package com.lab.lab.enums;

public enum ResponseEnum {
	
	FAIL("500","失敗"),
	SUC("200","成功"),
	
	LOGIN_FAIL("500","登入失敗"),
	LOGIN_SUC("200","登入成功"),
	
	LOGOUT_FAIL("500","登出失敗"),
	LOGOUT_SUC("200","登出成功"),
	
	AUTH_FAIL("500","驗證失敗"),
	AUTH_SUC("200","驗證成功"),

	ACCESS_DENIED("500","訪問失敗");
	
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
