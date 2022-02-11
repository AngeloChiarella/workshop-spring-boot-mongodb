package com.angelochiarella.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelochiarella.workshopmongo.domain.Post;
import com.angelochiarella.workshopmongo.repositories.PostRepository;
import com.angelochiarella.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService
{
	@Autowired
	private PostRepository repo;

	public Post findById(String id)
	{
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> findByTitle(String text)
	{
		return repo.findByTitle(text);
//		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate)
	{
		//soma um dia para comparar uma certa data até as "23:59:59 " ( menor ou igual)
		maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.fullSearch(text, minDate, maxDate);
	}
}
