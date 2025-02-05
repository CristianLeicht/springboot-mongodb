package com.cristianleicht.workshopmongo.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianleicht.workshopmongo.domain.Post;
import com.cristianleicht.workshopmongo.repository.PostRepository;
import com.cristianleicht.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;


	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);

		if (!user.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}

		return user.get();

	}
	
	public List<Post> findByTitle(String text){
		
		return repo.searchTitle(text);
	}
	
	public List<Post> fullSearch(String text, Date minDate, Date maxDate){
		
		//para fazer a comparacao de datas, adicionar um dia a data máxima
		maxDate = new Date(maxDate.getTime() + 86400000);
		return repo.fullSearch(text, minDate, maxDate);
		
	}


}
