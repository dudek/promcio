package com.promcio.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.promcio.domain.Calendar;
import com.promcio.domain.Company;
import com.promcio.domain.Employee;
import com.promcio.domain.Schedule;
import com.promcio.domain.Settlement;
import com.promcio.domain.Shift;

/**
 * Odczyt i zapis do bazy danych informacje na temat grafikow pracowikow oraz
 * metody pomocniczne.
 * @author michal
 *
 */

@Stateless
public class ScheduleManager {

	@PersistenceContext
	EntityManager em;
	
	public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
		List<T> r = new ArrayList<T>(c.size());
		for (Object o : c)
			 r.add(clazz.cast(o));
		return r;
	}
	
	public List<Schedule> getAllEmployeeSchedules(long employeeId){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s, IN (s.employees) e WHERE e.id='"+employeeId +"'").getResultList());
	}
	
	public List<Schedule> getAllEmployeeMonthSchedules(long employeeId, int month, int year){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s inner join s.employees e WHERE e.id='"+employeeId +"' AND s.calendar.month='"+month+"' AND s.calendar.year='"+year+"'").getResultList());
	}
	

	public List<Schedule> getAllCompanySchedules(Company company, int month, int year ){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s join fetch s.employees WHERE s.company=:company AND s.calendar.month='"+month+"' AND s.calendar.year='"+year+"'").setParameter("company", company).getResultList());
	}
	
	public Schedule getSchedule(Shift shift, Calendar calendar){
		try{
			calendar = em.merge(calendar);
		}
		catch(IllegalArgumentException e){
			calendar = em.find(Calendar.class, calendar.getId());
		}
		try{
			return (Schedule) em.createQuery("SELECT s FROM Schedule s WHERE s.shift=:shift AND s.calendar=:calendar").setParameter("shift", shift).setParameter("calendar", calendar).getSingleResult();
		}catch(NoResultException noResultException){
			return null;
		}
	}
