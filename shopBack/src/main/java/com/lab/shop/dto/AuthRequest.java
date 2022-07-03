package com.lab.shop.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthRequest implements Serializable{
	
	private static final long serialVersionUID = -4690717992919698119L;
	
	private String username;
	private String password;
	private String token;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
