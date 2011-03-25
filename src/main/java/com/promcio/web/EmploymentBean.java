package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;

@Model
@Named
@SessionScoped
public class EmploymentBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 // private Calendar contractStart;
	 // private Calendar contractEnd;
	 private String contractType;

	 private float salary;
	 private int hours;

	 /* --------------------------------------- */

	 public void setContractType(String contractType) {
			this.contractType = contractType;
	 }

	 public String getContractType() {
			return contractType;
	 }

	 public void setSalary(float salary) {
			this.salary = salary;
	 }

	 public float getSalary() {
			return salary;
	 }

	 public void setHours(int hours) {
			this.hours = hours;
	 }

	 public int getHours() {
			return hours;
	 }
}