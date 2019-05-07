package com.fokrys.gym.dto;

public class PriceListDto {
	private Long id;

	private String name;

	private String description;

	private Double price;
	
	private Long numberOfMonths;

	private Boolean popular;
	
	public PriceListDto(Long id, String name, String description, Double price, Long numberOfMonths, Boolean popular) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.numberOfMonths = numberOfMonths;
		this.popular = popular;
	}

	public PriceListDto() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMounths(Long numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public Boolean isPopular() {
		return popular;
	}

	public void setPopular(Boolean popular) {
		this.popular = popular;
	}
	
	
	
}
