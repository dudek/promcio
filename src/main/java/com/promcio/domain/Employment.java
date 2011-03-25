package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employment {

	 private long id;

	 // private Calendar contractStart;
	 // private Calendar contractEnd;
	 private String contractType;

	 private float salary;
	 private int hours;

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

	 public float getSalary() {
			return salary;
	 }

	 public void setSalary(float salary) {
			this.salary = salary;
	 }

	 public int getHours() {
			return hours;
	 }

	 public void setHours(int hours) {
			this.hours = hours;
	 }
}