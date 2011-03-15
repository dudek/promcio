package com.promcio.web;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;

import com.promcio.domain.Employee;

@SessionScoped
@Named
public @Model class AccountBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String password;
	
	private int privilages;

	private Employee employee;

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPrivilages(int privilages) {
		this.privilages = privilages;
	}

	public int getPrivilages() {
		return privilages;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

}
