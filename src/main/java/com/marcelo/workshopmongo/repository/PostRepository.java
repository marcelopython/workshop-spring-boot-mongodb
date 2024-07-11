package com.marcelo.workshopmongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.marcelo.workshopmongo.domain.Post;

public interface PostRepository extends MongoRepository<Post, String>{

}
