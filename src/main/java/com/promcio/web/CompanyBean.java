package com.promcio.web;

import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import com.promcio.domain.Employee;
import com.promcio.domain.Rank;
import com.promcio.service.CompanyManager;

@Model
@Named
@SessionScoped
public class CompanyBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 CompanyManager companyManager;

	 private long id;

	 private String name;
	 private String nip;
	 private String regon;

	 private List<Employee> employees;
	 private List<Rank> ranks;

	 public long getId() {
			return id;
	 }

	 public void setId(long id) {
			this.id = id;
	 }

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

	 public List<Employee> getCompanyEmployees(long companyId) {
			return companyManager.getAllCompanyEmployees(companyId);
	 }
}