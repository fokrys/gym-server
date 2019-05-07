package com.fokrys.gym.dto;

import org.springframework.web.multipart.MultipartFile;

public class GalleryDto {
	private Long id;
	private String name;
	private boolean profile;
	private String username;
	private Long userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isProfile() {
		return profile;
	}
	public void setProfile(boolean profile) {
		this.profile = profile;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public GalleryDto(Long id, String name, boolean profile, String username, Long userid) {
		super();
		this.id = id;
		this.name = name;
		this.profile = profile;
		this.username = username;
		this.userId = userid;
	}
	public GalleryDto(String name, boolean profile, String username, Long userId) {
		super();
		this.name = name;
		this.profile = profile;
		this.username = username;
		this.userId = userId;
	}
	public GalleryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GalleryDto [id=" + id + ", name=" + name + ", profile=" + profile + ", username=" + username
				+ ", userId=" + userId + "]";
	}
	
	
}
