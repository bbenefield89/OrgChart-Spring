package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import org.hibernate.Incubating;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/26/2016.
 */
public class JobTitleMapperTest {

    @InjectMocks
    private JobTitleMapper titleMapper;

    private JobTitleEntity titleEntity;
    private JobTitle titleModel;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);

        this.titleEntity = Entities.jobTitle();
        this.titleEntity.setId(Entities.JOB_TITLE_ID);

        this.titleModel = Models.jobTitle();
        this.titleModel.setId(Models.JOB_TITLE_ID);

    }

//    @Test
//    public void testJobTitleEntityToModel(){
//        this.titleModel = this.titleMapper.entityToModel(titleEntity);
//        Assert.assertNotNull(titleModel);
//        Assert.assertNotNull(titleModel.getName());
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testJobTitleEntityNull(){
//        this.titleMapper.entityToModel(null);
//    }
//
//    @Test
//    public void testJobTitleModelToEntity(){
//        this.titleEntity = this.titleMapper.modelToEntity(titleModel);
//        Assert.assertNotNull(titleEntity);
//        Assert.assertNotNull(titleEntity.getName());
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void testJobTitleModelNull(){
//        this.titleMapper.modelToEntity(null);
//    }

}
