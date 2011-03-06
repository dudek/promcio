package com.promcio.tests;

import org.testng.annotations.Test;

public class EmployeeManagerTest {
	 
	 private static final long ID_1 = 1;
	 
	 /*
	  * Employee
	  */
	 private static final String NAME_1 = "Jan";
	 private static final String SURNAME_1 = "Kowalski";
	 private static final String PESEL_1 = "11223344556";
	 private static final int YOB_1 = 1970;
	 private static final int NIP_1 = 123456;
	 
	 /*
	  * Employment
	  */
	 //private static final Calendar CON_START_1 = ?;
	 //private static final Calendar CON_END_1 = ?;
	 private static final String CON_TYPE_1 = "?";
	 private static final float SALARY_1 = 2500;
	 private static final int HOURS_1 = 10;
	 
	 /*
	  * Rank
	  */
	 private static final int HOURS_SALARY_1 = 50;
	 
	 /*
	  * EmployeeDetails
	  */
	 private static final String CITY_1 = "Gdansk";
	 private static final String POSTCODE_1 = "11-222";
	 private static final String STREET_1 = "Grunwaldzka";
	 private static final String BUILDING_NR_1 = "10";
	 private static final String APARTMENT_NR_1 = "4";
	 private static final String STAIRCASE_NR_1 = "2";
	 private static final String PHONE_NR_1 = "111-222-333";
	 private static final String EMAIL_1 = "jan.kowalski@example.com";
	 
	 //EmployeeManager = new EmployeeManager();

	 @Test
	 public void testAddEmployee() {
			//EmployeeManager.addEmployee(NAME_1, SURNAME_1, PESEL_1, YOB_1, NIP_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testRemoveEmployee() {
			//EmployeeManager.removeEmployee(ID_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testAddEmployment() {
			//EmployeeManager.addEmployment(CON_START_1, CON_END_1, CON_TYPE_1, SALARY_1, HOURS_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testRemoveEmployment() {
			//EmployeeManager.removeEmployment(ID_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testAddRank() {
			//EmployeeManager.addRank(NAME_1, HOURS_SALARY_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testRemoveRank() {
			//EmployeeManager.removeRank(ID_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testAddEmployeeDetails() {
			//EmployeeManager.addEmployeeDetails(CITY_1, POSTCODE_1, STREET_1, BUILDING_NR_1, APARTMENT_NR_1, STAIRCASE_NR_1, PHONE_NR_1, EMAIL_1);
			//assert EmployeeManager.getStatus();
	 }
	 
	 @Test
	 public void testRemoveEmployeeDetails() {
			//EmployeeManager.removeEmployeeDetails(ID_1);
			//assert EmployeeManager.getStatus();
	 }
}