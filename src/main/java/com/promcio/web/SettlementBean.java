package com.promcio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

import com.promcio.domain.Employee;
import com.promcio.domain.Settlement;
import com.promcio.service.ScheduleManager;

@Model
@Named
@SessionScoped
public class SettlementBean  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 @Inject
	 ScheduleManager scheduleManager;
	 
	 	private long id;
		private int month;
		private int year;
		private float workTime;
		
		private List<SelectItem> months;
		private List<SelectItem> years;
		
		private Employee employee; 
		
		private List<Settlement> settlements;
		
		
		public String getEmployeeSettlements() {
			
			settlements = new ArrayList<Settlement>();
			
			settlements.add(scheduleManager.getSettlement(employee, month, year));
			return null;
		}
		
		public List<SelectItem> getMonths() {
			months = new ArrayList<SelectItem>();
			
			SelectItem option = new SelectItem(0, "styczen");
			months.add(option);
			option = new SelectItem(1, "luty");
			months.add(option);
			option = new SelectItem(2, "marzec");
			months.add(option);
			option = new SelectItem(3, "kwiecień");
			months.add(option);
			option = new SelectItem(4, "maj");
			months.add(option);
			option = new SelectItem(5, "czerwiec");
			months.add(option);
			option = new SelectItem(6, "lipiec");
			months.add(option);
			option = new SelectItem(7, "sierpień");
			months.add(option);
			option = new SelectItem(8, "wrzesień");
			months.add(option);
			option = new SelectItem(9, "październik");
			months.add(option);
			option = new SelectItem(10, "listopad");
			months.add(option);
			option = new SelectItem(11, "grudzień");
			months.add(option);
			
				return months;
		}
		
		public List<SelectItem> getYears() {
			
			int actualYear = Calendar.getInstance().get(Calendar.YEAR);
			years = new ArrayList<SelectItem>();
			SelectItem options = new SelectItem();
			
			for (int i = 1989; i <= actualYear; i++){
				options = new SelectItem(i, ""+ i);
				years.add(options);
			}
			
			return years;
		}
		
		public String doRedirectSettlements() {
			return "settlements?faces-redirect=true";
			 
		 }

		public void setId(long id) {
			this.id = id;
		}

		public long getId() {
			return id;
		}

		public void setMonth(int month) {
			this.month = month;
		}

		public int getMonth() {
			return month;
		}

		public void setYear(int year) {
			this.year = year;
		}

		public int getYear() {
			return year;
		}

		public void setWorkTime(float workTime) {
			this.workTime = workTime;
		}

		public float getWorkTime() {
			return workTime;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
		}

		public Employee getEmployee() {
			return employee;
		}

		public void setSettlements(List<Settlement> settlements) {
			this.settlements = settlements;
		}

		public List<Settlement> getSettlements() {
			return settlements;
		}


		

}
