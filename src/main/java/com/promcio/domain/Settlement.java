package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/*
 * Zmieniona koncepcja, teraz obiekt Settlement odpowiada za miesieczne rozliczenie pracownika, narazie tylko 
 * informacje o przepracowanych godzinach.
 */

@Entity
public class Settlement {
		
		private long id;
		/* Jak w obiekcie Calendar od 0 do 11 */
		private int month;
		private int year;
		private float workTime;
		
		private Employee employee;
		
//		private float workTime;
//		boolean vacation;
//		
//		Schedule schedule;
//
		/* --------------------------------------- */
		
		@Id
		@GeneratedValue
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public int getMonth() {
			return month;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public float getWorkTime() {
			return workTime;
		}

		public void setWorkTime(float workTime) {
			this.workTime = workTime;
		}

		@ManyToOne
		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		
		
}
