package com.angelochiarella.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.angelochiarella.workshopmongo.domain.User;
import com.angelochiarella.workshopmongo.dto.UserDTO;
import com.angelochiarella.workshopmongo.repositories.UserRepository;
import com.angelochiarella.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService
{
	@Autowired
	private UserRepository repo;

	public List<User> findAll()
	{
		return repo.findAll();
	}

	public User findById(String id)
	{
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User obj)
	{
		return repo.insert(obj);
	}

	public User fromDTO(UserDTO objDto) // instanciado na aqui para se precisar, poderá acessar o nd
	{
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
