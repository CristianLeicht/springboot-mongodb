package com.cristianleicht.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cristianleicht.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	
	//?0 = primeiro parametro que vier no método
	//'i' = ignoreCase
	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	List<Post> searchTitle(String text);

	List<Post> findByTitleContainingIgnoreCase(String text);

}
