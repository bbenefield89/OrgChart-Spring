package com.nexient.orgchart.model;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/20/2016.
 */
public class BaseModelTest {
    static final private Integer id=12;
    BaseModel bm;

    @BeforeSuite
    public void before(){
        bm=new BaseModel();
    }

    @Test
    public void idSetandGetTest(){
        bm.setId(id);
        Integer bmId=bm.getId();
        Assert.assertEquals(id,bmId );
    }

    @Test
    public void isActiveSetAndGetTest(){
        bm.setIsActive(true);
        Assert.assertTrue(bm.getIsActive());
    }
}
