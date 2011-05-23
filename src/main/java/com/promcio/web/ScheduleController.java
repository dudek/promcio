package com.promcio.web;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;


import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.ScheduleEntrySelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import com.promcio.domain.Employee;
import com.promcio.domain.Schedule;
import com.promcio.domain.Shift;
import com.promcio.service.CompanyManager;
import com.promcio.service.ScheduleManager;
import com.promcio.util.EmployeeScheduleEvent;

@ManagedBean
@ViewScoped
public class ScheduleController implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Inject
		private ScheduleManager scheduleManager;
		@Inject
		private CompanyManager companyManager;
		@Inject
		private AccountBean accountBean;
		
		private long employeeId;
		private List<Employee> companyEmployees;
		
		private long shiftId;
		private List<Shift> companyShifts;
		
        private ScheduleModel eventModel;
        private ScheduleEvent event;

        private List<SelectItem> selectEmployees; 
        private List<SelectItem> selectShifts;

        private Calendar calendarDate = Calendar.getInstance();
        
		public ScheduleController() {
                eventModel = new DefaultScheduleModel();
                event = new EmployeeScheduleEvent();
                selectEmployees = new ArrayList<SelectItem>();                              
        }
        
        @PostConstruct
        public void viewInit(){
       	    selectEmployees = new ArrayList<SelectItem>();
       	    selectShifts = new ArrayList<SelectItem>();
       	    companyEmployees = companyManager.getAllCompanyEmployees(accountBean.getCompany().getId());
    	    for(Employee employee : companyEmployees  ) {
    	    	selectEmployees.add(new SelectItem(employee.getId(), "" + employee.getSurname() + " " + employee.getFirstname()));
    	    }     
    	    companyShifts = companyManager.getAllCompanyShifts(accountBean.getCompany().getId());
    	    for(Shift shift : companyShifts ){
    	    	selectShifts.add(new SelectItem(shift.getId(), "" + shift.getName() + ""));
    	    }
    	    
    	    /*
    	    for ( Employee employee : companyEmployees){
    	    	List<Schedule> employeeSchedules = scheduleManager.getAllEmployeeMonthSchedules(employee.getId(), calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.YEAR) );
    	    	for ( Schedule schedule : employeeSchedules){
    	    		eventModel.addEvent(new EmployeeScheduleEvent(employee.getSurname() + " " + employee.getFirstname() + " " + schedule.getShift().getName(), 
    	    				scheduleManager.calendarToDate(schedule.getCalendar()), scheduleManager.calendarToDate(schedule.getCalendar() ) ) );
    	    	}
    	     }*/
    	    
    	     List<Schedule> companySchedules = scheduleManager.getAllCompanySchedules(accountBean.getCompany(), calendarDate.get(Calendar.MONTH),calendarDate.get(Calendar.YEAR));
    	     for ( Schedule schedule : companySchedules){
    	    	 for ( Employee employee : schedule.getEmployees()){
    	    		 event = new EmployeeScheduleEvent(employee.getSurname() + " " + employee.getFirstname() + " " + schedule.getShift().getName(), 
      	    				scheduleManager.calendarToDate(schedule.getCalendar()), scheduleManager.calendarToDate(schedule.getCalendar() ) );
    	    		 
    	    		 ((EmployeeScheduleEvent)event).setEmployee(employee);
    	    		 ((EmployeeScheduleEvent)event).setShift(schedule.getShift());
    	    		 
    	    		 ArrayList<com.promcio.domain.Calendar> calendars = new ArrayList<com.promcio.domain.Calendar>();
    	    		 calendars.add(schedule.getCalendar());
    	    		 ((EmployeeScheduleEvent)event).setCalendars(calendars);
    	    		 
    	    		 
    	    		 eventModel.addEvent( event );
    	    		 
    	    	 }
    	     }
    	     
    	     /* Dla urposzczenia pobranie dla miesiaca w przod i w tyl */
    	     companySchedules = scheduleManager.getAllCompanySchedules(accountBean.getCompany(), calendarDate.get(Calendar.MONTH)-1,calendarDate.get(Calendar.YEAR));
    	     for ( Schedule schedule : companySchedules){
    	    	 for ( Employee employee : schedule.getEmployees()){
    	    		 event = new EmployeeScheduleEvent(employee.getSurname() + " " + employee.getFirstname() + " " + schedule.getShift().getName(), 
      	    				scheduleManager.calendarToDate(schedule.getCalendar()), scheduleManager.calendarToDate(schedule.getCalendar() ) );
    	    		 
    	    		 ((EmployeeScheduleEvent)event).setEmployee(employee);
    	    		 ((EmployeeScheduleEvent)event).setShift(schedule.getShift());
    	    		 
    	    		 ArrayList<com.promcio.domain.Calendar> calendars = new ArrayList<com.promcio.domain.Calendar>();
    	    		 calendars.add(schedule.getCalendar());
    	    		 ((EmployeeScheduleEvent)event).setCalendars(calendars);
    	    		 
    	    		 
    	    		 eventModel.addEvent( event );
    	    		 
    	    	 }
    	     }
    	     companySchedules = scheduleManager.getAllCompanySchedules(accountBean.getCompany(), calendarDate.get(Calendar.MONTH)+1,calendarDate.get(Calendar.YEAR));
    	     for ( Schedule schedule : companySchedules){
    	    	 for ( Employee employee : schedule.getEmployees()){
    	    		 event = new EmployeeScheduleEvent(employee.getSurname() + " " + employee.getFirstname() + " " + schedule.getShift().getName(), 
      	    				scheduleManager.calendarToDate(schedule.getCalendar()), scheduleManager.calendarToDate(schedule.getCalendar() ) );
    	    		 
    	    		 ((EmployeeScheduleEvent)event).setEmployee(employee);
    	    		 ((EmployeeScheduleEvent)event).setShift(schedule.getShift());
    	    		 
    	    		 ArrayList<com.promcio.domain.Calendar> calendars = new ArrayList<com.promcio.domain.Calendar>();
    	    		 calendars.add(schedule.getCalendar());
    	    		 ((EmployeeScheduleEvent)event).setCalendars(calendars);
    	    		 
    	    		 
    	    		 eventModel.addEvent( event );
    	    		 
    	    	 }
    	     }
        }
        
        /* gettery,settery */
        
        public ScheduleModel getEventModel() {
                return eventModel;
        }
        
        public ScheduleEvent getEvent() {
                return event;
        }


        public void setEvent(ScheduleEvent event) {
                this.event = event;
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

		public void setSelectEmployees(List<SelectItem> selectEmployees) {
			this.selectEmployees = selectEmployees;
		}
		
		public void setSelectShifts(List<SelectItem> selectShifts) {
			this.selectShifts = selectShifts;
		}

		public List<SelectItem> getSelectShifts() {
			return selectShifts;
		}
		
		public void setCompanyShifts(List<Shift> companyShifts) {
			this.companyShifts = companyShifts;
		}

		public List<Shift> getCompanyShifts() {
			return companyShifts;
		}
		
		public void setShiftId(long shiftId) {
			this.shiftId = shiftId;
		}

		public long getShiftId() {
			return shiftId;
		}
		
		/* Akcje */
		
		public void removeEvent(ActionEvent actionEvent){
			for ( com.promcio.domain.Calendar calendar : ((EmployeeScheduleEvent)event).getCalendars() ){
				Schedule schedule = scheduleManager.getSchedule(((EmployeeScheduleEvent)event).getShift(), calendar);
				scheduleManager.removeEmployeeFromSchedule(schedule, ((EmployeeScheduleEvent)event).getEmployee());
				scheduleManager.removeWorkTimeFromSettlement(((EmployeeScheduleEvent)event).getEmployee(), calendar.getMonth(), calendar.getYear(), ((EmployeeScheduleEvent)event).getShift());
			}
			eventModel.deleteEvent(event);
			event = new EmployeeScheduleEvent();
		}
        
        public void addEvent(ActionEvent actionEvent) {
				Employee employee = new Employee();	
				Shift shift = new Shift();
				for ( Employee it : companyEmployees ){
					if ( it.getId() == employeeId ){
						employee = it;
						break;
					}
				}
				for ( Shift it : companyShifts){
					if ( it.getId() == shiftId ){
						shift = it;
						break;
					}
				}
				
        		( (EmployeeScheduleEvent) event ).setTitle(employee.getSurname() + " " + employee.getFirstname() + " " + shift.getName());
                
        		if(event.getId() == null){
    				Schedule schedule;
    				// przypadek gdy zdarzenie jest jednodniowe
    				if ( event.getStartDate().equals(event.getEndDate()) ){
    					com.promcio.domain.Calendar calendar = scheduleManager.dateToCalendar(event.getStartDate());
    					schedule = scheduleManager.addSchedule(accountBean.getCompany(), shift, calendar);
    					scheduleManager.addEmployeeToSchedule(schedule, employee);
    					
    					//dodanie czasu pracy pracownika do jego rozliczenia
    					scheduleManager.addWorkTimeToSettlement(employee, calendar.getMonth(), calendar.getYear(), scheduleManager.getWorkTimeFromShift(shift));
    					
    					( (EmployeeScheduleEvent) event ).setEmployee(employee);
    					( (EmployeeScheduleEvent) event ).setShift(shift);
    					if ( ( (EmployeeScheduleEvent) event ).getCalendars() == null ){
    						( (EmployeeScheduleEvent) event ).setCalendars(new ArrayList<com.promcio.domain.Calendar>());
    					}
    					( (EmployeeScheduleEvent) event ).getCalendars().add( calendar );
    				}
    				// przypadek gdy zdarzenie jest rozciagniete na kilka dni, trzeba dla kazdego dnia osobny obiekt Schedule utrwalic jesli jeszcze nie ma go w bazie
    				// gdy istnieje dodac do niego pracownika
    				else{
    					com.promcio.domain.Calendar startCalendar = scheduleManager.dateToCalendar(event.getStartDate());
    					int dayDelta = scheduleManager.dateToCalendar(event.getEndDate()).getDay() - startCalendar.getDay();  
    					
    					( (EmployeeScheduleEvent) event ).setEmployee(employee);
    					( (EmployeeScheduleEvent) event ).setShift(shift);
    					if ( ( (EmployeeScheduleEvent) event ).getCalendars() == null ){
    						( (EmployeeScheduleEvent) event ).setCalendars(new ArrayList<com.promcio.domain.Calendar>());
    					}
    					
    					com.promcio.domain.Calendar calendar = new com.promcio.domain.Calendar(startCalendar.getYear(), startCalendar.getMonth(), startCalendar.getDay());
    					for ( int i = 0; i <= dayDelta; i++){
    						schedule = scheduleManager.addSchedule(accountBean.getCompany(), shift, calendar);
    						scheduleManager.addEmployeeToSchedule(schedule, employee);
    						
    						//dodanie czasu pracy pracownika do jego rozliczenia
    						scheduleManager.addWorkTimeToSettlement(employee, calendar.getMonth(), calendar.getYear(), scheduleManager.getWorkTimeFromShift(shift));
    						
    						( (EmployeeScheduleEvent) event ).getCalendars().add( calendar );
    						
    						calendar = new com.promcio.domain.Calendar(calendar.getYear(), calendar.getMonth(), calendar.getDay()+1);
    					}
    				}
    				eventModel.addEvent(event);
                }    
        		/*
        		 * Przypadek aktualizacji, najpierw usuniecie a potem dodanie nowej zaktualizowanej pozycji w grafiku
        		 */
                else{
                	ScheduleEvent updatedEvent = new EmployeeScheduleEvent(event.getTitle(), event.getStartDate(), event.getEndDate());
					removeEvent(actionEvent);
					event = new EmployeeScheduleEvent(updatedEvent.getTitle(), updatedEvent.getStartDate(), updatedEvent.getEndDate());
					addEvent(actionEvent);
                }
                       
                
                event = new EmployeeScheduleEvent();
        }
        
        public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {

        	    
                event = selectEvent.getScheduleEvent();
               
        }
        
        public void onDateSelect(DateSelectEvent selectEvent) {
    			Calendar calendar =  Calendar.getInstance();
    			calendar.setTime(selectEvent.getDate());
    			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
    			event = new EmployeeScheduleEvent("", calendar.getTime(), calendar.getTime());
        }
        
        public void onEventMove(ScheduleEntryMoveEvent event) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
                
                addMessage(message);
        }
        
        public void onEventResize(ScheduleEntryResizeEvent event) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
                
                addMessage(message);
        }
        
        private void addMessage(FacesMessage message) {
                FacesContext.getCurrentInstance().addMessage(null, message);
        }

		

		

		

        
}