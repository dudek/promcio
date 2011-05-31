package com.promcio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
import com.promcio.service.SearchManager;

@ManagedBean
@ViewScoped
public class SearchBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 private ScheduleManager scheduleManager;
	 @Inject
	 private CompanyManager companyManager;
	 @Inject
	 private EmployeeManager employeeManager;
	 @Inject
	 private AccountBean accountBean;

	 // --------
	 @Inject
	 SearchManager searchManager;

	 private String value;
	 private String indexing;

	 private List<Employee> employees = new ArrayList<Employee>();
	 // --------

	 private int actualDay;
	 private int actualMonth;
	 private int actualYear;

	 private int month;
	 private int year;
	 private int overtimeOrNot;

	 private List<SelectItem> months;
	 private List<SelectItem> years;
	 private List<SelectItem> overtimeOrNotList;

	 private List<Employee> companyEmployees;
	 private List<EmployeeSettlements> employeeSettlements;
	 private List<Employment> employments;

	 private int numberOfEmployees;

	 /* --------------------------------------- */

	 @PostConstruct
	 public void viewInit() {
			companyEmployees = companyManager.getAllCompanyEmployees(accountBean.getCompany().getId());
			for (Employee e : companyEmployees) {
				 employments = employeeManager.getEmployments(e.getId());
				 if (employments != null) e.setEmployments(employments);
			}
			numberOfEmployees = companyEmployees.size();
			
			actualDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			actualMonth = Calendar.getInstance().get(Calendar.MONTH);
			actualYear = Calendar.getInstance().get(Calendar.YEAR);

			System.out.println("\n" + actualDay + "/" + actualMonth + "/" + actualYear + "\n");

			// inicjalizacja overtimeOrNotList
			overtimeOrNotList = new ArrayList<SelectItem>();
			overtimeOrNotList.add(new SelectItem(0, "Nadgodziny"));
			overtimeOrNotList.add(new SelectItem(1, "Brakujące godziny"));

			// inicjalizacja years
			years = new ArrayList<SelectItem>();

			for (int i = 2002; i <= actualYear; i++) {
				 years.add(new SelectItem(i, "" + i));
			}

			// inicjalizacja moths
			months = new ArrayList<SelectItem>();

			months.add(new SelectItem(0, "styczen"));
			months.add(new SelectItem(1, "luty"));
			months.add(new SelectItem(2, "marzec"));
			months.add(new SelectItem(3, "kwiecień"));
			months.add(new SelectItem(4, "maj"));
			months.add(new SelectItem(5, "czerwiec"));
			months.add(new SelectItem(6, "lipiec"));
			months.add(new SelectItem(7, "sierpień"));
			months.add(new SelectItem(8, "wrzesień"));
			months.add(new SelectItem(9, "październik"));
			months.add(new SelectItem(10, "listopad"));
			months.add(new SelectItem(11, "grudzień"));
	 }

	 /*
	  * Klasa pomocnicza, sluzy jako kontener obliczonych informacji na temat
	  * rozliczenia pracownikow za dany miesiac
	  */
	 public class EmployeeSettlements {

			private Employee employee;
			private Settlement settlement;
			private Employment employment;

			private int month;
			private int year;

			private int period;

			private float monthHours;
			private float periodHours;

			// private int periodHoursTarget;
			private int monthHoursTarget;

			private float monthOvertime;

			public EmployeeSettlements(Employee e, Settlement s) {
				 this.employee = e;
				 this.settlement = s;
				 this.employment = employeeManager.getEmployments(this.employee.getId()).get(0);

				 this.month = s.getMonth();
				 this.year = s.getYear();

				 this.period = this.employment.getPeriod();
				 this.monthHours = this.settlement.getWorkTime();
				 // this.periodHoursTarget = employment.getHoursNorm();

				 switch (this.period) {
						case 1:
							 this.monthHoursTarget = this.employment.getHoursNorm();
							 this.periodHours = this.settlement.getWorkTime();
							 break;
						case 3:
							 this.monthHoursTarget = this.employment.getHoursNorm() / this.period;
							 this.periodHours = this.settlement.getWorkTime();
							 Settlement temp;
							 if (this.month >= 0 && this.month < 3) {
									for (int i = 0; i < 3; i++) {
										 if (i != this.month) {
												temp = scheduleManager.getSettlement(employee, i, this.year);
												if (temp != null) this.periodHours += temp.getWorkTime();
										 }
									}
							 } else if (this.month >= 3 && this.month < 6) {
									for (int i = 3; i < 6; i++) {
										 if (i != this.month) {
												temp = scheduleManager.getSettlement(employee, i, this.year);
												if (temp != null) this.periodHours += temp.getWorkTime();
										 }
									}
							 } else if (this.month >= 6 && this.month < 9) {
									for (int i = 6; i < 9; i++) {
										 if (i != this.month) {
												temp = scheduleManager.getSettlement(employee, i, this.year);
												if (temp != null) this.periodHours += temp.getWorkTime();
										 }
									}
							 } else if (this.month >= 9 && this.month < 12) {
									for (int i = 9; i < 12; i++) {
										 if (i != this.month) {
												temp = scheduleManager.getSettlement(employee, i, this.year);
												if (temp != null) this.periodHours += temp.getWorkTime();
										 }
									}
							 }
							 break;
				 }

				 this.monthOvertime = this.monthHours - this.monthHoursTarget;
			}

			public Employee getEmployee() {
				 return employee;
			}

			public void setEmployee(Employee employee) {
				 this.employee = employee;
			}

			public int getPeriod() {
				 return period;
			}

			public void setPeriod(int period) {
				 this.period = period;
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

	 /* --------------------------------------- */

	 public String getValue() {
			return value;
	 }

	 public void setValue(String value) {
			this.value = value;
	 }

	 public List<Employee> getEmployees() {
			return employees;
	 }

	 public void setEmployees(List<Employee> employees) {
			this.employees = employees;
	 }

	 public void setIndexing(String indexing) {
			this.indexing = indexing;
	 }

	 // ------

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

	 public int getOvertimeOrNot() {
			return overtimeOrNot;
	 }

	 public void setOvertimeOrNot(int overtimeOrNot) {
			this.overtimeOrNot = overtimeOrNot;
	 }

	 public List<SelectItem> getMonths() {
			return months;
	 }

	 public void setMonths(List<SelectItem> months) {
			this.months = months;
	 }

	 public List<SelectItem> getYears() {
			return years;
	 }

	 public void setYears(List<SelectItem> years) {
			this.years = years;
	 }

	 public List<EmployeeSettlements> getEmployeeSettlements() {
			return employeeSettlements;
	 }

	 public void setEmployeeSettlements(List<EmployeeSettlements> employeeSettlements) {
			this.employeeSettlements = employeeSettlements;
	 }

	 public List<SelectItem> getOvertimeOrNotList() {
			return overtimeOrNotList;
	 }

	 public void setOvertimeOrNotList(List<SelectItem> overtimeOrNotList) {
			this.overtimeOrNotList = overtimeOrNotList;
	 }

	 public List<Employee> getCompanyEmployees() {
	 	 return companyEmployees;
	 }

	 public void setCompanyEmployees(List<Employee> companyEmployees) {
	 	 this.companyEmployees = companyEmployees;
	 }

	 public int getNumberOfEmployees() {
			return numberOfEmployees;
	 }

	 public void setNumberOfEmployees(int numberOfEmployees) {
			this.numberOfEmployees = numberOfEmployees;
	 }

	 /* --------------------------------------- */
	 // actions

	 public String getIndexing() {
			searchManager.indexing();
			this.value = null;
			return indexing;
	 }

	 public void doEasySearchEmployees() {
			this.employees = searchManager.easySearchEmployee(this.value);
	 }

	 public void generateEmployeeSettlements() {
			this.employeeSettlements = new ArrayList<EmployeeSettlements>();
			Settlement s;
			EmployeeSettlements es;
			for (Employee e : companyEmployees) {
				 s = scheduleManager.getSettlement(e, this.month, this.year);
				 if (s != null) {
						es = new EmployeeSettlements(e, s);
						if (overtimeOrNot == 0 && es.getMonthOvertime() > 0) employeeSettlements.add(es);
						else if (overtimeOrNot == 1 && es.getMonthOvertime() < 0) employeeSettlements.add(es);
				 }
			}
	 }

	 public void getEmployeesWithEndedContract() {
			for(Iterator<Employee> it = companyEmployees.iterator(); it.hasNext();) {
          Employee e = it.next();
          if (e.getEmployments().get(0).getEndDate().getYear() >= actualYear && 
 							 ((e.getEmployments().get(0).getEndDate().getMonth() == actualMonth &&
 							 e.getEmployments().get(0).getEndDate().getDay() > actualDay) || 
 							 e.getEmployments().get(0).getEndDate().getMonth() > actualMonth)) {
 						it.remove();
 				 }
      }
	 }
}