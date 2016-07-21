package com.nexient.orgchart.model;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/20/2016.
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
