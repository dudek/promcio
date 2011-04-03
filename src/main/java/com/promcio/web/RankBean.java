package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;

import com.promcio.service.CompanyManager;

@Model
@Named
@SessionScoped
public class RankBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @Inject
	 CompanyManager companyManager;
	 
	 @NotNull
	 @NotEmpty
	 @Pattern(regexp = "^[A-Z][a-zA-Z]+$")
	 private String name;
	 
	 @NotNull
	 @NotEmpty
	 private float hourSalary;
	 
	 /* --------------------------------------- */

	 public void setName(String name) {
			this.name = name;
	 }

	 public String getName() {
			return name;
	 }

	 public void setHourSalary(float hourSalary) {
			this.hourSalary = hourSalary;
	 }

	 public float getHourSalary() {
			return hourSalary;
	 }

	 /* --------------------------------------- */
	 //actions
	 
	 public String doAddRankCompany(long companyId){
		 	companyManager.addRank(companyId, name, companyId);
		 	return null;
	 }
	 
	 public String doRemoveRankCompany(long rankId){
		 	companyManager.removeRank(rankId);
		 	return null;
	 }
	 
	 public String doUpdateRankCompany(long rankId){
		 	companyManager.updateRank(rankId, name, hourSalary );
		 	return null;
	 }

}