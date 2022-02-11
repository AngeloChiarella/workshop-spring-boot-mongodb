package com.angelochiarella.workshopmongo.repositories;

import java.util.Date;
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
	
	// TitleContaining - Faz busca a partir do dado parâmetro, dentro de Title
	// IgnoreCase - Ignorar maiúsculas e minúsculas
	List<Post> findByTitleContainingIgnoreCase(String text);

	//$gte - greater than or equal (maior ou igual)
	//?0 = text, ?1 = minDate, ?2 = maxDate
	//$lte - less than or equal (menor ou igual)
	//$or - ou
	//'comments.text' - buscar dentro do texto de cada comentário em uma lista de comentarios
	@Query("{ $and: [ { date: { $gte: ?1 } }, { date: { $lte: ?2 } }, "
			+ "{ $or: [ { 'title': { $regex: ?0, $options: 'i' } },"
			+ " { 'body': { $regex: ?0, $options: 'i' } }, "
			+ "{ 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}