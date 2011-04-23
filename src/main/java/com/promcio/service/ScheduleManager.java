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
import com.promcio.domain.Employee;
import com.promcio.domain.Schedule;
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
	
	/**
	 * @param employeeId
	 * @return Zwraca liste obiektow Schedule dla pracownika o danym id.
	 */
	public List<Schedule> getAllEmployeeSchedules(long employeeId){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s, IN (s.employees) e WHERE e.id='"+employeeId +"'").getResultList());
	}
	
	/**
	 * @param employeeId
	 * @param month
	 * @return lista obiektor Schedule dla pracownika o danym id w danym miesiacu
	 */
	public List<Schedule> getAllEmployeeMonthSchedules(long employeeId, int month){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s WHERE s.calendar.month='"+month+"', IN (s.employees) e WHERE e.id="+employeeId +"'").getResultList());
	}
	
	/**
	 * Wszystkie grafiki dla danej firmy.
	 * @param companyId
	 * @return Zwraca liste obiektow Schedule dla Company o danym id.
	 */
	public List<Schedule> getAllCompanySchedules(long companyId){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s, IN (s.employees) e WHERE e.company.id='"+companyId+"'").getResultList());
	}
	
	public List<Schedule> getAllCompanyMonthSchedules(long companyId, int month){
		return castList(Schedule.class, em.createQuery("SELECT DISTINCT s FROM Schedule s WHERE s.calendar.month='"+month+"', IN (s.employees) e WHERE e.company.id='"+companyId+"'").getResultList());
	}

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
	
	/**
	 * Metoda dodaje do bazy danych obiekt typu Schedule biorac odpowiednie parametry. Pracownicy sa dodawani w osobnej metodzie.
	 * @param shift
	 * @param dateCalendar
	 */
	public void addSchedule(Shift shift, Date dateCalendar){
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
		Schedule schedule = new Schedule();
//		schedule.setRealTimeStart(realTimeStart);
//		schedule.setRealTimeEnd(realTimeEnd);
		schedule.setShift(shift);
		schedule.setCalendar(calendar);
		
		em.persist(schedule);
	}
	
	public void addEmployeeToSchedule(Schedule schedule, Employee employee){
		em.merge(schedule);
		em.merge(employee);
		schedule.getEmployees().add(employee);
	}
	
	public void addCalendarFromDate(Date date){
		Calendar calendar = dateToCalendar(date);
		em.persist(calendar);
	}
	
	public void addCalendar(int day, int month, int year){
		Calendar calendar = new Calendar();
		calendar.setDay(day);
		calendar.setMonth(month);
		calendar.setYear(year);
		
		em.persist(calendar);
	}
	/*TODO
	 * Prawdopodobnie przydadza sie metody, ktore beda zwracaly List<Schedule> w danym przedziale czasowym.
	 */
	
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
