package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {

	private long id;
	
	private String login;
	private String password;
	
	private int privilages;

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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPrivilages() {
		return privilages;
	}

	public void setPrivilages(int privilages) {
		this.privilages = privilages;
	}
	
	
	@OneToOne
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}	
	
	public Account() {
	}

}
