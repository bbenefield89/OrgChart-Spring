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

//	@Autowired
//	private DepartmentService departmentService;
//
//	private ArrayList<Department> listOfFoundDepts = new ArrayList<Department>();
//
//	@Test
//	public void shouldFindDepartmentByID() {
//
//	}
//
//	private void assertTrue(boolean b) {
//	}
//
//	@Test
//	public void shouldFindAllDepartments() {
//		assertNotNull(departmentService.findAll());
//	}
//
//	@Test
//	public void shouldStoreDepartmentUsingDepartmentDAO() {
////		assertNotNull(departmentService.saveOrUpdate(mockDepartment));
////		assertEquals(TestObject.DEPT_ID, departmentService.saveOrUpdate(mockDepartment));
//	}

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
		Department dept = this.departmentService.storeDepartment(this.mockDepartment);
		Assert.assertNotNull(dept);
		Assert.assertEquals( Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
	}

	//TODO write test
//	@Test
//    public void removeDepartment(){
//
//	}

	@Test
	public void updateDepartment() throws Exception{
		mockDepartment.setName("Other Dept Name");
		departmentService.updateDepartment(mockDepartment);
		Assert.assertEquals(this.mockDepartment.getName(), departmentService.findDepartmentByID(mockDepartment.getId()).getName());
	}

	@Test
	public void findAllActiveDepartments(){
		List<Department> depts = departmentService.findAllActiveDepartments();
		Assert.assertTrue(depts.isEmpty());
	}
	
	@Test(expectedExceptions = Exception.class)
	public void testUpdateDepartmentIdNull() throws Exception{
		Department child = new Department();
		departmentService.updateDepartment(child);
	}

// TODO finish this test
//	@Test
//	public void testUpdateParentDepartmentIdNonexistent() throws Exception{
//		Department parent = new Department();
//		parent.setId(-1);
//		mockDepartment.setParentDepartment(parent);
//		departmentService.updateDepartment(mockDepartment);
//		Assert.assertNull(mockDepartment.getParentDepartment().getId());
//	}

}
