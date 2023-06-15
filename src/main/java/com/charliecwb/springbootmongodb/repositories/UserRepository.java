package com.charliecwb.springbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.charliecwb.springbootmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> { 
	User findByLoginUserName(String text);	
}
