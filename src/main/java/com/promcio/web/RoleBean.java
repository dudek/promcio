package com.promcio.web;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

@Model
@Named
@SessionScoped
public class RoleBean implements Serializable {

	 private static final long serialVersionUID = 1L;

	 @NotNull
	 @NotEmpty
	 private long id;

	 @NotNull
	 @NotEmpty
	 private String name;

	 /* --------------------------------------- */

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
}