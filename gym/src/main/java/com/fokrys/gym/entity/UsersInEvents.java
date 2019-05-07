package com.fokrys.gym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class UsersInEvents {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "eventId", nullable = false)
	private EventInformations event;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	@Column(name = "userEnabled")
	@Value("false")
	private boolean userEnabled;
	
	@Column(name = "userDisabled")
	@Value("false")
	private boolean userDisabled;
	

	public UsersInEvents() {
		super();
	}

	public UsersInEvents(EventInformations event, User user) {
		super();
		this.event = event;
		this.user = user;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	public EventInformations getEvent() {
		return event;
	}



	public void setEvent(EventInformations event) {
		this.event = event;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
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

	@Override
	public String toString() {
		return "UsersInEvents [id=" + id + ", event=" + event + ", user=" + user + ", userEnabled=" + userEnabled + "]";
	}
	
	
	
	
}
