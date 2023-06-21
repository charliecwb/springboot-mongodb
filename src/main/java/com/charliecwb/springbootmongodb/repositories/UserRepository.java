package com.charliecwb.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.charliecwb.springbootmongodb.entities.UserEntity;

public interface UserRepository extends MongoRepository<UserEntity, String> { 
	UserEntity findByLoginUserName(String username);	
}
