/**
 * Copyright the original author or authors.
 */
package com.data.security;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.data.service.AccountUserService;

/**
 * @author Branislav
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true,prePostEnabled = true,securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

	
	private final PasswordEncoder passwordEncoder;
	private final AccountUserService accountUserService;
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,AccountUserService accountUserService) {
		this.accountUserService=accountUserService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
			http														
//				.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())/** CookieCsrfTokenRepository => Read Class to gain insight of how the CSRF COokie is created */
//				.and()
				.csrf().disable()	
				.authorizeRequests()
//				.antMatchers("index","/","/css/*","/js/*").permitAll()
				.antMatchers("/api/**").permitAll()
				// antMatcher ORDER MATTERS ide sekvencijalno!!!! 
//				.antMatchers(HttpMethod.POST,"/management/api/***").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.PUT,"/management/api/***").hasAuthority(ApplicationUserPermission.C OURSE_WRITE.getPermission())
//				.antMatchers(HttpMethod.DELETE,"/management/api/***").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())				
//				.antMatchers("/management/api/**").hasAnyRole(ApplicationUserRole.ADMIN.name(),ApplicationUserRole.ADMINTRAINEE.name())
				
				.anyRequest()
				.authenticated()
				.and()
				.formLogin()
					.loginPage("/login")
					.permitAll()
					.defaultSuccessUrl("/courses", true)
					.passwordParameter("password")
					.usernameParameter("username")
				.and()
				.rememberMe()
					.tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
					.key("keysecured")
					.rememberMeParameter("remember-me")
				.and()
				.logout()
					.logoutUrl("/logout")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET")) // if csrf is enabled logout method should be POST considered best practice against csrf atacks
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID","remember-me")
					.logoutSuccessUrl("/login");
	}


	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(accountUserService);
		return provider;
	}
	
	
	
}
