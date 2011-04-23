package com.promcio.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Schedule {
	
		private long id;
		
//		private int realTimeStart;
//		private int realTimeEnd;
		
		List<Employee> employees;
		Shift shift;
		Calendar calendar;
		
		/* --------------------------------------- */
		
		@Id
		@GeneratedValue
		public long getId() {
				return id;
		}
		
		public void setId(long id) {
				this.id = id;
		}
		
//		public int getRealTimeStart() {
//				return realTimeStart;
//		}
//		
//		public void setRealTimeStart(int realTimeStart) {
//				this.realTimeStart = realTimeStart;
//		}
//		
//		public int getRealTimeEnd() {
//				return realTimeEnd;
//		}
//		
//		public void setRealTimeEnd(int realTimeEnd) {
//				this.realTimeEnd = realTimeEnd;
//		}
		
		@ManyToMany
		public List<Employee> getEmployees() {
				return employees;
		}
		
		public void setEmployees(List<Employee> employees) {
				this.employees = employees;
		}
		@ManyToOne
		public Shift getShift() {
				return shift;
		}
		
		public void setShift(Shift shift) {
				this.shift = shift;
		}
		@ManyToOne
		public Calendar getCalendar() {
				return calendar;
		}
		
		public void setCalendar(Calendar calendar) {
				this.calendar = calendar;
		}
}
