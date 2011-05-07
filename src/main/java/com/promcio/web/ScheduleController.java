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
import com.promcio.domain.Shift;
import com.promcio.service.ScheduleManager;

@ManagedBean
@ViewScoped
public class ScheduleController implements Serializable{

		private static final long serialVersionUID = 1L;
		
		@Inject
		private ScheduleManager scheduleManager;
		@Inject
		private CompanyBean companyBean;
		@Inject
		private AccountBean accountBean;
		
		private long employeeId;
		private List<Employee> companyEmployees;
		
		private List<Shift> companyShifts;
		
        private ScheduleModel eventModel;
        private ScheduleEvent event;

        private List<SelectItem> selectEmployees; 
        private List<SelectItem> selectShifts;

		public ScheduleController() {
                eventModel = new DefaultScheduleModel();
                event = new DefaultScheduleEvent();
                selectEmployees = new ArrayList<SelectItem>();                              
        }
        
        @PostConstruct
        public void viewInit(){
       	    selectEmployees = new ArrayList<SelectItem>();
       	    selectShifts = new ArrayList<SelectItem>();
       	    companyEmployees = companyBean.getCompanyEmployees(accountBean.getCompany().getId());
    	    for(Employee employee : companyEmployees  ) {
    	    	selectEmployees.add(new SelectItem(employee.getId(), "" + employee.getSurname() + " " + employee.getFirstname()));
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
		
		/* Akcje */
        
        public void addEvent(ActionEvent actionEvent) {
				Employee employee = new Employee();	
				for ( Employee it : companyEmployees ){
					if ( it.getId() == employeeId ){
						employee = it;
						break;
					}
				}
        		( (DefaultScheduleEvent) event ).setTitle(employee.getSurname() + " " + employee.getFirstname());
                if(event.getId() == null)
                        eventModel.addEvent(event);
                else
                        eventModel.updateEvent(event);
                
                event = new DefaultScheduleEvent();
        }
        
        public void onEventSelect(ScheduleEntrySelectEvent selectEvent) {

        	    
                event = selectEvent.getScheduleEvent();
               
        }
        
        public void onDateSelect(DateSelectEvent selectEvent) {
    			Calendar calendar =  Calendar.getInstance();
    			calendar.setTime(selectEvent.getDate());
    			calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)+1);
    			event = new DefaultScheduleEvent("", calendar.getTime(), calendar.getTime());
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