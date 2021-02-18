/**
 * Copyright the original author or authors.
 */
package com.data.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author Branislav
 *
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private final PasswordEncoder passwordEncoder;
	

	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
	
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http
			.csrf().disable()
				.authorizeRequests()
				.antMatchers("index","/","/css/*","/js/*").permitAll()
				.antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
				.antMatchers(HttpMethod.POST,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers(HttpMethod.PUT,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers(HttpMethod.DELETE,"/management/api/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.name())
				.antMatchers("/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
				.anyRequest()
				.authenticated()
				.and()
				.httpBasic();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails baneUser = User.builder()
						.username("bane")
						.password(passwordEncoder.encode("password"))
//						.roles(ApplicationUserRole.STUDENT.name()) //ROLE_STUDENT
						.build();
		UserDetails mikiUser = User.builder()
				.username("miki")
				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMMIN
				.build();
		UserDetails tomUser = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.ADMINTRAINEE.name())  //ROLE_ADMINTRAINEE
				.build();
		return new InMemoryUserDetailsManager(
				baneUser,mikiUser,tomUser
				);
	}

	
	
}
