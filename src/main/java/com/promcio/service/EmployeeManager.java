package com.promcio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.promcio.domain.Calendar;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.EmployeeDetails;
import com.promcio.domain.Employment;
import com.promcio.domain.Rank;
import com.promcio.domain.Schedule;
import com.promcio.domain.Settlement;

@Stateless
public class EmployeeManager {

	 @PersistenceContext
	 EntityManager em;
	 @Inject
	 ScheduleManager scheduleManager;

	 public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
			List<T> r = new ArrayList<T>(c.size());
			for (Object o : c)
				 r.add(clazz.cast(o));
			return r;
	 }

	 public void addEmployee(String firstname, String surname, String pesel, String nip, int yob) {
			Employee employee = new Employee();

			employee.setFirstname(firstname);
			employee.setSurname(surname);
			employee.setPesel(pesel);
			employee.setNip(nip);
			employee.setYob(yob);

			em.persist(employee);
	 }

	 public void addEmployee(long companyId, String firstname, String surname, String pesel, String nip, int yob) {
			Company company = em.find(Company.class, companyId);
			Employee employee = new Employee();

			employee.setFirstname(firstname);
			employee.setSurname(surname);
			employee.setPesel(pesel);
			employee.setNip(nip);
			employee.setYob(yob);

			employee.setCompany(company);
			company.getEmployees().add(employee);

			em.persist(employee);
	 }

	 public void updateEmployee(long id, String firstname, String surname, String pesel, String nip, Integer yob) {
			Employee employee = em.find(Employee.class, id);

			if (firstname != null) employee.setFirstname(firstname);
			if (surname != null) employee.setSurname(surname);
			if (pesel != null) employee.setPesel(pesel);
			if (nip != null) employee.setNip(nip);
			if (yob != null) employee.setYob(yob);

			// em.persist(employee);
	 }

	 public void removeEmployee(long id) {
			Employee employee = em.find(Employee.class, id);
			EmployeeDetails details = employee.getDetails();
			if ((details) != null) em.remove(details);
			List<Employment> employments = em.createQuery("SELECT e FROM Employment e WHERE e.employee=:employee").setParameter("employee", employee).getResultList();
			if (employments != null){
				for (Employment employment : employments)
					 em.remove(employment);
			}
			Company company = employee.getCompany();
			if (company != null) employee.getCompany().getEmployees().remove(employee);
			
			em.createQuery("DELETE FROM Settlement s WHERE s.employee=:employee").setParameter("employee", employee).executeUpdate();
			/* Zapytanie natywane SQL ponieważ sypało wyjątkami korzystając JPQL 
			 */
			em.createNativeQuery("DELETE FROM Schedule_Employee WHERE employees_id=:id").setParameter("id", employee.getId()).executeUpdate();
			
//			ArrayList<Settlement> employeeSettlements =  (ArrayList<Settlement>) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee").setParameter("employee", employee).getResultList();
//			for ( Settlement it : employeeSettlements ){
//				it = em.find(Settlement.class, it.getId());
//				em.remove(it);
//			}
//			ArrayList<Schedule> employeeSchedules = (ArrayList<Schedule>) em.createQuery("SELECT s FROM Schedule s, IN (s.employees) e WHERE e.id=:id").setParameter("id", employee.getId()).getResultList();
//			for ( Schedule schedule : employeeSchedules ){
//				schedule = em.find(Schedule.class, schedule.getId());
//				schedule.getEmployees().remove(employee);
//			}
			
			em.remove(employee);
	 }

	 public void addEmployeeDetails(String city, String postCode, String street, String buildingNumber, String apartmentNumber, String staircaseNumber, String phoneNumber, String email) {
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

	 public void addEmployeeDetails(long employeeId, String city, String postCode, String street, String buildingNumber, String apartmentNumber, String staircaseNumber, String phoneNumber, String email) {
			Employee employee = em.find(Employee.class, employeeId);
			EmployeeDetails employeeDetails = new EmployeeDetails();

			employeeDetails.setCity(city);
			employeeDetails.setPostCode(postCode);
			employeeDetails.setStreet(street);
			employeeDetails.setBuildingNumber(buildingNumber);
			employeeDetails.setApartmentNumber(apartmentNumber);
			employeeDetails.setStaircaseNumber(staircaseNumber);
			employeeDetails.setPhoneNumber(phoneNumber);
			employeeDetails.setEmail(email);

			employeeDetails.setEmployee(employee);
			employee.setDetails(employeeDetails);
			em.persist(employeeDetails);
	 }

	 public void updateEmployeeDetails(long employeeId, String city, String postCode, String street, String buildingNumber, String apartmentNumber, String staircaseNumber, String phoneNumber, String email) {
			EmployeeDetails details = (EmployeeDetails) em.createQuery("SELECT d FROM EmployeeDetails d WHERE d.employee.id='" + employeeId + "'").getSingleResult();

			if (city != null) details.setCity(city);
			if (postCode != null) details.setPostCode(postCode);
			if (street != null) details.setStreet(street);
			if (buildingNumber != null) details.setBuildingNumber(buildingNumber);
			if (apartmentNumber != null) details.setApartmentNumber(apartmentNumber);
			if (staircaseNumber != null) details.setStaircaseNumber(staircaseNumber);
			if (phoneNumber != null) details.setPhoneNumber(phoneNumber);
			if (email != null) details.setEmail(email);

			// em.persist(details);
	 }

	 public void removeEmployeeDetails(long id) {
			EmployeeDetails employeDetails = em.find(EmployeeDetails.class, id);

			em.remove(employeDetails);
	 }

	 public void addEmployment(String contractType, float contractValue, int period, int hoursNorm, float hourSalary) {
			Employment employment = new Employment();

			employment.setContractType(contractType);
			employment.setContractValue(contractValue);
			employment.setPeriod(period);
			employment.setHoursNorm(hoursNorm);
			employment.setHourSalary(hourSalary);

			em.persist(employment);
	 }

	 public void addEmployment(long employeeId, String contractType, Date startDate, Date endDate, float contractValue, int period, int hoursNorm, float hourSalary) {
			Employee employee = em.find(Employee.class, employeeId);
			Employment employment = new Employment();
			
			if ( startDate != null && endDate != null){
				Calendar from = scheduleManager.addOrGetCalendarFromDate(startDate);
				Calendar to = scheduleManager.addOrGetCalendarFromDate(endDate);
				
				employment.setStartDate(from);
				employment.setEndDate(to);				
			}

			
			employment.setContractType(contractType);
			employment.setContractValue(contractValue);
			employment.setPeriod(period);
			employment.setHoursNorm(hoursNorm);
			employment.setHourSalary(hourSalary);

			employee.getEmployments().add(employment);
			employment.setEmployee(employee);
			em.persist(employment);
	 }

	 public void updateEmployment(long id, String contractType, Date startDate, Date endDate, float contractValue, int period, int hoursNorm, float hourSalary) {
			Employment employment = em.find(Employment.class, id);

			if ( startDate != null && endDate != null){
				Calendar from = scheduleManager.addOrGetCalendarFromDate(startDate);
				Calendar to = scheduleManager.addOrGetCalendarFromDate(endDate);
				
				employment.setStartDate(from);
				employment.setEndDate(to);				
			}
			
			employment.setContractType(contractType);
			employment.setContractValue(contractValue);
			employment.setPeriod(period);
			employment.setHoursNorm(hoursNorm);
			employment.setHourSalary(hourSalary);

			// em.persist(employment);
	 }

	 public void removeEmployment(long id) {
			Employment employment = em.find(Employment.class, id);

			Employee employee = employment.getEmployee();
			if (employee != null) employee.getEmployments().remove(employment);
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

	 public List<Employee> getAllEmployees() {
			return castList(Employee.class, em.createQuery("SELECT NEW Employee(e.id, e.firstname, e.surname, e.pesel, e.nip, e.yob) FROM Employee e").getResultList());
	 }

	 public Employee getFullEmployee(long id) {
			return (Employee) em.createQuery("SELECT DISTINCT e FROM Employee e WHERE e.id = '" + id + "'").getSingleResult();
			// return em.find(Employee.class, id);
	 }

	 public List<Employment> getEmployments(long employeeId) {
			return castList(Employment.class, em.createQuery("SELECT e FROM Employment e WHERE e.employee.id = '" + employeeId + "'").getResultList());
	 }
}