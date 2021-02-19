/**
 * Copyright the original author or authors.
 */
package com.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

	private final UserJPAservice service;

	/**
	 * @param service must not be null
	 */
	@Autowired
	public StudentManagementController(UserJPAservice service) {
		super();
		this.service = service;
	}
//	PreAuthorize				 hasRole('ROLE_')  hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
	
	@RequestMapping(method = RequestMethod.GET)
	@PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_ADMINTRAINEE')")
	public List<User> findAllUsers(){
		return service.findAll();
	}

	
	@RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)	
	@PreAuthorize("hasAuthority('student:write')")
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	
	@RequestMapping(method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,path = "{id}")
	@PreAuthorize("hasAuthority('student:write')")
	public User updateUser(@PathVariable(name = "id")Long id, @RequestBody User user) {
		
		return service.updateById(id, user);
	}
	@PreAuthorize("hasAuthority('student:write')")	
	@RequestMapping(method = RequestMethod.DELETE,path = "{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		service.deleteUser(id);
		
	}
}
