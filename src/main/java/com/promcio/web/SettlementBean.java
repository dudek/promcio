package com.promcio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import com.promcio.domain.Employee;
import com.promcio.domain.Settlement;
import com.promcio.service.CompanyManager;
import com.promcio.service.ScheduleManager;

@ManagedBean
@ViewScoped
public class SettlementBean  implements Serializable {

		private static final long serialVersionUID = 1L;
	
	 	@Inject
	 	private ScheduleManager scheduleManager;
		@Inject
		private CompanyManager companyManager;
		@Inject
		private AccountBean accountBean;
	 	
	 	private long id;
		private int month;
		private int year;
		private float workTime;
		
		private long employeeId;
		
		private List<Employee> companyEmployees;
		
		private List<SelectItem> months;
		private List<SelectItem> years;
		
		private Employee employee; 
		
	    private List<SelectItem> selectEmployees; 
		private List<Settlement> settlements;
		
		@PostConstruct
		public void viewInit(){
			selectEmployees = new ArrayList<SelectItem>();
			companyEmployees = companyManager.getAllCompanyEmployees(accountBean.getCompany().getId());
    	    for(Employee employee : companyEmployees  ) {
    	    	selectEmployees.add(new SelectItem(employee.getId(), "" + employee.getSurname() + " " + employee.getFirstname()));
    	    } 
    	    
    	    // inicjalizacja years
			int actualYear = Calendar.getInstance().get(Calendar.YEAR);
			years = new ArrayList<SelectItem>();
			SelectItem options = new SelectItem();
			
			for (int i = 1989; i <= actualYear; i++){
				options = new SelectItem(i, ""+ i);
				years.add(options);
			}
			
			// inicjalizacja moths
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
		}
		
		
		public String getEmployeeSettlements() {
			Employee employee = new Employee();	
			for ( Employee it : companyEmployees ){
				if ( it.getId() == employeeId ){
					employee = it;
					break;
				}
			}
			
			
			settlements = new ArrayList<Settlement>();
			
			settlements.add(scheduleManager.getSettlement(employee, month, year));
			return null;
		}
		
		public List<SelectItem> getMonths() {
			return months;
		}
		
		public List<SelectItem> getYears() {
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


		public long getEmployeeId() {
			return employeeId;
		}


		public void setEmployeeId(long employeeId) {
			this.employeeId = employeeId;
		}


		public List<SelectItem> getSelectEmployees() {
			return selectEmployees;
		}


		

}
