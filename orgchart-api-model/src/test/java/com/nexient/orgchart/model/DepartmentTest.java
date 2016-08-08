package com.nexient.orgchart.model;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

/**
 * Created by mrangel on 7/20/2016.
 */
public class DepartmentTest {

    private static final String NEW_NAME = "new name";

    private Department dept;

    private Department child;

    private Employee emp;

    @BeforeSuite
    public void before() {
        dept = Models.department();
        emp=Models.employee();
    }

    @Test
    public void instantiated() {
        Assert.assertNotNull(dept);
    }

    @Test
    public void setAndGetName() {
        dept.setName(NEW_NAME);
        String name = dept.getName();
        Assert.assertNotNull(name);
        Assert.assertEquals(NEW_NAME, name);
    }

    @Test
    public void setAndGetParentDepartment() {
        child = Models.department(dept);
        Department parent = child.getParentDepartment();
        Assert.assertNotNull(parent);
        Assert.assertEquals(dept.getId(), parent.getId());
    }

    @Test
    public void setAndGetManager(){
        emp.setId(Models.EMPLOYEE_ID);
        dept.setManager(emp);
        Employee manager = dept.getManager();
        Assert.assertNotNull(manager);
        Assert.assertEquals(manager.getId(), emp.getId());
    }
}
