package com.angelochiarella.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.angelochiarella.workshopmongo.domain.User;
import com.angelochiarella.workshopmongo.dto.UserDTO;
import com.angelochiarella.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource
{
	@Autowired
	private UserService service;

	@RequestMapping(method = RequestMethod.GET) // @GetMapping
	public ResponseEntity<List<UserDTO>> findAll() // ResponseEntity - representa a resposta HTTP inteira.
	{
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);// .ok() - instacia com codigo http sucesso | .body() - define corpo da resposta
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // @GetMapping
	public ResponseEntity<UserDTO> findById(@PathVariable String id) // @PathVariable - Casa o id com id recebido da url
	{
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	@RequestMapping(method = RequestMethod.POST) // @PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) // @PathVariable - Casa o id com id recebido da url
	{
		User obj = service.fromDTO(objDto); // converter DTO para User
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(obj.getId()).toUri();
		// URI pega o endereço do novo objeto que for inserido
		return ResponseEntity.created(uri).build();
		// Retorna resposta vazia, com o código 201 com o cabeçalho contendo a localização do novo recurso criado
	}

}
