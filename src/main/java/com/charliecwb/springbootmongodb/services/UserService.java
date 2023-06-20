package com.charliecwb.springbootmongodb.services;

import java.util.List;

import com.charliecwb.springbootmongodb.entities.UserEntity;
import com.charliecwb.springbootmongodb.models.UserDTO;

public interface UserService {
	void saveUser(UserDTO userDto);

    UserEntity findByUserName(String username);

    List<UserDTO> findAllUsers();
}
