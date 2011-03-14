package com.promcio.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.Rank;

@Stateless
public class CompanyManager {
	 
	 @PersistenceContext
	 EntityManager em;
	 
	 //TODO addCompany()
	 
	 //TODO removeCompany()
	 
	 public void joinCompanyWithEmployee(long companyId, long employeeId) {
			Company company = em.find(Company.class, companyId);
			Employee employee = em.find(Employee.class, employeeId);
			
			List<Employee> employees = company.getEmployees();
			
			employees.add(employee);
			
			company.setEmployees(employees);
			employee.setCompany(company);
			
			em.persist(company);
			em.persist(employee);
	 }
	 
	 public void joinCompanyWithRank(long companyId, long rankId) {
			Company company = em.find(Company.class, companyId);
			Rank rank = em.find(Rank.class, rankId);
			
			List<Rank> ranks = company.getRanks();
			
			ranks.add(rank);
			
			company.setRanks(ranks);
			
			em.persist(company);
	 }
}