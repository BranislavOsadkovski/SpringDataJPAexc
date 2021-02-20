/**
 * Copyright the original author or authors.
 */
package com.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.data.entities.UserAccount;

/**
 * @author Branislav
 *
 */
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{

		UserAccount save(UserAccount userAccount);
		
		UserAccount findByUsername(String username);
	
}
