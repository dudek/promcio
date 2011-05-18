package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Account {

	 private String login;
	 private String password;

	 private Role role;

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

	 @ManyToOne
	 public Role getRole() {
			return role;
	 }

	 public void setRole(Role role) {
			this.role = role;
	 }

	 @OneToOne(mappedBy = "account")
	 public Employee getEmployee() {
			return employee;
	 }

	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }

	 @OneToOne(mappedBy = "account")
	 public Company getCompany() {
			return company;
	 }

	 public void setCompany(Company company) {
			this.company = company;
	 }
}