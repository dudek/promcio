package com.promcio.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import com.promcio.service.CompanyManager;

@Model
@Named
@SessionScoped
public class ShiftBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	CompanyManager companyManager;
	
	private String name;
	
	private int timeStart;
	private int timeEnd;
	
	/* --------------------------------------- */
	
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
	
	/* --------------------------------------- */
	
	 
	 public String doAddShiftCompany(long companyId){
		 	companyManager.addShift(companyId, name, timeStart, timeEnd);
		 	return null;
	 }
	 
	 public String doRemoveShiftCompany(long shiftId){
		 	companyManager.removeShift(shiftId);
		 	return null;
	 }
	 
	 public String doUpdateShiftCompany(long shiftId){
		 	companyManager.updateShift(shiftId, name, timeStart, timeEnd);
		 	return null;
	 }
}
