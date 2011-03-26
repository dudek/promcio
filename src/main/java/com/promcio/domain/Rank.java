package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Rank {

	 private long id;

	 private String name;
	 private float hourSalary;

	 /* --------------------------------------- */

	 @Id
	 @GeneratedValue
	 public long getId() {
			return id;
	 }

	 public void setId(long id) {
			this.id = id;
	 }

	 public String getName() {
			return name;
	 }

	 public void setName(String name) {
			this.name = name;
	 }

	 public float getHourSalary() {
			return hourSalary;
	 }

	 public void setHourSalary(float hourSalary) {
			this.hourSalary = hourSalary;
	 }
}