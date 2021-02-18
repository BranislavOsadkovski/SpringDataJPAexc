/**
 * Copyright the original author or authors.
 */
package com.data.entites;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table; 
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty; 

/**
 * @author Branislav
 */
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Long id;

	@JsonProperty
	@NotNull(message = "please enter name")
	@Column(name = "name", length = 50)
	@Size(max = 50, min = 3, message = "user.name.invalid")
	private String name;

	@JsonProperty
	@NotNull(message = "please enter lastname")
	@Column(name = "lastname", length = 100)
	@Size(max = 100, min = 3, message = "user.lastname.invalid")
	private String lastname;

	@JsonProperty
	@NotNull(message = "please enter password")
	@Column(name = "password", length = 100)
	@Size(max = 100, min = 15, message = "user.password.invalid")
	private String password;

	@JsonProperty
	@Email(message = "user.email.invalid")
	@Column(name = "email", length = 30)
	private String email;

	@JsonProperty
	@OneToOne
	private Address address;

	public User() {
	}
//	@Column(name = "creation_time", nullable = false)
//	@Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentZonedDateTime")
//	@CreatedDate
//	private ZonedDateTime creationTime;
//
//	@LastModifiedDate
//	private ZonedDateTime modificationTime;

	/**
	 * @param id
	 * @param name
	 * @param lastname
	 * @param password
	 * @param email
	 * @param address  must not be null
	 */
	public User(Long id, String name, String lastname, String password, String email, Address address) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
 
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPassword() {
		return password;
	}
	
	
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", address=" + address + "]";
	}

}
