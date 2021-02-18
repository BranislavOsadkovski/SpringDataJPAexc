/**
 * Copyright the original author or authors.
 */
package com.data.security;

import com.google.common.collect.Sets;
import java.util.Set;
import static com.data.security.ApplicationUserPermission.*;
/**
 * @author Branislav
 *
 */
public enum ApplicationUserRole {
	STUDENT(Sets.newHashSet()),
	ADMIN(Sets.newHashSet
			(
			STUDENT_READ,STUDENT_WRITE,COURSE_READ,COURSE_WRITE
			)), 
	ADMINTRAINEE(Sets.newHashSet
			(
			COURSE_READ,STUDENT_READ
			));
	
	private final Set<ApplicationUserPermission> permissions;

	/**
	 * @param permissions must not be null
	 */
	private ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the permissions
	 */
	public Set<ApplicationUserPermission> getPermissions() {
		return permissions;
	}
	
	
	
}
