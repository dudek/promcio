package com.promcio.web;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;

import com.promcio.domain.Employee;
import com.promcio.domain.Rank;
import com.promcio.service.CompanyManager;

@SessionScoped
@Named
public  @Model class CompanyBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	CompanyManager companymanager;
	
	private String name;
	private String nip;
	private String regon;
	
	private List<Employee> employees;
	private List<Rank> ranks;
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setNip(String nip) {
		this.nip = nip;
	}
	public String getNip() {
		return nip;
	}
	public void setRegon(String regon) {
		this.regon = regon;
	}
	public String getRegon() {
		return regon;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setRanks(List<Rank> ranks) {
		this.ranks = ranks;
	}
	public List<Rank> getRanks() {
		return ranks;
	}

}
