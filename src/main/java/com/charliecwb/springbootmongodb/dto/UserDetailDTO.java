package com.charliecwb.springbootmongodb.dto;

import java.io.Serializable;
import java.util.Objects;

import com.charliecwb.springbootmongodb.resources.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(required = true)
	private String userName;
	@JsonProperty(required = true)
	private String password;
	@JsonIgnore
	private Boolean twoFA;
	
	public UserDetailDTO() {}

	public UserDetailDTO(String userName, String password, Boolean twoFA) {
		this.userName = userName;
		this.password = Util.encryptPassword(password);
		this.twoFA = twoFA;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Boolean getTwoFA() {
		return twoFA;
	}

	public void setTwoFA(Boolean doubleAuthentication) {
		this.twoFA = doubleAuthentication;
	}	

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetailDTO other = (UserDetailDTO) obj;
		return Objects.equals(userName, other.userName);
	}
}
