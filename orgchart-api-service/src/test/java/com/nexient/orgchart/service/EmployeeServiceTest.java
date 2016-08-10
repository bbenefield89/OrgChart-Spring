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

    @Spy
    private EmployeeMapper employeeMapper = new EmployeeMapper();

    @Spy
    private DepartmentMapper departmentMapper = new DepartmentMapper();

    @Spy
    private JobTitleMapper jobTitleMapper = new JobTitleMapper();

    @Mock
    private EmployeeRepository repo;

    @Mock
    private DepartmentRepository deptRepo;

    private EmployeeEntity employee;
    private EmployeeEntity manager;
    private DepartmentEntity department;
    private List<EmployeeEntity> listOfFoundEmployees;
    private List<DepartmentEntity> listOfFoundDepts;

//    @BeforeTest
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//
//        manager = Entities.manager();
//        manager.setId(Entities.MANAGER_ID);
//        employee = Entities.employee();
//        employee.setId(Entities.EMPLOYEE_ID);
//        employee.setManager(manager);
//        department = Entities.department(Entities.DEPT_ID);
//        department.setManager(manager);
//
//        listOfFoundEmployees = new ArrayList<>();
//        listOfFoundEmployees.add(employee);
//
//        listOfFoundDepts = new ArrayList<>();
//        listOfFoundDepts.add(department);
//
//        employeeMapper.setDepartmentMapper(departmentMapper);
//        employeeMapper.setJobTitleMapper(jobTitleMapper);
//
//        when(repo.findAll()).thenReturn(listOfFoundEmployees);
//        when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(employee);
//        when(repo.save(any(EmployeeEntity.class))).thenReturn(employee);
//        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundEmployees);
//        when(repo.findByDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundEmployees);
//        when(repo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundEmployees);
//        when(repo.findByJobTitle(any(JobTitleEntity.class))).thenReturn(this.listOfFoundEmployees);
//        when(deptRepo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);
//        when(repo.findByIsActiveIsFalse()).thenReturn(this.listOfFoundEmployees);
//    }
//
//    @Test
//    public void findAllEmployees() {
//        List<Employee> emps = this.employeeService.findAll();
//        Assert.assertNotNull(this.employeeService.findAll());
//        Assert.assertTrue(emps.size() > 0);
//    }
//
//    @Test
//    public void findByEmployeeID() {
//        employee.setId(Entities.EMPLOYEE_ID);
//        Employee emp = this.employeeService.findEmployeeById(employee.getId());
//        Assert.assertNotNull(emp);
//        Assert.assertEquals(Models.EMPLOYEE_ID, emp.getId());
//
//    }
//
//    @Test
//    public void findByIsActiveIsTrue() {
//        List<Employee> emps = employeeService.findAllActiveEmployees();
//        Assert.assertNotNull(emps);
//        Assert.assertTrue(emps.size() > 0);
//    }
//
//    @Test
//    public void findByIsActiveIsFalse() {
//        List<Employee> emps = employeeService.findAllInactiveEmployees();
//        Assert.assertNotNull(emps);
//        Assert.assertTrue(emps.size() > 0);
//    }
//
//    @Test
//    public void storeEmployee() {
//        Employee emp = this.employeeService.storeOrUpdate(employeeMapper.entityToModel(this.employee));
//        Assert.assertNotNull(emp);
//        Assert.assertEquals(emp.getId(), employee.getId());
//    }
//
//    @Test
//    public void removeEmployee_false() {
//        this.employee.setIsActive(true);
//        this.employee.setJobTitle(Entities.jobTitle());
//        this.employee.setDepartment(Entities.department());
//
//        doAnswer(new Answer<EmployeeEntity>() {
//            @Override
//            public EmployeeEntity answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                EmployeeEntity empy = (EmployeeEntity) args[0];
//                empy.setIsActive(true);
//                return empy;
//            }
//        }).when(this.repo).save(any(EmployeeEntity.class));
//
//        Assert.assertFalse(this.employeeService.removeEmployee(this.employee.getId()));
//    }
//
//    @Test
//    public void removeEmployee() {
//        this.employee.setIsActive(true);
//        EmployeeEntity employee = this.employee;
//        employee.setIsActive(true);
//        employee.setJobTitle(Entities.jobTitle());
//        employee.setDepartment(Entities.department());
//
//        Assert.assertTrue(this.employeeService.removeEmployee(employee.getId()));
//    }
//
//    @Test
//    public void updateEmployee() {
//        this.employee.setFirstName("Other Employee Entity Name");
//        this.employeeService.storeOrUpdate(employeeMapper.entityToModel(this.employee));
//        Assert.assertEquals(this.employee.getFirstName(), employeeService.findEmployeeById(employee.getId()).getFirstName());
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void storeDepartment_Null() throws Exception {
//        this.employeeService.storeOrUpdate(null);
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void removeDepartment_Null() throws Exception {
//        this.employeeService.removeEmployee(null);
//    }
//
//    @Test
//    public void testSetDepartmentManagerToNullByManager() {
//        List<DepartmentEntity> depts = employeeService.setDepartmentManagerToNullByManager(listOfFoundDepts);
//        Assert.assertNotNull(depts);
//        Assert.assertTrue(depts.size() > 0);
//
//        for(DepartmentEntity d: depts){
//            Assert.assertNull(d.getManager());
//        }
//    }
//
//    @Test
//    public void testSetEmployeeManagerToNullByManager() {
//        List<EmployeeEntity> emps = employeeService.setEmployeeManagerToNullByManager(listOfFoundEmployees);
//
//        Assert.assertNotNull(emps);
//        Assert.assertTrue(emps.size() > 0);
//
//        for(EmployeeEntity e: emps){
//            Assert.assertNull(e.getManager());
//        }
//    }

}
