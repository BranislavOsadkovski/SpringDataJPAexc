/**
 * Copyright the original author or authors.
 */
package com.data.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.data.entites.User;

/**
 * @author Branislav
 *
 */
@Repository
public interface UserDAOCrudRepository extends CrudRepository<User, Long>{

		public void delete(User user);
		public void deleteById(Long id);
		public User save(User user);
		public Iterable<User> findAll();
		public Optional<User> findById(Long id); 
		
}
