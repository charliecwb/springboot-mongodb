package com.charliecwb.springbootmongodb.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.charliecwb.springbootmongodb.domain.User;
import com.charliecwb.springbootmongodb.resources.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String email;
	private String phone;
	@JsonIgnore
	private List<PostDTO> posts = new ArrayList<>();
	private UserDetailDTO login;

	public UserDTO() {
	}

	public UserDTO(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		phone = user.getPhone();
		posts = user.getPosts().stream().map(x -> new PostDTO(x)).toList();
		login = new UserDetailDTO();
		login.setUserName(user.getLogin().getUserName());
		login.setPassword(Util.decryptPassword(user.getLogin().getPassword()));
		login.setTwoFA(user.getLogin().getTwoFA());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<PostDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<PostDTO> posts) {
		this.posts = posts;
	}

	public UserDetailDTO getLogin() {
		return login;
	}

	public void setLogin(UserDetailDTO login) {
		this.login = login;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
