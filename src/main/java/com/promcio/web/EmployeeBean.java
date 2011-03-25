package com.promcio.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import com.promcio.domain.Employee;
import com.promcio.domain.EmployeeDetails;
import com.promcio.domain.Employment;
import com.promcio.domain.Rank;
import com.promcio.service.EmployeeManager;

@Model
@Named
@SessionScoped
public class EmployeeBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 EmployeeManager employeeManager;

	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "[A-Z][A-Za-z]+", message = "Imię powinno zaczynać się z wielkiej litery.")
	 private String firstname;

	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "[A-Z][A-Za-z]+", message = "Nazwisko powinno zaczynać się z wielkiej litery.")
	 private String surname;
	 private String pesel;
	 private String nip;
	 private int yob;

	 private EmployeeDetails details;
	 private Rank rank;
	 private List<Employment> employments;

	 /* --------------------------------------- */

	 public String getFirstname() {
			return firstname;
	 }

	 public void setFirstname(String firstname) {
			this.firstname = firstname;
	 }

	 public String getSurname() {
			return surname;
	 }

	 public void setSurname(String surname) {
			this.surname = surname;
	 }

	 public String getPesel() {
			return pesel;
	 }

	 public void setPesel(String pesel) {
			this.pesel = pesel;
	 }

	 public String getNip() {
			return nip;
	 }

	 public void setNip(String nip) {
			this.nip = nip;
	 }

	 public int getYob() {
			return yob;
	 }

	 public void setYob(int yob) {
			this.yob = yob;
	 }

	 public EmployeeDetails getDetails() {
			return details;
	 }

	 public void setDetails(EmployeeDetails details) {
			this.details = details;
	 }

	 public Rank getRank() {
			return rank;
	 }

	 public void setRank(Rank rank) {
			this.rank = rank;
	 }

	 public List<Employment> getEmployments() {
			return employments;
	 }

	 public void setEmployments(List<Employment> employments) {
			this.employments = employments;
	 }

	 /* --------------------------------------- */
	 // actions

	 public String doAddEmployee() {
			employeeManager.addEmployee(firstname, surname, pesel, nip, yob);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Pracownik dodany!"));
			return null;
	 }

	 public String doAddEmployee(long companyId) {
			employeeManager.addEmployee(companyId, firstname, surname, pesel, nip, yob);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Pracownik dodany!"));
			return null;
	 }

	 public List<Employee> getAllEmployees() {
			return employeeManager.getAllEmployees();
	 }

	 public String doRemoveEmployee(long id) {
			employeeManager.removeEmployee(id);
			return null;
	 }

	 public String doUpdateEmployee(long id) {
			employeeManager.updateEmployee(id, firstname, surname, pesel, nip, yob);
			return null;
	 }

	 public String doRedirectUpdateEmployee(long id) {
			return "editEmployee?faces-redirect=true";
	 }
}