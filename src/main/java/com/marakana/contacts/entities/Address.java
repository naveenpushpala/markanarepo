package com.marakana.contacts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address extends BaseEntity{

	
	@Column
	private String Street;
	@Column
	private String city;
	@Column
	private String state;
	@Column
	private String zip;
	
	public Address(){}
	
	public Address(String street, String city, String state, String zip) {
		super();
		this.Street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	
	

	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
}
 
