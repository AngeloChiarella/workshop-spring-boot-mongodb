package com.angelochiarella.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.angelochiarella.workshopmongo.domain.Post;
import com.angelochiarella.workshopmongo.resources.util.URL;
import com.angelochiarella.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource
{
	@Autowired
	private PostService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // @GetMapping
	public ResponseEntity<Post> findById(@PathVariable String id) // @PathVariable - Casa o id com id recebido da url
	{
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(value = "/titlesearch", method = RequestMethod.GET) // @GetMapping
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) // @PathVariable - Casa o id com id recebido da url
	{
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
}
