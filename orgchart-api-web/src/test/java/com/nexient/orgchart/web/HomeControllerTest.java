package com.nexient.orgchart.web;

import com.nexient.orgchart.web.com.nexient.orgchart.web.controller.HomeController;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by dhoover on 8/4/2016.
 */
public class HomeControllerTest {

    @InjectMocks
    HomeController controller;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testHomeDoGet(){
        Assert.assertNotNull(controller.doGet());
    }
}
