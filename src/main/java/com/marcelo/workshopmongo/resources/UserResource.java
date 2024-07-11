package com.marcelo.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.workshopmongo.config.DTO.UserDTO;
import com.marcelo.workshopmongo.domain.User;
import com.marcelo.workshopmongo.servicies.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> fundAll(){
		
		List<User> users = service.findAll();
		
		List<UserDTO> userTDO = users.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(userTDO);
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){
		
		User user = service.findById(id);
		
		return ResponseEntity.ok().body(new UserDTO(user));
		
	}
	
}
