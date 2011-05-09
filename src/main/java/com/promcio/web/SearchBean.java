package com.promcio.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import com.promcio.domain.Employee;
import com.promcio.service.SearchManager;

@Model
@Named
@SessionScoped
public class SearchBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 SearchManager searchManager;

	 private String value;
	 private String city;
	 private String indexing;

	 private List<Employee> employees = new ArrayList<Employee>();

	 /* --------------------------------------- */

	 public String getValue() {
			return value;
	 }

	 public void setValue(String value) {
			this.value = value;
	 }

	 public String getCity() {
			return city;
	 }

	 public void setCity(String city) {
			this.city = city;
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

	 public void doAdvancedSearchEmployees() {
			this.employees = searchManager.advancedSearchEmployee(this.city);
	 }
}