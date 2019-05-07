package com.fokrys.gym.dto;

import java.util.Date;
import java.util.List;

public class EventInformationsDto {
	
	private Long id;
	private String title;
	private String address;
	private Date eventStart;
	private Date eventEnd;
	private Date entriesEnd;
	private String description;
	private Long couchId;
	private String color;
	private Long typeId;
	private Long numberOfPeople;
	
	
	
	public EventInformationsDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EventInformationsDto(Long id, String title, String address, Date eventStart, Date eventEnd, Date entriesEnd,
			String description, Long couchId, String color, Long typeId, Long numberOfPeople) {
		super();
		this.id = id;
		this.title = title;
		this.address = address;
		this.eventStart = eventStart;
		this.eventEnd = eventEnd;
		this.entriesEnd = entriesEnd;
		this.description = description;
		this.couchId = couchId;
		this.color = color;
		this.typeId = typeId;
		this.numberOfPeople = numberOfPeople;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getCouchId() {
		return couchId;
	}
	public void setCouchId(Long couchId) {
		this.couchId = couchId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public Long getNumberOfPeople() {
		return numberOfPeople;
	}
	public void setNumberOfPeople(Long numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}
	@Override
	public String toString() {
		return "EventInformationsDto [id=" + id + ", title=" + title + ", address=" + address + ", eventStart="
				+ eventStart + ", eventEnd=" + eventEnd + ", entriesEnd=" + entriesEnd + ", description=" + description
				+ ", couchId=" + couchId + ", color=" + color + ", typeId=" + typeId + ", numberOfPeople="
				+ numberOfPeople + "]";
	}
	
	
}
