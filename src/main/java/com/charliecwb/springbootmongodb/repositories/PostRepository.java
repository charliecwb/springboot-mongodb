package com.charliecwb.springbootmongodb.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.charliecwb.springbootmongodb.entities.PostEntity;

public interface PostRepository extends MongoRepository<PostEntity, String> { 
	List<PostEntity> findByTitleContainingIgnoreCase(String text);
	
	List<PostEntity> findByBodyContainingIgnoreCase(String text);
	
	List<PostEntity> findByCommentsTextContainingIgnoreCase(String text);	
	
	@Query("{$and:[{'date':{$gte: ?1}}, {'date':{$lte: ?2}}, {$or:[{'title':{$regex: ?0, $options: 'i'}}, "
			+ "{'body':{$regex: ?0, $options: 'i'}}, {'comments.text':{$regex: ?0, $options: 'i'}}]}]}")
	List<PostEntity> fullSearch(String text, Date minDate, Date maxDate);
}
