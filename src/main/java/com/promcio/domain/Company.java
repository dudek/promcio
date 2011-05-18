package com.promcio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company {

	 private long id;

	 private String name;
	 private String nip;
	 private String regon;

	 private Account account;
	 
	 private List<Employee> employees;
	 private List<Rank> ranks;
	 private List<Shift> shifts;
	 
	 /* --------------------------------------- */

	 @Id
	 @GeneratedValue
	 public long getId() {
			return id;
	 }

	 public void setId(long id) {
			this.id = id;
	 }

	 public String getName() {
			return name;
	 }

	 public void setName(String name) {
			this.name = name;
	 }

	 public String getNip() {
			return nip;
	 }

	 public void setNip(String nip) {
			this.nip = nip;
	 }

	 public String getRegon() {
			return regon;
	 }

	 public void setRegon(String regon) {
			this.regon = regon;
	 }

	 @OneToOne
	 public Account getAccount() {
	 	 return account;
	 }

	 public void setAccount(Account account) {
	 	 this.account = account;
	 }

	 @OneToMany(mappedBy = "company", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 public List<Employee> getEmployees() {
			return employees;
	 }

	 public void setEmployees(List<Employee> employees) {
			this.employees = employees;
	 }

	 @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	 public List<Rank> getRanks() {
			return ranks;
	 }

	 public void setRanks(List<Rank> ranks) {
			this.ranks = ranks;
	 }

	 @OneToMany(mappedBy = "company")
	 public List<Shift> getShifts() {
		 	return shifts;
	 }

	 public void setShifts(List<Shift> shifts) {
		 	this.shifts = shifts;
	 }
}