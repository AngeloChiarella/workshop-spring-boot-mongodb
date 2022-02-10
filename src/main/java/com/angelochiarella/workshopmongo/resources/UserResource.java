package com.angelochiarella.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.angelochiarella.workshopmongo.domain.User;
import com.angelochiarella.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource
{
	@Autowired
	private UserService service;

//	@RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public ResponseEntity<List<User>> findAll() // ResponseEntity - representa a resposta HTTP inteira.
	{
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);// .ok() - instacia com codigo http sucesso | .body() - define corpo da resposta
	}
}
