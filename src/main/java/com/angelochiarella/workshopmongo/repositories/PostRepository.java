package com.angelochiarella.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.angelochiarella.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>
{
	//'title' - campo pelo qual vou efetuar busca
	//?0 - primeiro parâmetro do método
	// i - ignorar maiúsculas e minúsculas
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> findByTitle(String text);
	
	List<Post> findByTitleContainingIgnoreCase(String text);
	 // TitleContaining - Faz busca a partir do dado parâmetro, dentro de Title
	 // IgnoreCase - Ignorar maiúsculas e minúsculas
}