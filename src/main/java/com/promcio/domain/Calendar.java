package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Calendar {

		private long id;
		
		private int year;
		private int month;
		private int day;

		/* --------------------------------------- */
		
		@Id
		@GeneratedValue
		public long getId() {
				return id;
		}
		
		public void setId(long id) {
				this.id = id;
		}
		
		public int getYear() {
				return year;
		}
		
		public void setYear(int year) {
				this.year = year;
		}
		
		public int getMonth() {
				return month;
		}
		
		public void setMonth(int month) {
				this.month = month;
		}
		
		public int getDay() {
				return day;
		}
		
		public void setDay(int day) {
				this.day = day;
		}
		
}
