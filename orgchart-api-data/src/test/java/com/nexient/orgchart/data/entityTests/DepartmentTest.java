package com.nexient.orgchart.data.entityTests;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Employee;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by mrangel on 7/21/2016.
 */
public class DepartmentTest {

    private static final String NEW_NAME = "new name";

    private Department dept;
    private Employee emp;
    private Department child;

    private static final Random RANDOM = new Random();

    @BeforeSuite
    public void before() {
        dept = Entities.department();
//        emp = Entities.employee();
//        emp.setId(Entities.EMPLOYEE_ID);
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
        child = Entities.department(dept);
        Department parent = child.getParentDepartment();
        Assert.assertNotNull(parent);
        Assert.assertEquals(dept.getId(), parent.getId());
    }

//    @Test
//    public void setAndGetManager(){
//        dept.setManager(emp);
//        Assert.assertNotNull(dept.getManager());
//        Assert.assertEquals(dept.getManager().getId(), emp.getId());
//    }

}
