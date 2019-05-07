package com.fokrys.gym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class PersonalData {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "description")
	private String description;

	@Column(name = "address")
	private String address;

	
	

	public PersonalData(String name, String surname, String description, String address) {
		super();
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.address = address;
	}

	public PersonalData() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonalData(Long id, String name, String surname, String description, String address) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.address = address;
	}

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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	

}