package com.fokrys.gym.dto;

public class WebsiteDescriptionDto {

	private Long id;

	private String title;

	private String description;

	private String date;
	
	private Long userId;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "WebsiteDescriptionDto [id=" + id + ", title=" + title + ", description=" + description + ", date="
				+ date + ", userId=" + userId + "]";
	}
	
	
	
}
