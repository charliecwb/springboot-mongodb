package com.charliecwb.springbootmongodb.models;

import com.charliecwb.springbootmongodb.entities.UserEntity;

public class AuthorDTO {
	private String id;
	private String name;
	
	public AuthorDTO() {}

	public AuthorDTO(UserEntity user) {
		this.id = user.getId();
		this.name = user.getName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
