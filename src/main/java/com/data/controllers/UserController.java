/**
 * Copyright the original author or authors.
 */
package com.data.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.data.entites.User;
import com.data.service.UserService;

/**
 * @author Branislav
 *
 *
 */
//@RestController
//@RequestMapping(path = "api/v1/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	} 
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	public void saveRepoUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
	}

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public void saveRepoUser(@RequestBody User user) {
		userService.delete(user);

	}

	@RequestMapping(path = "/find/{username}", method = RequestMethod.GET)
	public List<User> findByName(@PathVariable("username") String username) {
		List<User> users = userService.findByName(username);
		return users;

	}


//	@RequestMapping(path = "/repo/{username}/{email}", method = RequestMethod.GET)
//	public User saveRepoUser(@PathVariable("username") String username, @PathVariable("email") String email) {
//		User user = new User(null, username, email, null);
//		return userService.saveRepoUser(user);
//	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		return userService.getRepoUsers();
	}

}
