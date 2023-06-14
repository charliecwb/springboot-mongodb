package com.charliecwb.springbootmongodb.dto;

import java.io.Serializable;
import java.util.Date;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.domain.User;

public class PostDTO implements Serializable {	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private Date date;
	private String title;
	private String body;
	
	public PostDTO() {}
	
	public PostDTO(Post post) {
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
	
	public Post fromDTO(User user) {
		return new Post(getId(), getDate(), getTitle(), getBody(), 
				new AuthorDTO(user));
	}	
}
