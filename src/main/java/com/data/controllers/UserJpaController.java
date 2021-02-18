/**
 * Copyright the original author or authors.
 */
package com.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.entites.User;
import com.data.service.UserJPAservice;

/**
 * @author Branislav
 *
 */
@RestController
@RequestMapping("api/v2/jpa")
public class UserJpaController {
	@Autowired
	private final UserJPAservice service;

	/**
	 * @param service must not be null
	 */
	public UserJpaController(UserJPAservice service) {
		this.service = service;
	}
	@RequestMapping(path="/user/{userId}",method=RequestMethod.GET)
	public User getUserById(@PathVariable(name = "userId") long userId) {
		return service.findUserById(userId);
	}
	
	@RequestMapping(path = "/custom",method = RequestMethod.GET)
	public List<User> getCustom(){
		return service.customAll();
	}
	
	
	
	
}
