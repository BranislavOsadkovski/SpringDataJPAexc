/**
 * Copyright the original author or authors. 
 */
package com.data.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.data.entities.User;

/**
 * @author Branislav
 *
 */
public interface UserJpaRepository extends JpaRepository<User, Long> {

	User findUserById(Long userId);
	User findUserByNameIgnoreCase(String name);
	
	List<User> findAll();
	
	Page<User> findByName(String name,Pageable pageable);
	
	User save(User user);
	
	@Query(value = "select u.password,u.lastname,u.id,u.name,u.email,u.address_id from Users u", nativeQuery = true)
	List<User> customAll();
	
	
	
	void deleteById(Long id);
}
