package com.angelochiarella.workshopmongo.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.angelochiarella.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>
{
	List<Post> findByTitleContainingIgnoreCase(String text);
	 // TitleContaining - Faz busca a partir do dado parâmetro, dentro de Title
	 // IgnoreCase - Ignorar maiúsculas e minúsculas
}