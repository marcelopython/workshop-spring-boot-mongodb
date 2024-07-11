package com.marcelo.workshopmongo.servicies;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.workshopmongo.domain.User;
import com.marcelo.workshopmongo.repository.UserRepository;
import com.marcelo.workshopmongo.servicies.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll() {

		return repository.findAll();
		
	}

	public User findById(String id) {
		
		
		try {
			Optional<User> userData = repository.findById(id);
			
			User user = userData.get();
			
			if(user == null) {
				
				throw new ObjectNotFoundException("Usuário não encontrado");
				
			}
			
			return user;
		}catch(NoSuchElementException e) {
			throw new ObjectNotFoundException("Usuário não encontrado");

		}

		
	}
	
}
