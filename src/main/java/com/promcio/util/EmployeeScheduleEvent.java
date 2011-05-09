package com.promcio.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.primefaces.model.ScheduleEvent;

import com.promcio.domain.Calendar;
import com.promcio.domain.Employee;
import com.promcio.domain.Shift;

public class EmployeeScheduleEvent implements ScheduleEvent, Serializable {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String title;
	
	private Date startDate;
	
	private Date endDate;
	
	private boolean allDay = true;
	
	private String styleClass;
	
	private Object data;
	
	
	private Employee employee;
	private Shift shift;
	private List<Calendar> calendars;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	public String getStyleClass() {
		return styleClass;
	}
	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	public List<Calendar> getCalendars() {
		return calendars;
	}
	public void setCalendars(List<Calendar> calendars) {
		this.calendars = calendars;
	}
	
	
	
	
	
}
