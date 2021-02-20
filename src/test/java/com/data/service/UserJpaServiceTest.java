/**
 * Copyright the original author or authors.
 */
package com.data.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.data.entities.User;
import com.data.repositories.UserJpaRepository;

/**
 * @author Branislav
 *
 */

public class UserJpaServiceTest {

	@InjectMocks
	UserJPAservice service;

	@Mock
	UserJpaRepository repo;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	
	@Test
	public void getAllUsers_test() {
		User user = new User(null, "branislav", "osadkovski", "password", "osadkovski.b@gmail.com", null);
		List<User> list = new ArrayList<>();
		list.add(user);

		when(repo.findAll()).thenReturn(list);

		List<User> test = service.findAll();

		assertEquals("branislav", test.get(0).getName());

	}

}
