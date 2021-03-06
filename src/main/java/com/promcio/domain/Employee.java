package com.promcio.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

@Indexed
@Entity
public class Employee {

	 private long id;

	 @Field(index = Index.TOKENIZED, store = Store.NO)
	 private String firstname;

	 @Field(index = Index.TOKENIZED, store = Store.NO)
	 private String surname;

	 private String pesel;
	 private String nip;
	 private int yob;

	 private Account account;
	 @IndexedEmbedded
	 private EmployeeDetails details;
	 private Rank rank;
	 private List<Employment> employments;
	 private Company company;

	 /* --------------------------------------- */

	 public Employee(long id, String firstname, String surname, String pesel, String nip, int yob, Rank rank) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.surname = surname;
			this.pesel = pesel;
			this.nip = nip;
			this.yob = yob;
			this.rank = rank;
	 }

	 public Employee(long id, String firstname, String surname, Rank rank) {
			super();
			this.id = id;
			this.firstname = firstname;
			this.surname = surname;
			this.rank = rank;
	 }

	 public Employee() {
			super();
	 }

	 /* --------------------------------------- */

	 @Id
	 @GeneratedValue
	 public long getId() {
			return id;
	 }

	 public void setId(long id) {
			this.id = id;
	 }

	 public String getFirstname() {
			return firstname;
	 }

	 public void setFirstname(String firstname) {
			this.firstname = firstname;
	 }

	 public String getSurname() {
			return surname;
	 }

	 public void setSurname(String surname) {
			this.surname = surname;
	 }

	 public String getPesel() {
			return pesel;
	 }

	 public void setPesel(String pesel) {
			this.pesel = pesel;
	 }

	 public int getYob() {
			return yob;
	 }

	 public void setYob(int yob) {
			this.yob = yob;
	 }

	 public String getNip() {
			return nip;
	 }

	 public void setNip(String nip) {
			this.nip = nip;
	 }

	 @OneToOne
	 public Account getAccount() {
			return account;
	 }

	 public void setAccount(Account account) {
			this.account = account;
	 }

	 @OneToOne
	 public EmployeeDetails getDetails() {
			return details;
	 }

	 public void setDetails(EmployeeDetails details) {
			this.details = details;
	 }

	 @OneToOne
	 public Rank getRank() {
			return rank;
	 }

	 public void setRank(Rank rank) {
			this.rank = rank;
	 }

	 @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
	 public List<Employment> getEmployments() {
			return employments;
	 }

	 public void setEmployments(List<Employment> employments) {
			this.employments = employments;
	 }

	 @ManyToOne
	 public Company getCompany() {
			return company;
	 }

	 public void setCompany(Company company) {
			this.company = company;
	 }
}