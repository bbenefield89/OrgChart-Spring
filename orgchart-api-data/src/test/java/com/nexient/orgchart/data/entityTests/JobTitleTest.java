package com.nexient.orgchart.data.entityTests;

import com.nexient.orgchart.data.entity.JobTitle;
//import org.springframework.validation.Validator;
import com.nexient.orgchart.data.validation.Validator;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * Created by kskronek on 6/8/2016.
 */
public class JobTitleTest {

	@Test
	public void testStuff(){
		JobTitle jobTitle = new JobTitle.Builder("description").employees(Collections.emptySet()).build();

		Validator validator = new Validator();
		validator.validate(jobTitle);
	}
}
