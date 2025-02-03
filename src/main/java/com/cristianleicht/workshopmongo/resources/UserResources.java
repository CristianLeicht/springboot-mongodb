package com.cristianleicht.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cristianleicht.workshopmongo.domain.Post;
import com.cristianleicht.workshopmongo.domain.User;
import com.cristianleicht.workshopmongo.dto.UserDTO;
import com.cristianleicht.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResources {

	@Autowired
	private UserService service;

	// @RequestMapping(method=RequestMethod.GET) -> versão não pertime escrever
	// desta forma
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = service.findAll();
		List<UserDTO> listDTO = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDTO);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User obj = service.findById(id);

		return ResponseEntity.ok().body(new UserDTO(obj));
	}

	// @RequestMapping(method=RequestMethod.POST)
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDTO) {

		User obj = service.fromDTO(objDTO);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String id) {

		service.delete(id);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "{id}",method=RequestMethod.PUT)
	//@PostMapping
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id) {
		
		User obj = service.fromDTO(objDTO);
		obj.setId(id);
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

		User obj = service.findById(id);

		return ResponseEntity.ok().body(obj.getPosts());
	}

}
