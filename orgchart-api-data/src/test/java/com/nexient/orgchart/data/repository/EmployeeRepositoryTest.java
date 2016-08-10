package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
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
    private DepartmentEntity departmentEntity = new DepartmentEntity();
    private JobTitleEntity jobTitle = new JobTitleEntity();
    private EmployeeEntity employee;
    private EmployeeEntity manager;

//    @Autowired
//    private EmployeeRepository empRepo;
//
//    @Autowired
//    private DepartmentRepository deptRepo;
//
//    @Autowired
//    private JobTitleRepository jobTitleRepo;
//
//    @BeforeClass
//    public void before() throws Exception {
//        super.springTestContextPrepareTestInstance();
//        this.departmentEntity = Entities.department();
//        this.deptRepo.saveAndFlush(this.departmentEntity);
//
//        this.jobTitle = Entities.jobTitle();
//        this.jobTitleRepo.saveAndFlush(jobTitle);
//
//        this.employee = Entities.employee();
//        this.employee.setJobTitle(this.jobTitle);
//        this.employee.setDepartment(this.departmentEntity);
//        this.empRepo.saveAndFlush(this.employee);
//    }
//
//    @Test
//    public void testInstantiation() {
//        Assert.assertNotNull(deptRepo);
//        Assert.assertNotNull(jobTitleRepo);
//        Assert.assertNotNull(empRepo);
//    }
//
//    @Test
//    public void findAll() throws Exception {
//        List<EmployeeEntity> emps = this.empRepo.findAll();
//        Assert.assertNotNull(emps, "Expecting a non-null EmployeeEntity List but was null");
//        Assert.assertTrue(0 < emps.size());
//    }
//
//    @Test
//    public void findByDepartment() throws Exception {
//        List<EmployeeEntity> emps = this.empRepo.findByDepartment(this.employee.getDepartment());
//        Assert.assertNotNull(emps, "Expecting a non-null EmployeeEntity List but was null");
//        EmployeeEntity emp = emps.get(0);
//        Assert.assertEquals(this.employee.getId(), emp.getId());
//        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
//        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
//        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
//    }
//
//    @Test
//    public void findByDepartment_null() throws Exception {
//        List<EmployeeEntity> emps = this.empRepo.findByDepartment(null);
//        Assert.assertTrue(emps.isEmpty());
//    }
//
//    @Test
//    public void findByEmail() throws Exception {
//        EmployeeEntity emp = this.empRepo.findByEmail(this.employee.getEmail());
//        Assert.assertNotNull(emp, "Expecting a non-null EmployeeEntity but was null");
//        Assert.assertEquals(this.employee.getId(), emp.getId());
//        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
//        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
//        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
//    }
//
//    @Test
//    public void findByEmail_null() throws Exception {
//        EmployeeEntity emp = this.empRepo.findByEmail(null);
//        Assert.assertNull(emp, "Expecting a null EmployeeEntity but was non-null");
//    }
//
//    @Test
//    public void findByEmailTest_notPresent() throws Exception {
//        EmployeeEntity emp = this.empRepo.findByEmail(NOT_PRESENT_VALUE);
//        Assert.assertNull(emp, "Expecting a null EmployeeEntity but was non-null");
//    }
//
//    @Test
//    public void findById() throws Exception {
//        EmployeeEntity emp = this.empRepo.findOne(this.employee.getId());
//        Assert.assertNotNull(emp, "Expecting a non-null EmployeeEntity but was null");
//        Assert.assertEquals(this.employee.getId(), emp.getId());
//        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
//        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
//        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
//    }
//
//    @Test (expectedExceptions = InvalidDataAccessApiUsageException.class)
//    public void findById_null() throws Exception {
//        EmployeeEntity emp = this.empRepo.findOne((Integer) null);
//        Assert.assertNull(emp, "Expecting a null EmployeeEntity but was non-null");
//    }
//
//    @Test
//    public void findById_notPresent() throws Exception {
//        EmployeeEntity emp = this.empRepo.findOne(NOT_PRESENT_ID);
//        Assert.assertNull(emp, "Expecting a null EmployeeEntity but was non-null");
//    }
//
//    private void createManager() {
//        this.manager = Entities.manager();
//        this.empRepo.save(this.manager);
//    }
//
//    @Test
//    public void findByManagerId() throws Exception {
//        createManager();
//
//        this.employee.setManager(this.manager);
//        this.empRepo.save(this.employee);
//
//        List<EmployeeEntity> emps = this.empRepo.findByManager(this.employee.getManager());
//        Assert.assertNotNull(emps, "Expecting a null EmployeeEntity but was non-null");
//        Assert.assertTrue(emps.size() > 0, "Expecting a null EmployeeEntity but was non-null");
//        EmployeeEntity emp = emps.get(0);
//        Assert.assertEquals(this.employee.getFirstName(), emp.getFirstName());
//        Assert.assertEquals(this.employee.getLastName(), emp.getLastName());
//        Assert.assertEquals(this.employee.getEmail(), emp.getEmail());
//    }
//
//    @Test
//    public void findByManagerId_empty() throws Exception {
//        EmployeeEntity mgr = Entities.employee();
//        this.empRepo.saveAndFlush(mgr);
//
//        List<EmployeeEntity> emps = this.empRepo.findByManager(mgr);
//        Assert.assertTrue(emps.isEmpty());
//    }
//
//    @Test
//    public void findByJobTitle(){
//        employee.setJobTitle(this.jobTitle);
//        this.empRepo.saveAndFlush(employee);
//        List<EmployeeEntity> emps = this.empRepo.findByJobTitle(this.jobTitle);
//        Assert.assertEquals(emps.size(),1);
//    }
//
//    @Test
//    public void findByJobTitle_Null(){
//        List<EmployeeEntity> emps = this.empRepo.findByJobTitle(null);
//        Assert.assertTrue(emps.isEmpty());
//    }
//
//    @Test
//    public void findByIsManager(){
//        List<EmployeeEntity> mgrs = this.empRepo.findByIsManager(true);
//        Assert.assertTrue(mgrs.isEmpty());
//    }
}
