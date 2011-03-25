package com.promcio.util;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Model
@Named
@SessionScoped
public class PasswordValidation implements Serializable {

	 private static final long serialVersionUID = 1L;

	 private String pass1;
	 private String pass2;
	 private boolean pass1Set;

	 public void validateField(FacesContext context, UIComponent component, Object value) {
			if (pass1Set) {
				 pass2 = (String) value;
				 if (pass1 == null || (!pass1.equals(pass2))) {
						((EditableValueHolder) component).setValid(false);
						context.addMessage(component.getClientId(context), new FacesMessage(FacesMessage.SEVERITY_WARN, "WARN:", "Password must have both fields identical"));
				 }
				 pass1Set = false;
			} else {
				 pass1Set = true;
				 pass1 = (String) value;
			}
	 }
}