/*	public List<Schedule> getAllCompanySchedules(long companyId){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s, IN (s.employees) e WHERE e.company.id='"+companyId+"'").getResultList());
	}
	
	public List<Schedule> getAllCompanyMonthSchedules(long companyId, int month){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s WHERE s.calendar.month='"+month+"', IN (s.employees) e WHERE e.company.id='"+companyId+"'").getResultList());
	}*/

	/**
	 * Testowa metoda, poniewaz najwidoczniej Hibernate nie obsluguje wiecej niz 1 wartosciowania zachlannego w aplikacji dla list
	 * trzeba jakas pobierac z bazy danych listy powiazanych atrybutow.
	 * Rozwiazanie znalezione tu: http://stackoverflow.com/questions/463349/jpa-eager-fetch-does-not-join/504617#504617.
	 * Jesli nie bedzie dzialac to trzeba bedzie zrobi to klasycznie.
	 * Metoda zwraca liste pracownikow, ktorzy maja tego dnia taki sam grafik.
	 * @param schedule
	 * @return lista obiektow typu <Employee> powiazanych z tym obiektem Schedule.
	 */
	public List<Employee> getAllScheduleEmployees(Schedule schedule){
		em.merge(schedule);
		return schedule.getEmployees();
	}
	
	public Schedule addSchedule(Company company, Shift shift, Date dateCalendar){
		Calendar calendar = dateToCalendar(dateCalendar);
		try{
			calendar = (Calendar) em.createQuery("SELECT c FROM Calendar c WHERE c.year='"+ calendar.getYear() +"' AND c.month='"+ calendar.getMonth()+"' AND c.day='"+ calendar.getDay() +"'" ).getSingleResult();
		}
		catch(NoResultException noResultException){
			try{
				em.persist(calendar);
			}
			catch(IllegalArgumentException illegalArgumentException){
				calendar = dateToCalendar(dateCalendar);
				em.persist(calendar);
			}
		}
		
		Schedule schedule;
		
		try{
			schedule = (Schedule) em.createQuery("SELECT s FROM Schedule s WHERE s.company=:company AND s.shift=:shift AND s.calendar=:calendar").setParameter("company", company).setParameter("shift", shift).setParameter("calendar", calendar).getSingleResult(); 
		}
		catch(NoResultException noResultException){
			schedule = new Schedule();
//			schedule.setRealTimeStart(realTimeStart);
//			schedule.setRealTimeEnd(realTimeEnd);
//			company = em.merge(company);
			schedule.setCompany(company);
			schedule.setShift(shift);
			schedule.setCalendar(calendar);
			schedule.setEmployees(new ArrayList<Employee>());
		
			em.persist(schedule);
			em.flush();
		}
		return schedule;
	}
	
	public Schedule addSchedule(Company company, Shift shift, Calendar calendar){
		try{
			calendar = (Calendar) em.createQuery("SELECT c FROM Calendar c WHERE c.year='"+ calendar.getYear() +"' AND c.month='"+ calendar.getMonth()+"' AND c.day='"+ calendar.getDay() +"'" ).getSingleResult();
		}
		catch(NoResultException noResultException){
			em.persist(calendar);
		}
		
		Schedule schedule;
		
		try{
			schedule = (Schedule) em.createQuery("SELECT s FROM Schedule s WHERE s.company=:company AND s.shift=:shift AND s.calendar=:calendar").setParameter("company", company).setParameter("shift", shift).setParameter("calendar", calendar).getSingleResult(); 
		}
		catch(NoResultException noResultException){
			schedule = new Schedule();
			calendar = em.merge(calendar);
//			schedule.setRealTimeStart(realTimeStart);
//			schedule.setRealTimeEnd(realTimeEnd);
//			company = em.merge(company);
			schedule.setCompany(company);
			schedule.setShift(shift);
			schedule.setCalendar(calendar);
			schedule.setEmployees(new ArrayList<Employee>());
		
			em.persist(schedule);
			//em.flush();
		}
		return schedule;
	}
	
	public void addEmployeeToSchedule(Schedule schedule, Employee employee){
		schedule = em.merge(schedule);
		//employee = em.merge(employee);
			
		schedule.getEmployees().add(employee);
	}
	
	public void removeEmployeeFromSchedule(Schedule schedule, Employee employee){
		schedule = em.merge(schedule);
		employee = em.find(Employee.class, employee.getId());
		
		schedule.getEmployees().remove(employee);
		
		if ( schedule.getEmployees().size() < 1 )
			em.remove(schedule);
	}
	
	public void addCalendarFromDate(Date date){
		Calendar calendar = dateToCalendar(date);
		em.persist(calendar);
	}
	
	public Calendar addOrGetCalendarFromDate(Date date){
		Calendar calendar = dateToCalendar(date);
		try{
			calendar = (Calendar) em.createQuery("SELECT c FROM Calendar c WHERE c.year='"+ calendar.getYear() +"' AND c.month='"+ calendar.getMonth()+"' AND c.day='"+ calendar.getDay() +"'" ).getSingleResult();
		}
		catch(NoResultException noResultException){
			em.persist(calendar);
		}
		return calendar;
	}
	
	public void addCalendar(int day, int month, int year){
		Calendar calendar = new Calendar();
		calendar.setDay(day);
		calendar.setMonth(month);
		calendar.setYear(year);
		
		em.persist(calendar);
	}
	
	public Settlement addSettlementForEmployee(Employee employee, int month, int year){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month AND s.year=:year").setParameter("employee", employee).setParameter("month", month).setParameter("year", year).getSingleResult();
			return settlement;
		}catch ( NoResultException e ){
			settlement = new Settlement();
		
			settlement.setMonth(month);
			settlement.setYear(year);
			settlement.setEmployee(employee);
			settlement.setWorkTime(0);
			
			em.persist(settlement);
			return settlement;
		}
	}
	
	
	public Settlement getSettlement(Employee employee, int month, int year){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month AND s.year=:year").setParameter("employee", employee).setParameter("month", month).setParameter("year", year).getSingleResult();
			return settlement;
		}catch ( NoResultException e ){
			return null;
		}
	}
	
	public void addWorkTimeToSettlement(Employee employee, int month, int year, float workTime){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month AND s.year=:year").setParameter("employee", employee).setParameter("month", month).setParameter("year", year).getSingleResult();
		}catch ( NoResultException e){
			settlement = addSettlementForEmployee(employee, month, year);
		}
		em.merge(settlement);
		settlement.setWorkTime(settlement.getWorkTime()+workTime);
		
	}
	
	public void addWorkTimeToSettlement(Employee employee, int month, int year, Shift shift){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month AND s.year=:year").setParameter("employee", employee).setParameter("month", month).setParameter("year", year).getSingleResult();
		}catch ( NoResultException e){
			settlement = addSettlementForEmployee(employee, month, year);
		}
		em.merge(settlement);
		settlement.setWorkTime(settlement.getWorkTime() + getWorkTimeFromShift(shift));
		
	}
	
	public void removeWorkTimeFromSettlement(Employee employee, int month, int year, float workTime){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month").setParameter("employee", employee).setParameter("month", month).getSingleResult();
		}catch ( NoResultException e){
			settlement = addSettlementForEmployee(employee, month, year);
		}
		em.merge(settlement);
		float newWorkTime = settlement.getWorkTime() - workTime;
		if ( newWorkTime >= 0  )
			settlement.setWorkTime(newWorkTime);
		else
			settlement.setWorkTime(0);
	}
	
	public void removeWorkTimeFromSettlement(Employee employee, int month, int year, Shift shift){
		Settlement settlement;
		try{
			settlement = (Settlement) em.createQuery("SELECT s FROM Settlement s WHERE s.employee=:employee AND s.month=:month").setParameter("employee", employee).setParameter("month", month).getSingleResult();
		}catch ( NoResultException e){
			settlement = addSettlementForEmployee(employee, month, year);
		}
		em.merge(settlement);
		float newWorkTime = settlement.getWorkTime() - getWorkTimeFromShift(shift);
		if ( newWorkTime >= 0  )
			settlement.setWorkTime(newWorkTime);
		else
			settlement.setWorkTime(0);
	}

	
	public float getWorkTimeFromShift(Shift shift){
		int timeStart = shift.getTimeStart();
		int timeEnd = shift.getTimeEnd();
		int delta = timeEnd - timeStart;
		float workTime = ( delta ) > 0 ? delta/100 : (-delta)/100;
		float minutes = ( (workTime - new Float(workTime).intValue()) * 100 )/60;
		return new Float(workTime).intValue() + minutes;
	}

	/** 
	 * Metoda zamienia obiekt typu java.util.Date na obiekt com.promcio.domain.Calendar.
	 * @param date
	 * @return obiekt typu com.promcio.domain.Calendar z polami odpowiadajacy dacie reprezentowanej przez obiekt typu java.util.Date.
	 */
	public Calendar dateToCalendar(Date date){
		Calendar domCalendar = new Calendar();
		java.util.Calendar utilCalendar = java.util.Calendar.getInstance();
		
		utilCalendar.setTime(date);
		domCalendar.setYear(utilCalendar.get(java.util.Calendar.YEAR));
		domCalendar.setMonth(utilCalendar.get(java.util.Calendar.MONTH));
		domCalendar.setDay(utilCalendar.get(java.util.Calendar.DAY_OF_MONTH));
		return domCalendar;
	}
	
	/**
	 * Metoda zamienia obiekt typu com.promcio.domain.Calendar na obiekt typu java.util.Date.
	 * @param domCalendar
	 * @return obiekt typu java.util.Date z polami odpowiadajacymi dacie reprezentowanej przez obiekt typu com.promcio.domain.Calendar.
	 */
	public Date calendarToDate(Calendar domCalendar){
		java.util.Calendar utilCalendar = java.util.Calendar.getInstance();
		
		utilCalendar.set(java.util.Calendar.YEAR, domCalendar.getYear());
		utilCalendar.set(java.util.Calendar.MONTH, domCalendar.getMonth());
		utilCalendar.set(java.util.Calendar.DAY_OF_MONTH, domCalendar.getDay());
		
		return utilCalendar.getTime();
	}
}
