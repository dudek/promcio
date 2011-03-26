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
import com.promcio.domain.Employee;
import com.promcio.service.EmployeeManager;

@Model
@Named
@SessionScoped
public class EmploymentBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 EmployeeManager employeeManager;
	 
	 // private Calendar contractStart;
	 // private Calendar contractEnd;
	 
	 @NotNull
	 @NotEmpty
	 private String contractType;

	 @NotNull
	 @NotEmpty
	 private float salary;
	 
	 @NotNull
	 @NotEmpty
	 private int hours;
	 
	 private Employee employee;

	 /* --------------------------------------- */

	 public void setContractType(String contractType) {
			this.contractType = contractType;
	 }

	 public String getContractType() {
			return contractType;
	 }

	 public void setSalary(float salary) {
			this.salary = salary;
	 }

	 public float getSalary() {
			return salary;
	 }

	 public void setHours(int hours) {
			this.hours = hours;
	 }

	 public int getHours() {
			return hours;
	 }
	 
	 public void setEmployee(Employee employee) {
			this.employee = employee;
	 }

	 public Employee getEmployee() {
			return employee;
	 }
	 
	 /* --------------------------------------- */
	 // actions
	 
	 public String doAddEmploymentEmployee(long employeeId){
		 	employeeManager.addEmployment(employeeId, contractType, salary, hours);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa dodana!"));
		 	return null;
	 }
	 
	 public String doRemoveEmploymentEmployee(long id){
		 	employeeManager.removeEmployment(id);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa usunięta!"));
		 	return null;
	 }
	 
	 public String doUpdateEmploymentEmployee(long id){
		 	employeeManager.updateEmployment(id, contractType, salary, hours);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa poprawiona !"));
		 	return null;
	 }
}