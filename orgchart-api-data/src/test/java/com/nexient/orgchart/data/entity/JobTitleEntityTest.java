package com.nexient.orgchart.data.entity;

//import org.springframework.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kskronek on 6/8/2016.
 */
public class JobTitleEntityTest {

	static final private String name = "New Job Title";

	private JobTitleEntity jobTitle;

	private Set<EmployeeEntity> titleEmployees;

	private EmployeeEntity emp;


	@BeforeSuite
	public void before(){
		jobTitle = new JobTitleEntity();
		titleEmployees=new HashSet<>();
		emp= Entities.employee();
	}

	@Test
	public void instantiated() {
		Assert.assertNotNull(jobTitle);
	}

	@Test
	public void descriptionSetandGetTest(){
		jobTitle.setName(name);
		String descript=jobTitle.getName();
		Assert.assertEquals(descript, name);
	}

	@Test
	public void setAndGetTitleEmployees(){
		titleEmployees.add(emp);
		jobTitle.setTitleEmployees(titleEmployees);
		Assert.assertNotNull(jobTitle.getTitleEmployees());
	}
}
