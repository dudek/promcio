package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class EmployeeDetails {

	 private long id;

	 private String city;
	 private String postCode;
	 private String street;
	 private String buildingNumber;
	 private String apartmentNumber;
	 private String staircaseNumber;
	 private String phoneNumber;
	 private String email;

	 private Employee employee;

	 /* --------------------------------------- */

	 @Id
	 @GeneratedValue
	 public long getId() {
			return id;
	 }

	 public void setId(long id) {
			this.id = id;
	 }

	 public String getCity() {
			return city;
	 }

	 public void setCity(String city) {
			this.city = city;
	 }

	 public String getPostCode() {
			return postCode;
	 }

	 public void setPostCode(String postCode) {
			this.postCode = postCode;
	 }

	 public String getStreet() {
			return street;
	 }

	 public void setStreet(String street) {
			this.street = street;
	 }

	 public String getBuildingNumber() {
			return buildingNumber;
	 }

	 public void setBuildingNumber(String buildingNumber) {
			this.buildingNumber = buildingNumber;
	 }

	 public String getApartmentNumber() {
			return apartmentNumber;
	 }

	 public void setApartmentNumber(String apartmentNumber) {
			this.apartmentNumber = apartmentNumber;
	 }

	 public String getStaircaseNumber() {
			return staircaseNumber;
	 }

	 public void setStaircaseNumber(String staircaseNumber) {
			this.staircaseNumber = staircaseNumber;
	 }

	 public String getPhoneNumber() {
			return phoneNumber;
	 }

	 public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
	 }

	 public String getEmail() {
			return email;
	 }

	 public void setEmail(String email) {
			this.email = email;
	 }

	 @OneToOne
	 public Employee getEmployee() {
			return employee;
	 }

	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }
}