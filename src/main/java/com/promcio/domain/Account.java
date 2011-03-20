package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Account {

	 private String login;
	 private String password;
	 private int privileges;

	 private Employee employee;
	 private Company company;

	 /* --------------------------------------- */

	 @Id
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

	 public int getPrivileges() {
			return privileges;
	 }

	 public void setPrivileges(int privileges) {
			this.privileges = privileges;
	 }

	 @OneToOne
	 public Employee getEmployee() {
			return employee;
	 }

	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }

	 @OneToOne
	 public Company getCompany() {
			return company;
	 }

	 public void setCompany(Company company) {
			this.company = company;
	 }
}