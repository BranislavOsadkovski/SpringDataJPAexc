/**
 * Copyright the original author or authors.
 */
package com.data.service;

import java.util.List; 

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.data.entites.User;
import com.data.repositories.UserDAORepository;

/**
 * @author Branislav
 *
 */
@Service
public class UserService {
 
	private final UserDAORepository userRepo;
	
	@Autowired
	public UserService(   UserDAORepository userRepo) {
		  
		this.userRepo = userRepo;
	}
	
	@Transactional
	public void deleteById(Long id) {
		userRepo.deleteById(id);
		
	}
	 

	@Transactional
	public User saveRepoUser(User user) {return userRepo.save(user);}
	
	@Transactional
	public List<User> getRepoUsers() {return userRepo.findAll();}

	@Transactional
	public List<User> findByName(String username) {
		
		return userRepo.findByName(username);
	}
	@Transactional
	public void delete(User user) {
		userRepo.delete(user);
		
	}

}
