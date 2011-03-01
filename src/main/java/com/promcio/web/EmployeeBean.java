package com.promcio.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@SessionScoped
@Named
public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String firstname;
	private String surname;
	private Integer yearOfBirth;
	private Integer pesel;
	private Integer nip;
	private Integer privileges;
	

	/*List<Employment> employments;
	
	EmployeeDetails employeeDetails;
	
	Rank rank;*/
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getSurname() {
		return surname;
	}
	public void setYearOfBirth(Integer yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public Integer getYearOfBirth() {
		return yearOfBirth;
	}
	public void setPesel(Integer pesel) {
		this.pesel = pesel;
	}
	public Integer getPesel() {
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
