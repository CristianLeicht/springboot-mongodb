package com.cristianleicht.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristianleicht.workshopmongo.domain.User;
import com.cristianleicht.workshopmongo.repository.UserRepository;
import com.cristianleicht.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		return repo.findAll();
		
	}
	
	public User findById(String id) {
		Optional<User> user  = repo.findById(id);
		
		if(!user.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return user.get();
		
	}

}
