package com.marcelo.workshopmongo.servicies;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcelo.workshopmongo.config.DTO.UserDTO;
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
	
	
	public User insert(User user) {
		return repository.insert(user);
	}
	
	public Void delete(String id) {
		
		repository.deleteById(id);
		return null;
		
	}
	
	public User update(User user) {
		
		User userData = findById(user.getId());
		updateData(userData, user);
		return repository.save(userData);
		
	}
	
	private void updateData(User userData, User user){
		
		userData.setEmail(user.getEmail());
		userData.setName(user.getName());
		
	}
	
	public User fromDTO(UserDTO userDTO) {
		
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
		
	}
	
}
