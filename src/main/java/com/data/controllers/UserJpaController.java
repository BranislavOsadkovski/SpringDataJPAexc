/**
 * Copyright the original author or authors.
 */
package com.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.entites.User;
import com.data.entites.UserAccount;
import com.data.security.ApplicationUserRole;
import com.data.service.AccountUserService;
import com.data.service.UserJPAservice;

/**
 * @author Branislav
 *
 */
@RestController
@RequestMapping("api/v1/account")
public class UserJpaController {
	
	private final UserJPAservice service;
	private final AccountUserService accountUserService;
	@Autowired
	public UserJpaController(AccountUserService accountUserService,UserJPAservice service) {
		this.service = service;
		this.accountUserService=accountUserService;
	}
	@RequestMapping(path="/user/{userId}",method=RequestMethod.GET)
	public User getUserById(@PathVariable(name = "userId") long userId) {
		return service.findUserById(userId);
	}
	
	@RequestMapping(path = "/custom",method = RequestMethod.GET)
	public List<User> getCustom(){
		
		return service.customAll();
	}
	@RequestMapping(method = RequestMethod.GET)
	public UserAccount getUser(){
		UserAccount userAccount = new UserAccount(null, "bane", "password", ApplicationUserRole.ADMIN, true, true, true, true);
		return accountUserService.saveUserAccount(userAccount);
	}
	 
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public UserAccount saveAccount(@RequestBody UserAccount userAccount) {
		return accountUserService.saveUserAccount(userAccount);
	}
	
	
	
}
