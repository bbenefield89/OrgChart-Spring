package com.nexient.orgchart.data.entity;

import com.nexient.orgchart.data.entity.JobTitle;
//import org.springframework.validation.Validator;
import com.nexient.orgchart.data.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Created by kskronek on 6/8/2016.
 */
public class JobTitleTest {

	static final private String name = "New Job Title";

	JobTitle jobTitle;

	@BeforeSuite
	public void before(){
		jobTitle=new JobTitle();
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
}
