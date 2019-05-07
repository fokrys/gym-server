package com.fokrys.gym.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class EventInformations {
	@Id
	@GeneratedValue
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "address")
	private String address;
	@Column(name = "eventStart")
	private Date eventStart;
	@Column(name = "eventEnd")
	private Date eventEnd;
	@Column(name = "entriesEnd")
	private Date entriesEnd;
	@Column(name = "color")
	private String color;
	@Column(name = "numberOfPeople")
	private Long numberOfPeople;
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "typeOfTraining", nullable = false)
	private TypeOfTraining typeOfTraining;
	
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;

	public EventInformations() {
		super();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getEventStart() {
		return eventStart;
	}

	public void setEventStart(Date eventStart) {
		this.eventStart = eventStart;
	}

	public Date getEventEnd() {
		return eventEnd;
	}

	public void setEventEnd(Date eventEnd) {
		this.eventEnd = eventEnd;
	}

	public Date getEntriesEnd() {
		return entriesEnd;
	}

	public void setEntriesEnd(Date entriesEnd) {
		this.entriesEnd = entriesEnd;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public TypeOfTraining getTypeOfTraining() {
		return typeOfTraining;
	}

	public void setTypeOfTraining(TypeOfTraining typeOfTraining) {
		this.typeOfTraining = typeOfTraining;
	}

	public Long getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Long numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public EventInformations(Long id, String title, String address, Date eventStart, Date eventEnd, Date entriesEnd,
			String color, Long numberOfPeople, String description, TypeOfTraining typeOfTraining, User user) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.entriesEnd = entriesEnd;
		this.color = color;
		this.numberOfPeople = numberOfPeople;
		this.description = description;
		this.typeOfTraining = typeOfTraining;
		this.user = user;
	}

	
	
	
	
}
