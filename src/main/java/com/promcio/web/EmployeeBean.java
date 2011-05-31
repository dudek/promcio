package com.promcio.web;

import java.io.Serializable;
import java.util.Date;
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
import com.promcio.domain.Calendar;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.EmployeeDetails;
import com.promcio.domain.Employment;
import com.promcio.domain.Rank;
import com.promcio.service.CompanyManager;
import com.promcio.service.EmployeeManager;
import com.promcio.service.ScheduleManager;
import com.promcio.service.SearchManager;

@Model
@Named
@SessionScoped
public class EmployeeBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 EmployeeManager employeeManager;
	 @Inject
	 CompanyManager companyManager;
	 @Inject
	 ScheduleManager scheduleManager;
	 @Inject
	 SearchManager searchManager;

	 @Inject
	 EmployeeDetailsBean employeeDetailsBean;
	 @Inject
	 EmploymentBean employmentBean;

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
	 private Employee selectedEmployee;

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

	 public Integer getYob() {
			return yob;
	 }

	 public void setYob(Integer yob) {
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

	 public Employee getSelectedEmployee() {
			return selectedEmployee;
	 }

	 public void setSelectedEmployee(Employee selectedEmployee) {
			this.selectedEmployee = selectedEmployee;
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
			employmentBean.doUpdateEmploymentEmployee();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO:", "Pracownik wyedytowany ;D"));
			// doClearFields();
			return null;
	 }

	 public String doRedirectAddEmployee() {
			doClearFields();
			return "addEmployee?faces-redirect=true";

	 }

	 public String doRedirectUpdateEmployee(long id) {
			Employee employee = employeeManager.getFullEmployee(id);
			this.updId = employee.getId();
			this.firstname = employee.getFirstname();
			this.surname = employee.getSurname();
			this.pesel = employee.getPesel();
			this.nip = employee.getNip();
			this.yob = employee.getYob();
			this.rank = employee.getRank();

			if (employee.getDetails() == null) {
				 employeeDetailsBean.doAddEmployeeDetails(updId);
			} else {
				 employeeDetailsBean.setApartmentNumber(employee.getDetails().getApartmentNumber());
				 employeeDetailsBean.setBuildingNumber(employee.getDetails().getBuildingNumber());
				 employeeDetailsBean.setCity(employee.getDetails().getCity());
				 employeeDetailsBean.setEmail(employee.getDetails().getEmail());
				 employeeDetailsBean.setPhoneNumber(employee.getDetails().getPhoneNumber());
				 employeeDetailsBean.setPostCode(employee.getDetails().getPostCode());
				 employeeDetailsBean.setStaircaseNumber(employee.getDetails().getStaircaseNumber());
				 employeeDetailsBean.setStreet(employee.getDetails().getStreet());
			}

			if (employeeManager.getEmployments(employee.getId()).size() <= 0) {
				 employmentBean.doAddEmploymentEmployee(updId);
				 employmentBean.setId(employeeManager.getEmployments(employee.getId()).get(0).getId());
			} else {
				 Employment employment = employeeManager.getEmployments(employee.getId()).get(0);

				 Calendar startDate = employment.getStartDate();
				 Calendar endDate = employment.getEndDate();
				 Date from;
				 Date to;

				 if (startDate != null && endDate != null) {
						from = scheduleManager.calendarToDate(startDate);
						to = scheduleManager.calendarToDate(endDate);
				 } else {
						from = null;
						to = null;
				 }
				 employmentBean.setStartDate(from);
				 employmentBean.setEndDate(to);
				 employmentBean.setContractType(employment.getContractType());
				 employmentBean.setHoursNorm(employment.getHoursNorm());
				 employmentBean.setContractValue(employment.getContractValue());
				 employmentBean.setPeriod(employment.getPeriod());
				 employmentBean.setHourSalary(employment.getHourSalary());
				 employmentBean.setId(employment.getId());
			}

			return "editEmployee?faces-redirect=true";
	 }

	 public String doRedirectEmployeeDetails(long id) {
			Employee employee = employeeManager.getFullEmployee(id);
			this.updId = id;
			this.firstname = employee.getFirstname();
			this.surname = employee.getSurname();
			this.pesel = employee.getPesel();
			this.nip = employee.getNip();
			this.yob = employee.getYob();
			this.details = employee.getDetails();
			this.rank = employee.getRank();
			// this.employments = employee.getEmployments();

			if ( this.details != null ){
				employeeDetailsBean.setApartmentNumber(this.details.getApartmentNumber());
				employeeDetailsBean.setBuildingNumber(this.details.getBuildingNumber());
				employeeDetailsBean.setCity(this.details.getCity());
				employeeDetailsBean.setEmail(this.details.getEmail());
				employeeDetailsBean.setPhoneNumber(this.details.getPhoneNumber());
				employeeDetailsBean.setPostCode(this.details.getPostCode());
				employeeDetailsBean.setStaircaseNumber(this.details.getStaircaseNumber());
				employeeDetailsBean.setStreet(this.details.getStreet());
			}
			return "employeeDetails?faces-redirect=true";
	 }

	 public List<Employment> getAllEmployments() {
			return employeeManager.getEmployments(this.updId);
	 }

	 public void doClearFields() {
			this.updId = null;
			this.firstname = null;
			this.surname = null;
			this.pesel = null;
			this.nip = null;
			this.yob = null;
			this.details = null;
	 }
}