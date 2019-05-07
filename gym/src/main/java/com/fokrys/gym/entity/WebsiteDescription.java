package com.fokrys.gym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class WebsiteDescription {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "date")
	private Date date;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	
	

	public WebsiteDescription() {
		super();
	}
	
	public WebsiteDescription(Long id, String title, String description, Date date, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.user = user;
	}



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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
