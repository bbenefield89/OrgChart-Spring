package com.nexient.orgchart.data.entity;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by mrangel on 7/21/2016.
 */
public class DepartmentEntityTest {

    private static final String NEW_NAME = "new name";

    private DepartmentEntity dept;
    private EmployeeEntity emp;
    private DepartmentEntity child;

    private Set<DepartmentEntity> childDepartments;
    private Set<EmployeeEntity> departmentEmployees;

    private static final Random RANDOM = new Random();

//    @BeforeSuite
//    public void before() {
//        dept = Entities.department();
//        emp = Entities.employee();
//        child = Entities.department();
//
//        childDepartments= new HashSet<>();
//        departmentEmployees= new HashSet<>();
//
//        emp.setId(Entities.EMPLOYEE_ID);
//    }

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
        DepartmentEntity parent = child.getParentDepartment();
        Assert.assertNotNull(parent);
        Assert.assertEquals(dept.getId(), parent.getId());
    }

    @Test
    public void setAndGetManager(){
        dept.setManager(emp);
        Assert.assertNotNull(dept.getManager());
        Assert.assertEquals(dept.getManager().getId(), emp.getId());
    }

    @Test
    public void setAndGetChildDepartments(){
        childDepartments.add(child);
        dept.setChildDepartments(childDepartments);
        Assert.assertNotNull(dept.getChildDepartments());
    }

    @Test
    public void setAndGetEmployees(){
        departmentEmployees.add(emp);
        dept.setDepartmentEmployees(departmentEmployees);
        Assert.assertNotNull(dept.getDepartmentEmployees());
    }

}
