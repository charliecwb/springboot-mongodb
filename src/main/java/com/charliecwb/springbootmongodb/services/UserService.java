package com.charliecwb.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.domain.User;
import com.charliecwb.springbootmongodb.dto.UserDTO;
import com.charliecwb.springbootmongodb.repositories.UserRepository;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;
import com.charliecwb.springbootmongodb.services.exception.UserNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {
		return repository.findAll(); 
	}
	
	public User findByUserName(String text) {
		User resp = repository.findByLoginUserName(text);
		if (resp == null) {
			throw new UserNotFoundException(text);
		}
		return resp;
	}	
	
	public User findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public User update(User obj) {
		User user = findById(obj.getId());
		user.setName(obj.getName());
		user.setEmail(obj.getEmail());
		return repository.save(user);
	}
	
	public User fromDTO(UserDTO obj) {
		User resp = new User(obj.getId(), obj.getName(), obj.getEmail(), obj.getPhone());
		resp.setLogin(obj.getLogin());
		List<Post> posts = obj.getPosts().stream().map(x -> x.fromDTO(resp)).toList();
		resp.getPosts().addAll(posts);	
		
		return resp;
	}
}
