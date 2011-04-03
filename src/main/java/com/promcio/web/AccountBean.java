package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.Role;
import com.promcio.service.AccountManager;

@Model
@Named
@SessionScoped
public class AccountBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 AccountManager accountManager;
	 @Inject
	 CompanyBean companyBean;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[a-zA-Z][!-~]*$", message = "Invalid login")
	 private String login;

	 @NotNull
	 @NotEmpty
	 private String password;
	 private String newPassword;

	 private Role role;

	 private Employee employee;
	 private Company company;

	 private long type;
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

	 public String getNewPassword() {
			return newPassword;
	 }

	 public void setNewPassword(String newPassword) {
			this.newPassword = newPassword;
	 }

	 public Role getRole() {
			return role;
	 }

	 public void setRole(Role role) {
			this.role = role;
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
	 
	 public long getType() {
			return type;
	 }

	 public void setType(long type) {
			this.type = type;
	 }

	 /* --------------------------------------- */
	 // actions

	public String signIn() {
			if (accountManager.signIn(login, password)) {
				 this.role = accountManager.getAccount(login).getRole();
				 this.employee = accountManager.getAccount(login).getEmployee();
				 this.company = accountManager.getAccount(login).getCompany();
				 this.isLogged = true;
				 this.password = null; // wyrzucamy haslo z pamieci

				 return "home?faces-redirect=true";
			}

			this.login = null;
			this.password = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Błędne hasło!"));

			return null;
	 }

	 public String signOut() {
			this.login = null;
			this.password = null;
			this.role = null;
			this.employee = null;
			this.company = null;
			this.isLogged = false;

			return "home?faces-redirect=true";
	 }

	 public String doAddAccount() {
			if (accountManager.addAccount(login, password, type)) {
				 if (type == 2)
					 companyBean.doAddCompany(login);
				 this.login = null;
				 this.password = null; // wyrzucamy haslo z pamieci
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Konto utworzone!"));
			} else {
				 this.login = null;
				 this.password = null;
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Login zajety!"));
			}
			return null;
	 }

	 public String doChangePassword() {
			if (accountManager.changePassword(login, password, newPassword)) {
				 this.password = null;
				 this.newPassword = null;
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Hasło zmienione!"));
			} else {
				 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR:", "Błędne hasło!"));
			}
			return null;
	 }
}