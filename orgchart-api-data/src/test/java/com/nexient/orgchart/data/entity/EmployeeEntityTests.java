package com.nexient.orgchart.data.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class EmployeeEntityTests {

	private static final Character MIDDLE_INITIAL = 'M';
	private static final String LAST_NAME = "last name";
	private static final String FIRST_NAME = "first name";
	private static final String EMAIL = "email@email.com";
	private static final String SKYPE_NAME="skype";
	EmployeeEntity emp;
	EmployeeEntity mgr;
	DepartmentEntity dept;
	JobTitleEntity title;

	Set<EmployeeEntity> managedEmployees;
	Set<DepartmentEntity> managedDepartments;

	Random random = new Random();

	@BeforeSuite
	public void before() {
		emp = new EmployeeEntity();
		mgr = new EmployeeEntity();
		mgr.setId(random.nextInt());
		dept = new DepartmentEntity();
		dept.setId(random.nextInt());
		title = new JobTitleEntity();
		title.setId(random.nextInt());
		managedDepartments= new HashSet<>();
		managedEmployees = new HashSet<>();
	}

	@Test
	public void instantiated() {
		Assert.assertNotNull(emp);
	}

	@Test
	public void setAndGetFirstName() {
		emp.setFirstName(FIRST_NAME);
		String name = emp.getFirstName();
		Assert.assertNotNull(name);
		Assert.assertEquals(FIRST_NAME, name);
	}

	@Test
	public void setAndGetLastName() {
		emp.setLastName(LAST_NAME);
		String name = emp.getLastName();
		Assert.assertNotNull(name);
		Assert.assertEquals(LAST_NAME, name);
	}

	@Test
	public void setAndGetMiddleInitial() {
		emp.setMiddleInitial(MIDDLE_INITIAL);
		Character middleInitial = emp.getMiddleInitial();
		Assert.assertNotNull(middleInitial);
		Assert.assertEquals(MIDDLE_INITIAL, middleInitial);
	}

	@Test
	public void setAndGetManager() {
		emp.setManager(mgr);
		EmployeeEntity manager = emp.getManager();
		Assert.assertNotNull(manager);
		Assert.assertEquals(mgr.getId(), manager.getId());
	}

	@Test
	public void setAndGetDepartment() {
		emp.setDepartment(dept);
		Assert.assertNotNull(emp.getDepartment());
		Assert.assertEquals(dept.getId(), emp.getDepartment().getId());
	}

	@Test
	public void setAndGetEmail(){
		emp.setEmail(EMAIL);
		Assert.assertNotNull(emp.getEmail());
		Assert.assertEquals(emp.getEmail(), EMAIL);
	}

	@Test
	public void setAndGetSkypeName(){
		emp.setSkypeName(SKYPE_NAME);
		Assert.assertNotNull(emp.getSkypeName());
		Assert.assertEquals(emp.getSkypeName(), SKYPE_NAME);
	}

	@Test
	public void setAndGetIsManager(){
		emp.setIsManager(true);
		Assert.assertNotNull(emp.getIsManager());
		Assert.assertTrue(emp.getIsManager());
	}

	@Test
	public void setAndGetJobTitle(){
		emp.setJobTitle(title);
		Assert.assertNotNull(emp.getJobTitle());
		Assert.assertEquals(emp.getJobTitle().getId(), title.getId());
	}

	@Test
	public void setAndGetManangedEmployees(){
		managedEmployees.add(emp);
		emp.setManagedEmployees(managedEmployees);
		Assert.assertNotNull(emp.getManagedEmployees());
	}

	@Test
	public void setAndGetManagedDepartments(){
		managedDepartments.add(dept);
		emp.setManagedDepartments(managedDepartments);
		Assert.assertNotNull(emp.getManagedDepartments());
	}
}