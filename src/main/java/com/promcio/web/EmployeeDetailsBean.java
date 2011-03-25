package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import com.promcio.domain.Employee;

@Model
@Named
@SessionScoped
public class EmployeeDetailsBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Pattern(regexp = "^[A-Z][A-Za-z]+$")
	 private String city;

	 @Pattern(regexp = "^[0-9]{2}-[0-9]{3}$")
	 private String postCode;

	 @Pattern(regexp = "^[A-Z][A-Za-z]+$")
	 private String street;
	 
	 private String buildingNumber;
	 private String apartmentNumber;
	 private String staircaseNumber;

	 @Pattern(regexp = "^[0-9]{3}-[0-9]{3}-[0-9]{3}$")
	 private String phoneNumber;

	 @Pattern(regexp = "^[a-z]+@[a-z]+.[a-z]+$")
	 private String email;

	 private Employee employee;

	 /* --------------------------------------- */

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

	 public Employee getEmployee() {
			return employee;
	 }

	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }
}