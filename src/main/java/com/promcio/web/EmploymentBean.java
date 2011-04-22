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

	 
	 
	 private int period;
	 private int hoursNorm;
	 private float contractValue;
	 
	 private float hourSalary;
	 
	 private long id;
	 
	 private Employee employee;

	 /* --------------------------------------- */

	 public void setContractType(String contractType) {
			this.contractType = contractType;
	 }

	 public String getContractType() {
			return contractType;
	 }
	 
	 public void setContractValue(float contractValue) {
			this.contractValue = contractValue;
	 }

	 public float getContractValue() {
			return contractValue;
	 }

	 public int getPeriod() {
		 	return period;
	 }

	 public void setPeriod(int period) {
		 	this.period = period;
	 }

	 public int getHoursNorm() {
		 	return hoursNorm;
	 }

	 public void setHoursNorm(int hoursNorm) {
		 	this.hoursNorm = hoursNorm;
	 }

	 public float getHourSalary() {
		 	return hourSalary;
	 }

	 public void setHourSalary(float hourSalary) {
		 	this.hourSalary = hourSalary;
	 }

	 public void setEmployee(Employee employee) {
		 	this.employee = employee;
	 }

	 public Employee getEmployee() {
			return employee;
	 }
	 
	 public void setId(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}
	 
	 /* --------------------------------------- */
	 // actions
	 
	 public String doAddEmploymentEmployee(long employeeId){
		 	employeeManager.addEmployment(employeeId, contractType, contractValue, period, hoursNorm, hourSalary);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa dodana!"));
		 	return null;
	 }
	 
	 public String doRemoveEmploymentEmployee(long id){
		 	employeeManager.removeEmployment(id);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa usunięta!"));
		 	return null;
	 }
	 

	 public String doUpdateEmploymentEmployee(){
		 	employeeManager.updateEmployment(id, contractType,  contractValue, period, hoursNorm, hourSalary);
		 	FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Umowa poprawiona !"));
		 	return null;
	 }

	
}