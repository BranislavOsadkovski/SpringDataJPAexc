/**
 * Copyright the original author or authors.
 */
package com.data.repositories;

import java.util.List; 
import org.springframework.data.repository.RepositoryDefinition;

import com.data.entities.User;

/**
 * @author Branislav
 *
 */

@RepositoryDefinition(domainClass = User.class, idClass = Long.class)
public interface UserDAORepository/*extends Repository<User,Long> */ {
	
	public User save(User user);
	
	public void delete(User user);
	
	public void deleteById(Long id);
	public User getUserByNameIgnoreCase(String name); 
	public List<User> findAll();
	
	public User getUserByEmail(String email);
 
	public List<User> findByName(String username);

}
