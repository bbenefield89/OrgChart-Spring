package com.nexient.orgchart.web;

import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.EmployeeService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by mrangel on 8/1/2016.
 */
public class EmployeeControllerTest {

    private Employee employee;
    private ArrayList<Employee> findAllEmployeeList;

    @Mock
    private EmployeeService mockEmployeeService;

    @InjectMocks
    private EmployeeController controller = new EmployeeController();

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        this.employee = Models.employee();
        this.employee.setId(Models.EMPLOYEE_ID);
        this.findAllEmployeeList = new ArrayList<>();
        this.findAllEmployeeList.add(employee);

        when(this.mockEmployeeService.findAllActiveEmployees()).thenReturn(this.findAllEmployeeList);
        when(this.mockEmployeeService.findEmployeeById(anyInt())).thenReturn(this.employee);
        when(this.mockEmployeeService.findAllInactiveEmployees()).thenReturn(this.findAllEmployeeList);
        when(this.mockEmployeeService.storeOrUpdate(any(Employee.class))).thenReturn(this.employee);
        when(this.mockEmployeeService.removeEmployee(this.employee.getId())).thenReturn(true);
    }

    @Test
    public void getAllActiveEmployeesTest(){
        List<Employee> empList = this.mockEmployeeService.findAllActiveEmployees();

        Assert.assertNotNull(empList);
        Assert.assertFalse(empList.isEmpty());
    }

    @Test
    public void readEmployeeTest(){
        Employee emp = this.mockEmployeeService.findEmployeeById(employee.getId());

        Assert.assertNotNull(emp);
        Assert.assertEquals(this.employee.getId(), emp.getId());
        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
    }

    @Test
    public void findAllInactiveEmployeesTest(){
        List<Employee> emps = this.mockEmployeeService.findAllInactiveEmployees();

        Assert.assertNotNull(emps);
        Assert.assertFalse(emps.isEmpty());
    }


    @Test
    public void createOrUpdateTest(){
        Employee emp = this.mockEmployeeService.storeOrUpdate(employee);

        Assert.assertNotNull(emp);
        Assert.assertEquals(employee.getId(), emp.getId());
    }

    @Test
    public void deleteTest(){
        boolean hasDeleted = this.mockEmployeeService.removeEmployee(employee.getId());

        Assert.assertNotNull(hasDeleted);
        Assert.assertTrue(hasDeleted);
    }

}
