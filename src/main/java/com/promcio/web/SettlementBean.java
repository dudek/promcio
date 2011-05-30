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
import com.promcio.domain.Employment;
import com.promcio.domain.Settlement;
import com.promcio.service.CompanyManager;
import com.promcio.service.EmployeeManager;
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
		private EmployeeManager employeeManager;
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
		private List<GeneratedSettlement> generatedSettlements;
		
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
		
		
		public void getEmployeeSettlements() {
			employee = new Employee();	
			for ( Employee it : companyEmployees ){
				if ( it.getId() == employeeId ){
					employee = it;
					break;
				}
			}
			
			
			settlements = new ArrayList<Settlement>();
			Settlement settlement = scheduleManager.getSettlement(employee, month, year);
			if ( settlement != null )
				settlements.add(settlement);
		}
		
		public void generate(){
			this.getEmployeeSettlements();
			this.generatedSettlements = new ArrayList<GeneratedSettlement>();
			Employment employment = employeeManager.getEmployments(employeeId).get(0);
			
			for ( Settlement it : this.settlements){
				GeneratedSettlement gs = new GeneratedSettlement(employee, it, employment);
				generatedSettlements.add(gs);
			}
		}
		
		public List<SelectItem> getMonths() {
			return months;
		}
		
		public List<SelectItem> getYears() {
			return years;
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

		
		
		public List<GeneratedSettlement> getGeneratedSettlements() {
			return generatedSettlements;
		}


		public void setGeneratedSettlements(
				List<GeneratedSettlement> generatedSettlements) {
			this.generatedSettlements = generatedSettlements;
		}



		/* Klasa pomocnicza, sluzy jako kontener obliczonych informacji na temat rozliczenia danego pracownika za dany miesiac */
		public class GeneratedSettlement{
			int month;
			int year;
			
			int period;
			
			float monthHours;
			float periodHours;
			
			int periodHoursTarget;
			int monthHoursTarget;
			
			float monthOvertime;
			
			public GeneratedSettlement(Employee employee, Settlement settlement, Employment employment){
				this.month = settlement.getMonth();
				this.year = settlement.getYear();
				
				this.period = employment.getPeriod();
				this.monthHours = settlement.getWorkTime();		
				this.periodHoursTarget = employment.getHoursNorm();
				
				switch ( this.period ){
					case 1:  
						this.monthHoursTarget = employment.getHoursNorm();
						this.periodHours = settlement.getWorkTime();						
					break;
					case 3:
						this.monthHoursTarget = employment.getHoursNorm()/this.period;
						this.periodHours = settlement.getWorkTime();
						Settlement temp;
						if ( this.month >= 0 && this.month < 3  ){
							for ( int i = 0; i < 3; i++){
								if ( i != this.month){
									temp = scheduleManager.getSettlement(employee, i, this.year);
									if ( temp != null )
										this.periodHours += temp.getWorkTime();
								}
							}
						}
						else if ( this.month >= 3 && this.month < 6){
							for ( int i = 3; i < 6; i++){
								if ( i != this.month ){
									temp = scheduleManager.getSettlement(employee, i, this.year);
									if ( temp != null )
										this.periodHours += temp.getWorkTime();
								}
							}
						}
						else if ( this.month >= 6 && this.month < 9){
							for ( int i = 6; i < 9; i++){
								if ( i != this.month ){
									temp = scheduleManager.getSettlement(employee, i, this.year);
									if ( temp != null )
										this.periodHours += temp.getWorkTime();
								}
							}
						}
						else if ( this.month >= 9 && this.month < 12){
							for ( int i = 9; i < 12; i++){
								if ( i != this.month ){
									temp = scheduleManager.getSettlement(employee, i, this.year);
									if ( temp != null )
										this.periodHours += temp.getWorkTime();
								}
							}
						}
					break;
				}
				
				this.monthOvertime = this.monthHours - this.monthHoursTarget;
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

			public int getPeriod() {
				return period;
			}

			public void setPeriod(int period) {
				this.period = period;
			}

			public float getMonthHours() {
				return monthHours;
			}

			public void setMonthHours(float monthHours) {
				this.monthHours = monthHours;
			}

			public float getPeriodHours() {
				return periodHours;
			}

			public void setPeriodHours(float periodHours) {
				this.periodHours = periodHours;
			}

			public int getPeriodHoursTarget() {
				return periodHoursTarget;
			}

			public void setPeriodHoursTarget(int periodHoursTarget) {
				this.periodHoursTarget = periodHoursTarget;
			}

			public int getMonthHoursTarget() {
				return monthHoursTarget;
			}

			public void setMonthHoursTarget(int monthHoursTarget) {
				this.monthHoursTarget = monthHoursTarget;
			}

			public float getMonthOvertime() {
				return monthOvertime;
			}

			public void setMonthOvertime(float monthOvertime) {
				this.monthOvertime = monthOvertime;
			}
			
		}

}
