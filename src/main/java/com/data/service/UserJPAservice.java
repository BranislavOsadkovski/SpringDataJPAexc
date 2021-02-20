/**
 * Copyright the original author or authors.
 */
package com.data.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.data.entities.User;
import com.data.repositories.UserJpaRepository;

/**
 * @author Branislav
 *
 */
@Service
public class UserJPAservice {
	
	
	
	private final UserJpaRepository jpaRepo;
	private final PasswordEncoder passwordEncoder;
	/**
	 * @param jpaRepo must not be null
	 */
	@Autowired
	public UserJPAservice(UserJpaRepository jpaRepo,PasswordEncoder passwordEncoder) {
		this.jpaRepo = jpaRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	@Transactional
	public User findUserById(long userId) {
		return jpaRepo.findUserById(userId);
	}
	@Transactional
	public User findUserByNameIgnoreCase(String name) {
		return jpaRepo.findUserByNameIgnoreCase(name);
	}
	@Transactional 
	public Page<User> findByName(String name,Pageable pageable){
		return jpaRepo.findByName(name, pageable);
	}
	@Transactional
	public List<User> customAll(){
		return jpaRepo.customAll();
	}

	@Transactional
	public List<User> findAll() {
		return jpaRepo.findAll();
	}
	/**
	 * @param user
	 * @return
	 */
	@Transactional
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return jpaRepo.save(user);
	}
	
	@Transactional
	public User updateById(Long id,User user) {
		user.setId(id);
		return jpaRepo.save(user);
	}
	@Transactional
	public void deleteUser(Long id) {
		
		jpaRepo.deleteById(id);
	}
	
	
	
	
}
