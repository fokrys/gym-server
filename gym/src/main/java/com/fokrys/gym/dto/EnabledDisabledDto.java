package com.fokrys.gym.dto;

public class EnabledDisabledDto {
	private int enabled;
	private int disabled;
	private Long userId;
	private String username;
	
	
	
	
	public EnabledDisabledDto(int enabled, int disabled, Long userId, String username) {
		super();
		this.enabled = enabled;
		this.disabled = disabled;
		this.userId = userId;
		this.username = username;
	}
	public EnabledDisabledDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	public int getDisabled() {
		return disabled;
	}
	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
}
