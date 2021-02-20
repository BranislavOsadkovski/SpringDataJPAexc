/**
 * Copyright the original author or authors.
 */
package com.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Branislav
 *
 */
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private final long id;
	@Column(name = "address1", length = 100)
	private final String address1;
	@Column(name = "address2", length = 100)
	private final String address2;
	@ManyToOne
	private final City city;
	
	/**
	 * @param address1
	 * @param address2
	 * @param city     must not be null
	 */
	public Address(long id, String address1, String address2,City city) {
		super();
		this.id = id;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

}
