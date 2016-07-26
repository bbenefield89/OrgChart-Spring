package com.nexient.orgchart.data.entity;

//import org.springframework.validation.Validator;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by kskronek on 6/8/2016.
 */
public class JobTitleEntityTest {

	static final private String name = "New Job Title";

	JobTitleEntity jobTitle;

	@BeforeSuite
	public void before(){
		jobTitle=new JobTitleEntity();
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
