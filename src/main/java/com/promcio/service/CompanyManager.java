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
import com.promcio.domain.Shift;

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
	 
	 public void updateCompany(long id, String name, String nip, String regon){
		 	Company company = em.find(Company.class, id);
		 	company.setName(name);
		 	company.setNip(nip);
		 	company.setRegon(regon);
	 }
	 
	 public void removeCompany(long id) {
			Company company = em.find(Company.class, id);

			em.remove(company);
	 }
	 
	 public void addShift(long companyId, String name, int timeStart, int timeEnd){
		 	Shift shift = new Shift();
		 	Company company = em.find(Company.class, companyId);
		 	
		 	shift.setName(name);
		 	shift.setTimeStart(timeStart);
		 	shift.setTimeEnd(timeEnd);
		 	
		 	shift.setCompany(company);
		 	
		 	em.find(Company.class, companyId).getShifts().add(shift);
		 	
		 	em.persist(shift);
	 }
	 
	 public void updateShift(long id, String name, Integer timeStart, Integer timeEnd){
		 	Shift shift = em.find(Shift.class, id);
		 	
		 	if ( name != null )
		 		shift.setName(name);
		 	if ( timeStart != null )
		 		shift.setTimeStart(timeStart);
		 	if ( timeEnd != null )
		 		shift.setTimeEnd(timeEnd);
		 	
	 }
	 
	 public void removeShift(long id){
		 	Shift shift = em.find(Shift.class, id);
		 	
		 	em.remove(shift);
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
			//rank.setCompany(company);
			
			em.persist(rank);
	 }

	 public void updateRank(long id, String name, float hourSalary){
		 	Rank rank = em.find(Rank.class, id);
		 	
		 	rank.setName(name);
		 	rank.setHourSalary(hourSalary);
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
			return castList(Employee.class, em.createQuery("SELECT NEW Employee(e.id, e.firstname, e.surname, e.pesel, e.nip, e.yob, e.rank) FROM Employee e WHERE e.company.id ='" + companyId + "'").getResultList());
	 }
	 
	 public List<Shift> getAllCompanyShifts(long companyId){
		 	return castList(Shift.class, em.createQuery("SELECT DISTINCT s FROM Shift s WHERE s.company.id='" + companyId + "'").getResultList());
	 }
	 
	 public List<Rank> getAllCompanyRanks(long companyId){
		 	//return castList(Rank.class, em.find(Company.class, companyId).getRanks());
		 	return castList(Rank.class, em.createQuery("SELECT DISTINCT r FROM Rank r WHERE r.company.id='" + companyId + "'").getResultList());
	 }
	 
	 public Company getFullCompany(long id){
		 	return em.find(Company.class, id);
	 }
}