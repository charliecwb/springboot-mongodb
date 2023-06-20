package com.charliecwb.springbootmongodb.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.charliecwb.springbootmongodb.entities.PostEntity;
import com.charliecwb.springbootmongodb.entities.UserEntity;
import com.charliecwb.springbootmongodb.models.UserDTO;
import com.charliecwb.springbootmongodb.repositories.UserRepository;
import com.charliecwb.springbootmongodb.secutiry.Sha512PasswordEncoder;
import com.charliecwb.springbootmongodb.services.exception.ObjectNotFoundException;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	
	@Autowired 
	private Sha512PasswordEncoder passEncoder;
	
	@Override
	public UserEntity findByUserName(String text) {
		return repository.findByLoginUserName(text);
	}	
	
	public UserEntity findById(String id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public UserEntity fromDTO(UserDTO obj) {
		UserEntity resp = new UserEntity(obj.getId(), obj.getName(), obj.getEmail(), obj.getPhone());
		resp.setLogin(obj.getLogin());
		List<PostEntity> posts = obj.getPosts().stream().map(x -> x.fromDTO(resp)).toList();
		resp.getPosts().addAll(posts);	
		
		return resp;
	}

	@Override
	public void saveUser(UserDTO userDto) {
		userDto.getLogin().setPassword(passEncoder.encode(userDto.getLogin().getPassword()));
		
		repository.insert(fromDTO(userDto));
	}

	@Override
	public List<UserDTO> findAllUsers() {
		return repository.findAll().stream().map(user -> new UserDTO(user)).toList();
	}
}
