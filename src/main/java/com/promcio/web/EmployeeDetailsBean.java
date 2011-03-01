package com.promcio.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class EmployeeDetailsBean implements Serializable {

	private static final long serialVersionUID = -2966477448613458981L;
	
	private String city;
	private String postCode;
	private String street;
	private String buildingNumber;
	private String apartmentNumber;
	private String staircaseNumber;
	private String phoneNumber;
	private String email;
	
	
	
	
	public void setCity(String city) {
		this.city = city;
	}
	public String getCity() {
		return city;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet() {
		return street;
	}
	public void setBuildingNumber(String buildingNumber) {
		this.buildingNumber = buildingNumber;
	}
	public String getBuildingNumber() {
		return buildingNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setStaircaseNumber(String staircaseNumber) {
		this.staircaseNumber = staircaseNumber;
	}
	public String getStaircaseNumber() {
		return staircaseNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	

}
