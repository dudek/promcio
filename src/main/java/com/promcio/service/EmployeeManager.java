package com.promcio.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.promcio.domain.Employee;
import com.promcio.domain.EmployeeDetails;
import com.promcio.domain.Employment;
import com.promcio.domain.Rank;

@Stateless
public class EmployeeManager {
	 
	 @PersistenceContext
	 EntityManager em;
	 
	 public void addEmployee(String firstname, String surname, String pesel, int yob, int nip) {
			Employee employee = new Employee();
			
			employee.setFirstname(firstname);
			employee.setSurname(surname);
			employee.setPesel(pesel);
			employee.setYob(yob);
			employee.setNip(nip);
			
			em.persist(employee);
	 }
	 
	 public void removeEmplyee(long id) {
			Employee employee = em.find(Employee.class, id);
			
			em.remove(employee);
	 }
	 
	 public void addRank(String name, float hourSalary) {
			Rank rank = new Rank();
			
			rank.setName(name);
			rank.setHourSalary(hourSalary);
			
			em.persist(rank);
	 }
	 
	 public void removeRank(long id) {
			Rank rank = em.find(Rank.class, id);
			
			em.remove(rank);
	 }
	 
	 public void addEmployeeDetails(String city, String postCode, String street, String buildingNumber, 
			 	 String apartmentNumber,String staircaseNumber, String phoneNumber, String email){
		 	EmployeeDetails employeeDetails = new EmployeeDetails();
		 	
		 	employeeDetails.setCity(city);
		 	employeeDetails.setPostCode(postCode);
		 	employeeDetails.setStreet(street);
		 	employeeDetails.setBuildingNumber(buildingNumber);
		 	employeeDetails.setApartmentNumber(apartmentNumber);
		 	employeeDetails.setStaircaseNumber(staircaseNumber);
		 	employeeDetails.setPhoneNumber(phoneNumber);
		 	employeeDetails.setEmail(email);
		 	
		 	em.persist(employeeDetails);
	 }
	 
	 public void removeEmployeeDetails(long id){
		 	EmployeeDetails employeDetails = em.find(EmployeeDetails.class, id);
		 	
		 	em.remove(employeDetails);
	 }
	 
	 public void addEmployment(String contractType, float salary, int hours){
		 	Employment employment = new Employment();
		 	
		 	employment.setContractType(contractType);
		 	employment.setSalary(salary);
		 	employment.setHours(hours);
		 	
		 	em.persist(employment);
	 }
	 
	 public void removeEmployment(long id){
		 	Employment employment = em.find(Employment.class, id);
		 	
		 	em.remove(employment);
	 }
	 
	 public void joinEmployeeWithDetails(long employeeId, long employeeDetailsId) {
			Employee employee = em.find(Employee.class, employeeId);
			EmployeeDetails employeeDetails = em.find(EmployeeDetails.class, employeeDetailsId);
			
			employee.setDetails(employeeDetails);
			
			em.persist(employee);
	 }
	 
	 public void joinEmployeeWithRank(long employeeId, long rankId) {
			Employee employee = em.find(Employee.class, employeeId);
			Rank rank = em.find(Rank.class, rankId);
			
			employee.setRank(rank);
			
			em.persist(employee);
	 }
	 
	 public void joinEmployeeWithEmployment(long employeeId, long employmentId) {
			Employee employee = em.find(Employee.class, employeeId);
			Employment employment = em.find(Employment.class, employmentId);
			
			List<Employment> employments = employee.getEmployments();
			
			employments.add(employment);
			
			employee.setEmployments(employments);
			
			em.persist(employee);
	 }
}