/**
 * Copyright the original author or authors.
 */
package com.data.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Branislav
 *
 */
@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final int id;
	@Column(name = "name", length = 50)
	private final String name;

	/**
	 * @param name must not be null
	 */
	public Country(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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

}
