package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shift {
		
		private long id;
		private String name;
		
		private int timeStart;
		private int timeEnd;
		
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
		
		public int getTimeStop() {
				return timeEnd;
		}
		
		public void setTimeStop(int timeEnd) {
				this.timeEnd = timeEnd;
		}
}
