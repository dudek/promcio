package com.promcio.service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.promcio.domain.Employee;
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
}