/**
 * Copyright the original author or authors.
 */
package com.data.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.data.security.ApplicationUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Branislav
 *
 */
@Entity
@Table(name = "useraccount")
public class UserAccount implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private   Long accountId;
	
	@JsonProperty
	@Column(unique = true,name = "username", length =100)
	private   String username;
	@JsonProperty
	@Column(name = "password", length =100)
	private   String password;
	
	@Transient
	private Set<? extends GrantedAuthority> authorities;
	@JsonProperty
	@Column(name = "isAccountNonExpired", length =100)
	private   boolean isAccountNonExpired;
	@JsonProperty
	@Column(name = "isAccountNonLocked", length =100)
	private   boolean isAccountNonLocked;
	@JsonProperty
	@Column(name = "isCredentialsNonExpired", length =100)
	private   boolean isCredentialsNonExpired;
	@JsonProperty
	@Column(name = "isEnabled", length =100)
	private   boolean isEnabled;
	@JsonProperty
	@Column(name="userrole", length=10)
	private ApplicationUserRole userRole;
 
	public UserAccount() {}
	public UserAccount(Long accountId, String username, String password, ApplicationUserRole userRole,
			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
			boolean isEnabled) {
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.userRole=userRole;
		this.authorities = userRole.getGrantedAuthorities();
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.isEnabled = isEnabled;
	}

 
//	public UserAccount(Long accountId, String username, String password,ApplicationUserRole userRole,
//			boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired,
//			boolean isEnabled) {
//		
//			this( accountId,  username,  password, userRole.getGrantedAuthorities(),
//					 isAccountNonExpired,  isAccountNonLocked,  isCredentialsNonExpired,
//					 isEnabled);
//	} 
	
	public Long getAccountId() {
		return accountId;
	}
	@Override
	public Set<? extends GrantedAuthority> getAuthorities() {
		return getUserRole().getGrantedAuthorities();
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public ApplicationUserRole getUserRole() {
		return userRole;
	}
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
	
}
