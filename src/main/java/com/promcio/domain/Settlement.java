package com.promcio.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Settlement {
		
		private long id;
		
		private float workTime;
		boolean vacation;
		
		Schedule schedule;

		/* --------------------------------------- */
		
		@Id
		@GeneratedValue
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public float getWorkTime() {
			return workTime;
		}

		public void setWorkTime(float workTime) {
			this.workTime = workTime;
		}

		public boolean isVacation() {
			return vacation;
		}

		public void setVacation(boolean vacation) {
			this.vacation = vacation;
		}

		@ManyToOne
		public Schedule getSchedule() {
			return schedule;
		}

		public void setSchedule(Schedule schedule) {
			this.schedule = schedule;
		}
		
		
}
