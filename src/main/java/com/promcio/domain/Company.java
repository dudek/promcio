package com.promcio.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	 private long id;

	 private String name;
	 private String nip;
	 private String regon;

	 private List<Employee> employees;
	 private List<Rank> ranks;

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

	 @OneToMany(mappedBy="company")
	 public List<Employee> getEmployees() {
			return employees;
	 }

	 public void setEmployees(List<Employee> employees) {
			this.employees = employees;
	 }

	 @OneToMany
	 public List<Rank> getRanks() {
			return ranks;
	 }

	 public void setRanks(List<Rank> ranks) {
			this.ranks = ranks;
	 }
}