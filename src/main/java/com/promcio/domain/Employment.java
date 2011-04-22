package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employment {

	 private long id;

	 // private Calendar contractStart;
	 // private Calendar contractEnd;
	 private String contractType;
	 
	 private int period;
	 private int hoursNorm;
	 private float contractValue;
	 
	 private float hourSalary;

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

	 public String getContractType() {
			return contractType;
	 }

	 public void setContractType(String contractType) {
			this.contractType = contractType;
	 }

	 public float getContractValue() {
		 	return contractValue;
	 }

	 public void setContractValue(float contractValue) {
		 	this.contractValue = contractValue;
	 }

	 public int getPeriod() {
		 	return period;
	 }

	 public void setPeriod(int period) {
		 	this.period = period;
	 }

	 public int getHoursNorm() {
			return hoursNorm;
	 }

	 public void setHoursNorm(int hoursNorm) {
			this.hoursNorm = hoursNorm;
	 }

	 public float getHourSalary() {
		return hourSalary;
	 }

	 public void setHourSalary(float hourSalary) {
		this.hourSalary = hourSalary;
	 }

	@ManyToOne
	 public Employee getEmployee() {
		 	return employee;
	 }

	 public void setEmployee(Employee employee) {
		 	this.employee = employee;
	 }
}