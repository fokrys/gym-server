package com.fokrys.gym.entity;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Gallery {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private boolean profile;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gallery(Long id, String name, boolean profile, User user) {
		super();
		this.id = id;
		this.name = name;
		this.profile = profile;
		this.user = user;
	}

	public Gallery(String name, boolean profile, User user) {
		super();
		this.name = name;
		this.profile = profile;
		this.user = user;
	}

	public Gallery() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
