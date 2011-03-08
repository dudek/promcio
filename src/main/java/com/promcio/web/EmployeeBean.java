package com.promcio.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


@SessionScoped
@Named
public @Model class EmployeeBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String surname;
	private Integer yearOfBirth;
	private String pesel;
	private Integer nip;
	private Integer privileges;
	

	/*List<Employment> employments;
	
	EmployeeDetails employeeDetails;
	
	Rank rank;*/
	
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "imie powinno zaczynać się z dużej litery")
	public String getFirstname() {
		return firstname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "nazwisko powinno zaczynać się z dużej litery")
	public String getSurname() {
		return surname;
	}
	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public String getPesel() {
		return pesel;
	}
	public void setNip(Integer nip) {
		this.nip = nip;
	}
	
	
	public Integer getNip() {
		return nip;
	}
	public void setPrivileges(Integer privileges) {
		this.privileges = privileges;
	}
	
	public Integer getPrivileges() {
		return privileges;
	}
	
	
	
	
}
