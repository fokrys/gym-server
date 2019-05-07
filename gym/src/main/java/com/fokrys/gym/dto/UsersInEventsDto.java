package com.fokrys.gym.dto;

public class UsersInEventsDto {
	private Long id;
	private Long eventId;
	private String username;
	private boolean userEnabled; 
	private boolean userDisabled;
	
	public Long getEventId() {
		return eventId;
	}
	
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}
	

	public boolean isUserDisabled() {
		return userDisabled;
	}

	public void setUserDisabled(boolean userDisabled) {
		this.userDisabled = userDisabled;
	}

	public UsersInEventsDto(Long id, Long eventId, String username, boolean userEnabled, boolean userDisabled) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.username = username;
		this.userEnabled = userEnabled;
		this.userDisabled = userDisabled;
	}

	public UsersInEventsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "UsersInEventsDto [id=" + id + ", eventId=" + eventId + ", username=" + username + ", userEnabled="
				+ userEnabled + ", userDisabled=" + userDisabled + "]";
	}

	public UsersInEventsDto(Long eventId, String username, boolean userEnabled, boolean userDisabled) {
		super();
		this.eventId = eventId;
		this.username = username;
		this.userEnabled = userEnabled;
		this.userDisabled = userDisabled;
	}

	
}
