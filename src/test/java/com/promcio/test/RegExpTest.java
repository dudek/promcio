package com.promcio.test;

import java.util.regex.Pattern;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RegExpTest {

	 @DataProvider(name = "loginValidator")
	 public Object[][] getLoginData() {
			return new Object[][] {
						{ "admin", true },
						{ "Admin", true },
						{ "admin1", true },
						{ "admin_1", true },
						{ "admin 1", false },
						{ "1", false },
						{ new Integer(1), false },
						{ new Double(1.0), false },
			};
	 }
	 
	 @DataProvider(name = "nameValidator")
	 public Object[][] getNameData() {
			return new Object[][] {
						{ "Malto Corp.", true },
						{ "A B H 34", true },
						{ "wxeRet", true },
						{ "!@#$%^&*()", false },
						{ "A     C", false },
						{ new Integer(1), false },
						{ new Double(1.0), false },
			};
	 }
	 
	 @DataProvider(name = "emailValidator")
	 public Object[][] getEMailData() {
			return new Object[][] {
						{ "test@example.com", true },
						{ "Zuo.Test@example.com.pl", true },
						{ "Jan_kowalski@example.org", true },
						{ "arek test@example.com", false },
						{ "error@com", false },
						{ new Double(1.0), false },
			};
	 }
	 
	 @DataProvider(name = "nipValidator")
	 public Object[][] getNIPData() {
			return new Object[][] {
						{"123-456-78-90", true},
            {"123-45-67-890", true},
            {"1234567890", false},
            {"123-456-78-9a", false},
            {"123-45-67-89a", false},
			};
	 }

	 @Test(dataProvider = "loginValidator")
	 public void testLogin(Object value, boolean valid) {
			assert Pattern.matches("^[a-zA-Z][!-~]*$", value.toString()) == valid : "Validation result is wrong.";
	 }
	 
	 @Test(dataProvider = "nameValidator")
	 public void testName(Object value, boolean valid) {
			assert Pattern.matches("^[a-zA-Z]+([ ]?[.a-zA-Z0-9]+)*$", value.toString()) == valid : "Validation result is wrong.";
	 }
	 
	 @Test(dataProvider = "emailValidator")
	 public void testEMail(Object value, boolean valid) {
			assert Pattern.matches("^[_a-zA-Z0-9-]+([.][_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+([.][a-zA-Z0-9-]{1,})*[.]([a-zA-Z]{2,})$", value.toString()) == valid : "Validation result is wrong.";
	 }
	 
	 @Test(dataProvider = "nipValidator")
	 public void testNIP(Object value, boolean valid) {
			assert Pattern.matches("^([0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}|[0-9]{3}-[0-9]{2}-[0-9]{2}-[0-9]{3})$", value.toString()) == valid : "Validation result is wrong.";
	 }
}