package com.charliecwb.springbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.charliecwb.springbootmongodb.domain.Post;
import com.charliecwb.springbootmongodb.domain.User;
import com.charliecwb.springbootmongodb.dto.AuthorDTO;
import com.charliecwb.springbootmongodb.dto.CommentDTO;
import com.charliecwb.springbootmongodb.dto.UserDetailDTO;
import com.charliecwb.springbootmongodb.repositories.PostRepository;
import com.charliecwb.springbootmongodb.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "+5541988482727");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "+5541988482727");
		User u3 = new User(null, "Bob Grey", "bob@gmail.com", "+5541988482727");
					
		UserDetailDTO l1 = new UserDetailDTO("maria_brown", "123456", true);
		UserDetailDTO l2 = new UserDetailDTO("alex_green", "123456", false);
		UserDetailDTO l3 = new UserDetailDTO("bob_grey", "123456", false);
		
		u1.setLogin(l1);
		u2.setLogin(l2);
		u3.setLogin(l3);
		userRepository.saveAll(Arrays.asList(u1, u2, u3));		
		
		Post p1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo", new AuthorDTO(u1));
		Post p2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Estoy em sampa!", new AuthorDTO(u1));
			
		CommentDTO c1 = new CommentDTO("Boa viagem!", sdf.parse("21/03/2018"), new AuthorDTO(u2));
		CommentDTO c2 = new CommentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(u3));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(u2));
		
		p1.getComments().addAll(Arrays.asList(c1, c2));
		p2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(p1, p2));		
		
		u1.getPosts().addAll(Arrays.asList(p1, p2));
		userRepository.save(u1);
	}	
}