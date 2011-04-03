package com.promcio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.promcio.domain.Account;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.Rank;

@Stateless
public class CompanyManager {

	 @PersistenceContext
	 EntityManager em;

	 public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
			List<T> r = new ArrayList<T>(c.size());
			for (Object o : c)
				 r.add(clazz.cast(o));
			return r;
	 }

	 public void addCompany(String name, String nip, String regon) {
			Company company = new Company();
			company.setName(name);
			company.setNip(nip);
			company.setRegon(regon);

			em.persist(company);
	 }

	 public void addCompany(String accountId, String name, String nip, String regon) {
			Account account = em.find(Account.class, accountId);
		 	Company company = new Company();
			company.setName(name);
			company.setNip(nip);
			company.setRegon(regon);

			account.setCompany(company);
			em.persist(company);
	 }
	 
	 public void removeCompany(long id) {
			Company company = em.find(Company.class, id);

			em.remove(company);
	 }
	 
	 public void addRank(String name, float hourSalary) {
			Rank rank = new Rank();

			rank.setName(name);
			rank.setHourSalary(hourSalary);

			em.persist(rank);
	 }
	 
	 public void addRank(long companyId, String name, float hourSalary){
		 	Company company = em.find(Company.class, companyId);
		 	Rank rank = new Rank();
		 	
			rank.setName(name);
			rank.setHourSalary(hourSalary);
			
			company.getRanks().add(rank);
			
			em.persist(rank);
	 }

	 public void removeRank(long id) {
			Rank rank = em.find(Rank.class, id);

			em.remove(rank);
	 }

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

	 public List<Employee> getAllCompanyEmployees(long companyId) {
			return castList(Employee.class, em.createQuery("SELECT NEW Employee(e.id, e.firstname, e.surname, e.pesel, e.nip, e.yob) FROM Employee e WHERE e.company.id ='" + companyId + "'").getResultList());
	 }
}