package com.promcio.web;

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
import com.promcio.service.EmployeeManager;


@SessionScoped
@Named
public @Model class EmployeeBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	EmployeeManager employeeManager;
	
	private String firstname;
	private String surname;
	private String pesel;
	private String nip;
	private Integer yob;
	private Integer privileges;
	

	/*List<Employment> employments;
	
	EmployeeDetails employeeDetails;
	
	Rank rank;*/
	
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "imie powinno zaczynać się z dużej litery")
	public String getFirstname() {
		return firstname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@NotNull
	@NotEmpty
	@Pattern(regexp = "[A-Z][A-Za-z]+", message = "nazwisko powinno zaczynać się z dużej litery")
	public String getSurname() {
		return surname;
	}
	public void setYob(Integer yob) {
		this.yob = yob;
	}
	
	public Integer getYob() {
		return yob;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public String getPesel() {
		return pesel;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	
	
	public String getNip() {
		return nip;
	}
	public void setPrivileges(Integer privileges) {
		this.privileges = privileges;
	}
	
	public Integer getPrivileges() {
		return privileges;
	}
	
	public String doAddEmployee(){
		
		employeeManager.addEmployee(firstname, surname, pesel, nip, yob);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Info:", "Pracownik dodany!"));
		return null;
	} 
	
	public List<Employee> getAllEmployees(){
		return employeeManager.getAllEmployees();
	}
	 
	
}
