package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.mapper.JobTitleMapper;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.Models;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

    private EmployeeEntity employee;

	@Spy
    private EmployeeMapper employeeMapper = new EmployeeMapper();

	@Spy
	private DepartmentMapper departmentMapper = new DepartmentMapper();

	@Spy
	private JobTitleMapper jobTitleMapper = new JobTitleMapper();

    @Mock
    private EmployeeRepository repo;

    @Mock
    private DepartmentEntity mockDepartment;

    @Mock
    private DepartmentRepository deptRepo;

    private List<EmployeeEntity> listOfFoundEmployees;

    private List<DepartmentEntity> listOfFoundDepts;

    @BeforeTest
	public void before() {
        MockitoAnnotations.initMocks(this);

	    listOfFoundEmployees = new ArrayList<>();
        listOfFoundEmployees.add(Entities.employee());
        listOfFoundDepts = new ArrayList<>();
        mockDepartment = Entities.department(Entities.DEPT_ID);
        listOfFoundDepts.add(mockDepartment);

		employeeMapper.setDepartmentMapper(departmentMapper);
		employeeMapper.setJobTitleMapper(jobTitleMapper);

		employee = Entities.employee();
		employee.setId(Entities.EMPLOYEE_ID);

        when(repo.findAll()).thenReturn(listOfFoundEmployees);
        when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(employee);
        when(repo.save(any(EmployeeEntity.class))).thenReturn(employee);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundEmployees);
        when(repo.findByDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(repo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(repo.findByJobTitle(any(JobTitleEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(deptRepo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);

	}

	@AfterTest
    public void after(){
    }

	@Test
	public void findAllEmployees() {
	    List<Employee> emps = this.employeeService.findAll();
		Assert.assertNotNull(this.employeeService.findAll());
        Assert.assertTrue(emps.size() > 0);
	}

	@Test
	public void findByEmployeeID() {
	    employee.setId(Entities.EMPLOYEE_ID);
		Employee emp = this.employeeService.findEmployeeById(employee.getId());
        Assert.assertNotNull(emp);
        Assert.assertEquals(Models.EMPLOYEE_ID, emp.getId());

	}

	@Test
	public void findByIsActiveIsTrue(){
		List<Employee> emps = employeeService.findByIsActive();
		Assert.assertNotNull(emps);
        Assert.assertTrue(emps.size()>0);
	}

	@Test
	public void storeEmployee(){
		Employee emp = this.employeeService.storeOrUpdate(employeeMapper.entityToModel(this.employee));
		Assert.assertNotNull(emp);
		Assert.assertEquals( emp.getId(), employee.getId());
	}

	@Test
	public void removeEmployee_false(){
		this.employee.setIsActive(true);
		this.employee.setJobTitle(Entities.jobTitle());
		this.employee.setDepartment(Entities.department());

        doAnswer(new Answer<EmployeeEntity>() {
            @Override
            public EmployeeEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                EmployeeEntity empy = (EmployeeEntity) args[0];
                empy.setIsActive(true);
                return empy;
            }
        }).when(this.repo).save(any(EmployeeEntity.class));

		Assert.assertFalse(this.employeeService.removeEmployee(this.employee.getId()));
	}

	@Test
	public void removeEmployee(){
	    this.employee.setIsActive(true);
		EmployeeEntity employee = this.employee;
		employee.setIsActive(true);
		employee.setJobTitle(Entities.jobTitle());
		employee.setDepartment(Entities.department());

		Assert.assertTrue(this.employeeService.removeEmployee(employee.getId()));
	}

	@Test
	public void updateEmployee() {
		this.employee.setFirstName("Other Employee Entity Name");
		this.employeeService.storeOrUpdate(employeeMapper.entityToModel(this.employee));
		Assert.assertEquals(this.employee.getFirstName(), employeeService.findEmployeeById(employee.getId()).getFirstName());
	}

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeDepartment_Null()throws Exception{
        this.employeeService.storeOrUpdate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeDepartment_Null()throws Exception{
        this.employeeService.removeEmployee(null);
    }

}
