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
import com.promcio.domain.Company;
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
	 @Inject
	 EmployeeDetailsBean employeeDetailsBean;

	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[A-Z][a-zA-Z]+$")
	 private String firstname;

	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[A-Z][a-zA-Z]+$")
	 private String surname;

	 private String pesel;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^([0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}|[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{3})$")
	 private String nip;
	 private Integer yob;

	 private EmployeeDetails details;
	 private Rank rank;
	 private List<Employment> employments;
	 private Company company;

	 private Long updId;

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

	 public void setCompany(Company company) {
			this.company = company;
	 }

	 public Company getCompany() {
			return company;
	 }

	 public void setUpdId(long updId) {
			this.updId = updId;
	 }

	 public long getUpdId() {
			return updId;
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

	 public String doUpdateEmployee() {
			employeeManager.updateEmployee(updId, firstname, surname, pesel, nip, yob);
			employeeDetailsBean.doUpdateEmployeeDetails(updId);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Pracownik wyedytowany ;D"));
			doClearFields();
			return null;
	 }

	 public String doRedirectUpdateEmployee(long id) {
		 	Employee employee = employeeManager.getFullEmployee(id);
		 	this.updId = employee.getId();
		 	this.firstname = employee.getFirstname();
		 	this.surname = employee.getSurname();
		 	this.pesel = employee.getPesel();
		 	this.nip = employee.getNip();
		 	this.yob = employee.getYob();
		 	this.details = employee.getDetails();
		 	
		 	if (employee.getDetails() == null) {
		 		employeeDetailsBean.doAddEmployeeDetails(updId);
		 	}
		 	else {
		 	employeeDetailsBean.setApartmentNumber(employee.getDetails().getApartmentNumber());
		 	employeeDetailsBean.setBuildingNumber(employee.getDetails().getBuildingNumber());
		 	employeeDetailsBean.setCity(employee.getDetails().getCity());
		 	employeeDetailsBean.setEmail(employee.getDetails().getEmail());
		 	employeeDetailsBean.setPhoneNumber(employee.getDetails().getPhoneNumber());
		 	employeeDetailsBean.setPostCode(employee.getDetails().getPostCode());
		 	employeeDetailsBean.setStaircaseNumber(employee.getDetails().getStaircaseNumber());
		 	employeeDetailsBean.setStreet(employee.getDetails().getStreet());
		 	}
			return "editEmployee?faces-redirect=true";
	 }
	 
	 public void doClearFields()	{
		 this.updId = null;
		 this.firstname = null;
		 this.surname = null;
		 this.pesel = null;
		 this.nip = null;
		 this.yob = null;
		 this.details = null;
		 
	 }
}