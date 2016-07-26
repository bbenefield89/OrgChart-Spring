package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;


import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class DepartmentServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private DepartmentService departmentService;

	@Autowired
	private DepartmentEntity mockDepartmentEntity;

	@Test
	public void findAllDepartments() {
		List<DepartmentEntity> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		assertTrue(depts.size() > 0);
	}

	@Test
	public void findDepartmentByID() {
		DepartmentEntity dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		DepartmentEntity dept = this.departmentService.storeOrUpdateDepartment(this.mockDepartmentEntity);
		Assert.assertNotNull(dept);
		Assert.assertEquals(Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
	}

	@Test
	public void removeDepartment() {
		mockDepartmentEntity.setIsActive(true);
		departmentService.removeDepartment(mockDepartmentEntity);
		Assert.assertFalse(mockDepartmentEntity.getIsActive());
	}

	@Test
	public void updateDepartment() throws Exception {
		mockDepartmentEntity.setName("Other Dept Name");
		departmentService.storeOrUpdateDepartment(mockDepartmentEntity);
		Assert.assertEquals(this.mockDepartmentEntity.getName(), departmentService.findDepartmentByID(mockDepartmentEntity.getId()).getName());
	}

	@Test
	public void findAllActiveDepartments() {
		List<DepartmentEntity> depts = departmentService.findAllActiveDepartments();
		Assert.assertTrue(depts.isEmpty());
	}

}
