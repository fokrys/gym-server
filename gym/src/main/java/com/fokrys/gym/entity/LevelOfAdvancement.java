package com.fokrys.gym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="levelOfAdvancement")
public class LevelOfAdvancement {
	
	@Id
	private Long id;
	
	@Column(name="name")
	private String name;
	
	
	public LevelOfAdvancement() {
		super();
	}

	public LevelOfAdvancement(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
