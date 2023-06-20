package com.charliecwb.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.entities.UserEntity;
import com.charliecwb.springbootmongodb.models.UserDTO;
import com.charliecwb.springbootmongodb.repositories.UserRepository;
import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;
import com.charliecwb.springbootmongodb.services.exception.UserNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<UserEntity> findAll() {
		return repository.findAll(); 
	}
	
	public UserEntity findByUserName(String text) {
		return repository.findByLoginUserName(text);
	}	
	
	public UserEntity findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public UserEntity insert(UserEntity obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public UserEntity update(UserEntity obj) {
		UserEntity user = findById(obj.getId());
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		return repository.save(user);
	}
	
	public UserEntity fromDTO(UserDTO obj) {
		UserEntity resp = new UserEntity(obj.getId(), obj.getName(), obj.getEmail(), obj.getPhone());
		resp.setLogin(obj.getLogin());
		List<PostEntity> posts = obj.getPosts().stream().map(x -> x.fromDTO(resp)).toList();
		resp.getPosts().addAll(posts);	
		
		return resp;
	}
}
