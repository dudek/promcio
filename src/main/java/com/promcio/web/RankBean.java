package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;

@Model
@Named
@SessionScoped
public class RankBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 private String name;
	 private float hourSalary;
	 
	 /* --------------------------------------- */

	 public void setName(String name) {
			this.name = name;
	 }

	 public String getName() {
			return name;
	 }

	 public void setHourSalary(float hourSalary) {
			this.hourSalary = hourSalary;
	 }

	 public float getHourSalary() {
			return hourSalary;
	 }
}