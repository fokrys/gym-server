package com.fokrys.gym.dto;


public class PersonalDataDto {

	private Long id;
	private String name;
	private String surname;
	private String description;
	private String address;
	
	
	public PersonalDataDto() {
		super();
	}

	public PersonalDataDto(Long id, String name, String surname, String description, String address) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.description = description;
		this.address = address;
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

	public Long getId() {
		return id;
	}

	public void setId(Long userId) {
		this.id = userId;
	}

	@Override
	public String toString() {
		return "PersonalDataDto [id=" + id + ", name=" + name + ", surname=" + surname + ", description=" + description
				+ ", address=" + address + "]";
	}
	
	

}
