/**
 * Copyright the original author or authors.
 */
package com.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.data.entities.UserAccount;
import com.data.repositories.UserAccountRepository;

/**
 * @author Branislav
 *
 */
@Service
public class AccountUserService implements UserDetailsService {

	private final UserAccountRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	public AccountUserService(UserAccountRepository repo,PasswordEncoder passwordEncoder) {
		this.passwordEncoder=passwordEncoder;
		this.repo = repo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAccount acc =repo.findByUsername(username); 
		
		return acc;
	}

	public UserAccount saveUserAccount(UserAccount userAccount) {
		userAccount = new UserAccount(userAccount.getAccountId(), userAccount.getUsername(), passwordEncoder.encode(userAccount.getPassword()),	 userAccount.getUserRole(), userAccount.isAccountNonExpired(), userAccount.isAccountNonLocked(), userAccount.isCredentialsNonExpired(), userAccount.isEnabled());
		return repo.save(userAccount);
	}
	
}
