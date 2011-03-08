package com.promcio.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

@SessionScoped
@Named
public class EmployeeDetailsBean implements Serializable {

	 private static final long serialVersionUID = 1L;
	
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
	

	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "Miasto musi zaczynac sie z duzej litery")
	public String getCity() {
		return city;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[0-9][0-9]-[0-9][0-9][0-9]", message = "Kod pocztowy nie pasuje do wzorca [0-9][0-9]-[0-9][0-9][0-9]")
	public String getPostCode() {
		return postCode;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "Ulica musi zaczynac sie z duzej litery")
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
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[0-9]+-[0-9]+-[0-9]+", message = "Numer nie pasuje do wzorca [0-9]+-[0-9]+-[0-9]+")
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[a-z.]+@[a-z]+.[a-z]+", message = "Email nie pasuje do wzorca [a-z]+@[a-z]+.[a-z]+")
	public String getEmail() {
		return email;
	}
	
	

}
