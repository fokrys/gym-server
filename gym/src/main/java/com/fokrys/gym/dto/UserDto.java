package com.fokrys.gym.dto;


public class UserDto {
	private Long id;
	private String username;
	private String password;
	private String email;
	private Long roleId;
	private Long personalId;
	private Boolean userEqual;

	public UserDto() {
		super();
	}
	
	

	public UserDto(Long id, String username, String password, String email, Long roleId, Long personalId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roleId = roleId;
		this.personalId = personalId;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPersonalId() {
		return personalId;
	}

	public void setPersonalId(Long personalId) {
		this.personalId = personalId;
	}



	public Boolean getUserEqual() {
		return userEqual;
	}



	public void setUserEqual(Boolean userEqual) {
		this.userEqual = userEqual;
	}



	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", roleId=" + roleId + ", personalId=" + personalId + ", userEqual=" + userEqual + "]";
	}
	
	

}
