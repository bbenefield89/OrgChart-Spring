package com.nexient.orgchart.model;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/20/2016.
 */
public class JobTitleModelTest {
    static final private String description= "New Name";

    JobTitleModel jobTitle;

    @BeforeSuite
    public void before(){
        jobTitle=new JobTitleModel();
    }

    @Test
    public void descriptionSetandGetTest(){
        jobTitle.setDescription(description);
        String descript=jobTitle.getDescription();
        Assert.assertEquals(descript, description);
    }


}
