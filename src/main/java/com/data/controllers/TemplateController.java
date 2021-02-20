/**
 * Copyright the original author or authors.
 */
package com.data.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Branislav
 *
 */
@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView() {
		return "login";
	}

	@GetMapping("courses")
	public String getCourseView() {
		return "courses";
	}
}
