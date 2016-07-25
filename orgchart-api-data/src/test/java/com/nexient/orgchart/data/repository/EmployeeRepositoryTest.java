package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by dhoover on 7/21/2016.
 */
@ContextConfiguration(classes = TestJpaConfig.class)
@Transactional
public class EmployeeRepositoryTest  extends AbstractTransactionalTestNGSpringContextTests{

    private static final String NOT_PRESENT_VALUE = "XXX";
    private static final Integer NOT_PRESENT_ID = -666;
    private Department department = new Department();
    private JobTitle jobTitle = new JobTitle();
    private Employee employee;
    private Employee manager;

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private DepartmentRepository deptRepo;

    @Autowired
    private JobTitleRepository jobTitleRepo;

    @BeforeClass
    public void before() throws Exception {
        super.springTestContextPrepareTestInstance();
        this.department = Entities.department();
        this.deptRepo.saveAndFlush(this.department);

        this.jobTitle = Entities.jobTitle();
        this.jobTitleRepo.saveAndFlush(jobTitle);

        this.employee = Entities.employee();
        this.employee.setDepartment(this.department);
        this.employee.setJobTitle(this.jobTitle);
        this.empRepo.saveAndFlush(this.employee);
    }

    @AfterSuite
    public void after() {
        this.empRepo.delete(this.employee);
        this.deptRepo.delete(this.department);
        this.jobTitleRepo.delete(this.jobTitle);

        if (this.manager != null) {
            this.empRepo.delete(this.manager);
        }
    }

    @Test
    public void testInstantiation() {
        Assert.assertNotNull(deptRepo);
        Assert.assertNotNull(jobTitleRepo);
        Assert.assertNotNull(empRepo);
    }

    @Test
    public void findAll() throws Exception {
        List<Employee> emps = this.empRepo.findAll();
        Assert.assertNotNull(emps, "Expecting a non-null Employee List but was null");
        Assert.assertTrue(0 < emps.size());
    }

    @Test
    public void findByDepartment() throws Exception {
        List<Employee> emps = this.empRepo.findByDepartment(this.employee.getDepartment());
        Assert.assertNotNull(emps, "Expecting a non-null Employee List but was null");
        Employee emp = emps.get(0);
        Assert.assertEquals(this.employee.getId(), emp.getId());
        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
    }

    @Test
    public void findByDepartment_null() throws Exception {
        List<Employee> emps = this.empRepo.findByDepartment(null);
        Assert.assertTrue(emps.isEmpty());
    }

    @Test
    public void findByEmail() throws Exception {
        Employee emp = this.empRepo.findByEmail(this.employee.getEmail());
        Assert.assertNotNull(emp, "Expecting a non-null Employee but was null");
        Assert.assertEquals(this.employee.getId(), emp.getId());
        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
    }

    @Test
    public void findByEmail_null() throws Exception {
        Employee emp = this.empRepo.findByEmail(null);
        Assert.assertNull(emp, "Expecting a null Employee but was non-null");
    }

    @Test
    public void findByEmailTest_XXX() throws Exception {
        Employee emp = this.empRepo.findByEmail(NOT_PRESENT_VALUE);
        Assert.assertNull(emp, "Expecting a null Employee but was non-null");
    }

    @Test
    public void findById() throws Exception {
        Employee emp = this.empRepo.findOne(this.employee.getId());
        Assert.assertNotNull(emp, "Expecting a non-null Employee but was null");
        Assert.assertEquals(this.employee.getId(), emp.getId());
        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
    }

    @Test (expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findById_null() throws Exception {
        Employee emp = this.empRepo.findOne((Integer) null);
        Assert.assertNull(emp, "Expecting a null Employee but was non-null");
    }

    @Test
    public void findById_XXX() throws Exception {
        Employee emp = this.empRepo.findOne(NOT_PRESENT_ID);
        Assert.assertNull(emp, "Expecting a null Employee but was non-null");
    }

    private void createManager() {
        this.manager = Entities.manager();
        this.empRepo.save(this.manager);
    }

    @Test
    public void findByManagerId() throws Exception {
        createManager();

        this.employee.setManager(this.manager);
        this.empRepo.save(this.employee);

        List<Employee> emps = this.empRepo.findByManager(this.employee.getManager());
        Assert.assertNotNull(emps, "Expecting a null Employee but was non-null");
        Assert.assertTrue(emps.size() > 0, "Expecting a null Employee but was non-null");
        Employee emp = emps.get(0);
        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
    }

    @Test
    public void findByManagerId_empty() throws Exception {
        Employee emp = Entities.employee();
        this.empRepo.saveAndFlush(emp);

        List<Employee> emps = this.empRepo.findByManager(emp);
        Assert.assertTrue(emps.isEmpty());
    }

    @Test
    public void findByJobTitle(){
        employee.setJobTitle(this.jobTitle);
        this.empRepo.saveAndFlush(employee);
        List<Employee> emps = this.empRepo.findByJobTitle(this.jobTitle);
        Assert.assertEquals(emps.size(),1);
    }

    @Test
    public void findByIsManager(){
        List<Employee> mgrs = this.empRepo.findByIsManager(true);
        Assert.assertTrue(mgrs.isEmpty());
    }
}
