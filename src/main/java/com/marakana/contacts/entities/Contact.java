package com.marakana.contacts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Contact {

	public Contact(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public Contact(){}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String name;
	
	@OneToOne
	private Address address;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}	
	
}
