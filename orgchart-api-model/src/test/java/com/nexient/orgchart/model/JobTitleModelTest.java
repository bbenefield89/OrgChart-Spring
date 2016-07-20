package com.nexient.orgchart.model;

import com.nexient.orgchart.model.JobTitleModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/20/2016.
 */
public class JobTitleModelTest {
    static final private String description= "New Name";

    JobTitleModel jobTitle;

    @BeforeClass
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
