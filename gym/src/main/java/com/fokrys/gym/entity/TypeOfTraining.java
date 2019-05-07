package com.fokrys.gym.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="types")
public class TypeOfTraining {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name="contraindications", columnDefinition = "TEXT")
	private String contraindications;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "levelOfAdvancement", nullable = false)
	private LevelOfAdvancement levelOfAdvancement;
	
	

	public TypeOfTraining() {
		super();
	}

	
	public TypeOfTraining(String title, String description, String contraindications,
			LevelOfAdvancement levelOfAdvancement) {
		super();
		this.title = title;
		this.description = description;
		this.contraindications = contraindications;
		this.levelOfAdvancement = levelOfAdvancement;
	}

	public TypeOfTraining(Long id, String title, String description, String contraindications,
			LevelOfAdvancement levelOfAdvancement) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.contraindications = contraindications;
		this.levelOfAdvancement = levelOfAdvancement;
	}

	public TypeOfTraining(Long id, String title, String description, String contraindications, String level,
			LevelOfAdvancement levelOfAdvancement) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.contraindications = contraindications;
		this.levelOfAdvancement = levelOfAdvancement;
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

	public String getContraindications() {
		return contraindications;
	}

	public void setContraindications(String contraindications) {
		this.contraindications = contraindications;
	}

	public LevelOfAdvancement getLevelOfAdvancement() {
		return levelOfAdvancement;
	}

	public void setLevelOfAdvancement(LevelOfAdvancement levelOfAdvancement) {
		this.levelOfAdvancement = levelOfAdvancement;
	}


	@Override
	public String toString() {
		return "TypeOfTraining [id=" + id + ", title=" + title + ", description=" + description + ", contraindications="
				+ contraindications + ", levelOfAdvancement=" + levelOfAdvancement + "]";
	}
	
	
	
	
}
