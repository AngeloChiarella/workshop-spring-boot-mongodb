package com.angelochiarella.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.angelochiarella.workshopmongo.domain.User;
import com.angelochiarella.workshopmongo.dto.UserDTO;
import com.angelochiarella.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource
{
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() // ResponseEntity - representa a resposta HTTP inteira.
	{
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);// .ok() - instacia com codigo http sucesso | .body() - define corpo da resposta
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) //@PathVariable - Casa o id com id recebido da url
	{
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

}
