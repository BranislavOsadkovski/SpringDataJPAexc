/**
 * Copyright the original author or authors.
 */
package com.data.security;

/**
 * @author Branislav
 *
 */
public enum ApplicationUserPermission {
		STUDENT_WRITE("student:write"),
		STUDENT_READ("student:read"),
		COURSE_READ("course:read"),
		COURSE_WRITE("course:write");
	
	private final String permission;

	/**
	 * @param permission must not be null
	 */
	private ApplicationUserPermission(String permission) {
		this.permission = permission;
	}

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}
	
	
	
}
