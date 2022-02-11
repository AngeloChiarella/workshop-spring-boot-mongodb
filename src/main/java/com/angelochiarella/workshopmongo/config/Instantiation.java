package com.angelochiarella.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.angelochiarella.workshopmongo.domain.Post;
import com.angelochiarella.workshopmongo.domain.User;
import com.angelochiarella.workshopmongo.dto.AuthorDTO;
import com.angelochiarella.workshopmongo.repositories.PostRepository;
import com.angelochiarella.workshopmongo.repositories.UserRepository;

// ***************** Objeto de instanciação da carga inicial
@Configuration
public class Instantiation implements CommandLineRunner
{
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		//Primeiro salva os usuários para terem ID.
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post1, post2));

	}
}
