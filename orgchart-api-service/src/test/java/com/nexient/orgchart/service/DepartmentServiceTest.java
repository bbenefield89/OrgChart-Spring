package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.model.Department;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class DepartmentServiceTest {

	@InjectMocks
	private DepartmentService departmentService;

	private DepartmentEntity department;

	@Spy
	private DepartmentMapper departmentMapper = new DepartmentMapper();

	@Spy
	private EmployeeMapper employeeMapper = new EmployeeMapper();

	@Mock
	private DepartmentRepository repo;

	private List<DepartmentEntity> listOfFoundDepts;

	@BeforeTest
	public void before(){
		MockitoAnnotations.initMocks(this);

		listOfFoundDepts = new ArrayList<>();
		listOfFoundDepts.add(Entities.department());

		department=Entities.department();
		department.setId(Entities.DEPT_ID);

		departmentMapper.setEmployeeMapper(employeeMapper);

		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.department);
		when(repo.save(any(DepartmentEntity.class))).thenReturn(this.department);
		when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
		when(repo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);
		when(repo.findByParentDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundDepts);

	}
	@Test
	public void findAllDepartments() {
		List<Department> depts = this.departmentService.findAllDepartments();
		assertNotNull(depts);
		Assert.assertTrue(depts.size() > 0);
	}

	@Test
	public void findDepartment() {
		Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
		assertNotNull(dept);
		assertEquals(Entities.DEPT_ID, dept.getId());
	}

	@Test
	public void storeDepartment() {
		Department dept = this.departmentService.storeOrUpdate(departmentMapper.entityToModel(this.department));
		Assert.assertNotNull(dept);
		Assert.assertEquals(Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
	}

	@Test
	public void removeDepartment() {
		this.department.setIsActive(true);
		this.department.setParentDepartment(Entities.department());
		this.department.setManager(Entities.manager());

		Assert.assertTrue(departmentService.removeDepartment(this.department.getId()));
	}

	@Test
	public void removeDepartment_False(){
		this.department.setIsActive(true);

		doAnswer(new Answer<DepartmentEntity>() {
			@Override
			public DepartmentEntity answer(InvocationOnMock invocation) throws Throwable {
				Object[] args = invocation.getArguments();
				DepartmentEntity depy = (DepartmentEntity) args[0];
				depy.setIsActive(true);
				return depy;
			}
		}).when(this.repo).save(any(DepartmentEntity.class));

		Assert.assertFalse(this.departmentService.removeDepartment(department.getId()));
	}
	@Test
	public void updateDepartment() throws Exception {
		department.setName("Other Dept Name");

		departmentService.storeOrUpdate(departmentMapper.entityToModel(this.department));
		Assert.assertEquals(this.department.getName(), departmentService.findDepartmentByID(department.getId()).getName());
	}

	@Test
	public void findAllActiveDepartments() {
		List<Department> depts = departmentService.findAllActiveDepartments();
		Assert.assertTrue(depts.size()>0);
	}

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeDepartment_Null()throws Exception{
        this.departmentService.storeOrUpdate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeDepartment_Null()throws Exception{
        this.departmentService.removeDepartment(null);
    }

}
