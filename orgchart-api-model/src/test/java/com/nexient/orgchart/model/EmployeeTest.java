package com.nexient.orgchart.model;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by mrangel on 7/20/2016.
 */
public class EmployeeTest {
    private static final Character MIDDLE_INITIAL = 'M';
    private static final String LAST_NAME = "last name";
    private static final String FIRST_NAME = "first name";
    private static final String EMAIL = "email@email.com";
    private static final String SKYPE_NAME="skype";
    private Employee emp;
    private Employee mgr;
    private Department dept;
    private JobTitle title;

    Random random = new Random();

    @BeforeSuite
    public void before() {
        emp = new Employee();
        mgr = new Employee();
        mgr.setId(random.nextInt());
        dept = new Department();
        dept.setId(random.nextInt());
        title=Models.jobTitle();
    }

    @Test
    public void instantiated() {
        Assert.assertNotNull(emp);
    }

    @Test
    public void setAndGetName() {
        emp.setFirstName(FIRST_NAME);
        String name = emp.getFirstName();
        Assert.assertNotNull(name);
        Assert.assertEquals(FIRST_NAME, name);
    }
    //
    @Test
    public void setAndGetLastName() {
        emp.setLastName(LAST_NAME);
        String name = emp.getLastName();
        Assert.assertNotNull(name);
        Assert.assertEquals(LAST_NAME, name);
    }

    @Test
    public void setAndGetMiddleInitial() {
        emp.setMiddleInitial(MIDDLE_INITIAL);
        Character middleInitial = emp.getMiddleInitial();
        Assert.assertNotNull(middleInitial);
        Assert.assertEquals(MIDDLE_INITIAL, middleInitial);
    }

    @Test
    public void setAndGetManager() {
        emp.setManager(mgr);
        Employee manager = emp.getManager();
        Assert.assertNotNull(manager);
        Assert.assertEquals(mgr.getId(), manager.getId());
    }

    @Test
    public void setAndGetDepartment(){
        emp.setDepartment(dept);
        Assert.assertNotNull(emp.getDepartment());
        Assert.assertEquals(dept.getId(),emp.getDepartment().getId());
    }

    @Test
    public void setAndGetJobTitle(){
        emp.setJobTitle(title);
        Assert.assertNotNull(emp.getJobTitle());
        Assert.assertEquals(title.getId(), emp.getJobTitle().getId());
    }

    @Test
    public void setAndGetEmail(){
        emp.setEmail(EMAIL);
        Assert.assertNotNull(emp.getEmail());
        Assert.assertEquals(emp.getEmail(), EMAIL);
    }

    @Test
    public void setAndGetSkypeName(){
        emp.setSkypeName(SKYPE_NAME);
        Assert.assertNotNull(emp.getSkypeName());
        Assert.assertEquals(emp.getSkypeName(), SKYPE_NAME);
    }

    @Test
    public void setAndGetIsManager(){
        emp.setIsManager(true);
        Assert.assertNotNull(emp.getIsManager());
        Assert.assertTrue(emp.getIsManager());
    }

}