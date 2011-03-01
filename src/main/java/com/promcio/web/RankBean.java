package com.promcio.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@SessionScoped
@Named
public class RankBean  implements Serializable{

	private static final long serialVersionUID = -6961978690202962358L;
	
	
	private String name;
	private float hourSalary;

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
