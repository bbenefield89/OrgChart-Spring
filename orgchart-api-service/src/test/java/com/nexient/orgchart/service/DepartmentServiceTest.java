package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.nexient.orgchart.service.DepartmentService;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class DepartmentServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private Department mockDepartment;

	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void findDepartmentByID() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		Department dept = this.departmentService.storeOrUpdateDepartment(this.mockDepartment);
		Assert.assertNotNull(dept);
		Assert.assertEquals(Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
	}

	@Test
	public void removeDepartment() {
		mockDepartment.setIsActive(true);
		departmentService.removeDepartment(mockDepartment);
		Assert.assertFalse(mockDepartment.getIsActive());
	}

	@Test
	public void updateDepartment() throws Exception {
		mockDepartment.setName("Other Dept Name");
		departmentService.storeOrUpdateDepartment(mockDepartment);
		Assert.assertEquals(this.mockDepartment.getName(), departmentService.findDepartmentByID(mockDepartment.getId()).getName());
	}

	@Test
	public void findAllActiveDepartments() {
		List<Department> depts = departmentService.findAllActiveDepartments();
		Assert.assertTrue(depts.isEmpty());
	}

}
