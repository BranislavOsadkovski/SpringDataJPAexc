/**
 * Copyright the original author or authors.
 */
package com.data.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Branislav
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true,prePostEnabled = true,securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private final PasswordEncoder passwordEncoder;
	

	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
	
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http														
				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())/** CookieCsrfTokenRepository => Read Class to gain insight of how the CSRF COokie is created */
				.and()
				.authorizeRequests()
				.antMatchers("index","/","/css/*","/js/*").permitAll()
				.antMatchers("api/**").hasRole(ApplicationUserRole.STUDENT.name()) 
				// antMatcher ORDER MATTERS ide sekvencijalno!!!! 
//				.antMatchers(HttpMethod.POST,"/management/api/***").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.PUT,"/management/api/***").hasAuthority(ApplicationUserPermission.C OURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.DELETE,"/management/api/***").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())				
//				.antMatchers("/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
				
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
						.authorities(ApplicationUserRole.STUDENT.getGrantedAuthorities())
						.build();
		UserDetails mikiUser = User.builder()
				.username("miki")
				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.ADMIN.name()) //ROLE_ADMMIN
				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
				.build();
		UserDetails tomUser = User.builder()
				.username("tom")
				.password(passwordEncoder.encode("password"))
//				.roles(ApplicationUserRole.ADMINTRAINEE.name())  //ROLE_ADMINTRAINEE
				.authorities(ApplicationUserRole.ADMINTRAINEE.getGrantedAuthorities())
				.build();
		return new InMemoryUserDetailsManager(
				baneUser,mikiUser,tomUser
				);
	}

	
	
}
