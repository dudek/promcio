package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Shift {
		
		private long id;
		private String name;
		
		private int timeStart;
		private int timeEnd;
		
		private Company company;
		
		/* --------------------------------------- */
		@Id
		@GeneratedValue
		public long getId() {
				return id;
		}
		
		public void setId(long id) {
				this.id = id;
		}
		
		public String getName() {
				return name;
		}
		
		public void setName(String name) {
				this.name = name;
		}
		
		public int getTimeStart() {
				return timeStart;
		}
		
		public void setTimeStart(int timeStart) {
				this.timeStart = timeStart;
		}
		
		public int getTimeEnd() {
				return timeEnd;
		}
		
		public void setTimeEnd(int timeEnd) {
				this.timeEnd = timeEnd;
		}

		@ManyToOne
		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
		}
		
}
