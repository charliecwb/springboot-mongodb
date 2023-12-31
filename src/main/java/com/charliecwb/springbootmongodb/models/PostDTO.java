package com.charliecwb.springbootmongodb.models;

import java.util.Date;

import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.entities.UserEntity;

public class PostDTO {	
	private String id;
	private Date date;
	private String title;
	private String body;
	
	public PostDTO() {}
	
	public PostDTO(PostEntity post) {
		id = post.getId();
		date = post.getDate();
		title = post.getTitle();
		body = post.getBody();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public PostEntity fromDTO(UserEntity user) {
		return new PostEntity(getId(), getDate(), getTitle(), getBody(), 
				new AuthorDTO(user));
	}	
}
