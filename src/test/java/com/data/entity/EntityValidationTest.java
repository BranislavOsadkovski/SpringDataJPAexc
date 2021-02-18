/**
 * Copyright the original author or authors.
 */
package com.data.entity;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test; 

import java.util.Set; 
import com.data.entites.User; 

/**
 * @author Branislav
 *
 */  
public class EntityValidationTest {

	User user = null;
	String VALIDATE_NAME="user.name.invalid";
	String VALIDATE_LAST_NAME="user.lastname.invalid";
	String VALIDATE_PASSWORD="user.password.invalid";
	String VALIDATE_EMAIL="user.email.invalid";
 
	
 	Validator validator;
	   
	@BeforeEach
	public void initTest() {
		validator = Validation.buildDefaultValidatorFactory().getValidator();
	}
	
	@Test
	public void userTest() {
		
		User user = new User(null, "22","la","two","email", null);

		Set<ConstraintViolation<User>> violations = validator.validate(user);
		Assert.assertEquals(false, violations.isEmpty());
	}
	 
}
