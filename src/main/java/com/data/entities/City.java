/**
 * Copyright the original author or authors.
 */
package com.data.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Branislav
 *
 */
@Entity
public class City {

	@Id
	private final int id;
	@Column(name="name",length = 50)
	private final String name;
	
	@ManyToOne
	private final Country country;

	public City(int id, String name, Country country) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the country
	 */
	public Country getCountry() {
		return country;
	}
 
}
