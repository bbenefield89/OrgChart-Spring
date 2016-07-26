package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.nexient.orgchart.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class EmployeeServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	Employee mockEmployee;

	@BeforeClass
	public void before() {

	}

	@Test
	public void findAllEmployees() {
		assertNotNull(this.employeeService.findAll());
	}

	@Test
	public void findByEmployeeID() {
		Employee emp = this.employeeService.findEmployeeById(Entities.EMPLOYEE_ID);
		assertNotNull(emp);
		assertEquals(Entities.EMPLOYEE_ID, emp.getId());

	}

	@Test
	public void findByIsActiveIsTrue(){
		List<Employee> emps = employeeService.findByIsActive();
		Assert.assertTrue(emps.isEmpty());
	}

	@Test
	public void storeEmployee(){
		Employee emp = this.employeeService.storeOrUpdate(this.mockEmployee);
		Assert.assertNotNull(emp);
		Assert.assertEquals( emp.getId(), Entities.EMPLOYEE_ID);
	}

	@Test
	public void removeEmployee(){
		this.mockEmployee.setIsActive(true);
		this.employeeService.removeEmployee(this.mockEmployee);
		Assert.assertFalse(this.mockEmployee.getIsActive());
	}

	@Test
	public void updateEmployee() {
		this.mockEmployee.setFirstName("Other Employee Name");
		this.employeeService.storeOrUpdate(this.mockEmployee);
		Assert.assertEquals(this.mockEmployee.getFirstName(), employeeService.findEmployeeById(mockEmployee.getId()).getFirstName());
	}
}
