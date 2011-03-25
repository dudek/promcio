package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.service.AccountManager;

@Model
@Named
@SessionScoped
public class AccountBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 AccountManager accountManager;

	 @NotNull
	 @NotEmpty
	 private String login;

	 @NotNull
	 @NotEmpty
	 private String password;
	 private int privileges;

	 private Employee employee;
	 private Company company;

	 private boolean isLogged = false;

	 /* --------------------------------------- */

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

	 public void setPrivileges(int privileges) {
			this.privileges = privileges;
	 }

	 public int getPrivileges() {
			return privileges;
	 }

	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }

	 public Employee getEmployee() {
			return employee;
	 }

	 public Company getCompany() {
			return company;
	 }

	 public void setCompany(Company company) {
			this.company = company;
	 }

	 public boolean isLogged() {
			return isLogged;
	 }

	 /* --------------------------------------- */
	 // actions

	 public String signIn() {
			if (accountManager.signIn(login, password)) {
				 this.company = accountManager.getAccount(login).getCompany();
				 this.employee = accountManager.getAccount(login).getEmployee();
				 this.isLogged = true;
			}
			return "home?faces-redirect=true";
	 }

	 public String signOut() {
			this.isLogged = false;
			this.login = null;
			this.password = null;
			this.company = null;
			this.employee = null;

			return "home?faces-redirect=true";
	 }

	 public String doAddAccount() {
			accountManager.addAccount(login, password);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Konto utworzone!"));
			return null;
	 }
}