package com.fokrys.gym.dto;

public class TokenDto {
private String token;
private String role;
private String username;
	
	public TokenDto() {}

	public TokenDto(String token) {
		super();
		this.token = token;
	}
	
	public TokenDto(String token, String role, String username) {
		super();
		this.token = token;
		this.role = role;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
