package com.fokrys.gym.dto;


public class TypeOfTrainingDto {
	
	private Long id;
	
	private String title;
	
	private String description;
	
	private String contraindications;
	
	private Long levelId;
	
	

	public TypeOfTrainingDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeOfTrainingDto(Long id, String title, String description, String contraindications,
			Long levelId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.contraindications = contraindications;
		this.levelId = levelId;
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

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	@Override
	public String toString() {
		return "TypeOfTrainingDto [id=" + id + ", title=" + title + ", description=" + description
				+ ", contraindications=" + contraindications + ", levelId=" + levelId + "]";
	}

	 
	
	
	
}
