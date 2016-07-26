package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by mrangel on 7/22/2016.
 */
@ContextConfiguration(classes= TestJpaConfig.class)
@Transactional
public class DepartmentRepositoryTest  extends AbstractTransactionalTestNGSpringContextTests {
    private static Random random = new Random();

    private static final String SOME_NEW_NAME = "Some New Name";

    private static final String NOT_PRESENT_VALUE = "XXX";

    private static final Integer NOT_PRESENT_ID = -666;

    private Department department;
    private Department parent;

    @Autowired
    private DepartmentRepository repository;

    @BeforeClass
    public void before() throws Exception {
        super.springTestContextPrepareTestInstance();
        this.parent = Entities.department();
        this.parent = this.repository.saveAndFlush(parent);

        this.department = Entities.department(this.parent);
        this.department = this.repository.saveAndFlush(department);
    }

    @Test
    public void created() {
       Assert.assertNotNull(this.parent);
        Assert.assertNotNull(this.parent.getId());
        Assert.assertNotNull(this.department);
        Assert.assertNotNull(this.department.getId());
    }

    @Test(expectedExceptions = DataIntegrityViolationException.class)
    public void duplicateName() throws Exception {
        Department dept = Entities.department();
        dept.setName(this.department.getName());
        this.repository.save(dept);
    }

    @Test
    public void findByIsActiveIsTrue() throws Exception {
        List<Department> depts = this.repository.findByIsActiveIsTrue();
        Assert.assertNotNull(depts);
        Assert.assertTrue(0 < depts.size());
    }

    @Test
    public void findAll_notNull() throws Exception {
        List<Department> depts = this.repository.findAll();
        Assert.assertNotNull(depts);
        Assert.assertTrue(0 < depts.size());
    }

    @Test
    public void findByDeptId() throws Exception {
        Department dept = this.repository.findOne(this.department.getId());
        Assert.assertNotNull(dept);
        Assert.assertEquals(this.department.getName(), dept.getName());
        Assert.assertNotNull(this.department.getParentDepartment());
    }

    @Test
    public void findById_notPresent() throws Exception {
        Department dept = this.repository.findOne(NOT_PRESENT_ID);
        Assert.assertNull(dept);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findById_null() throws Exception {
        Department dept = this.repository.findOne((Integer) null);
        Assert.assertNull(dept);
    }

    @Test
    public void findByName() throws Exception {
        Department dept = this.repository.findByName(this.department.getName());
        Assert.assertNotNull(dept);
        Assert.assertEquals(this.department.getName(), dept.getName());
        Assert.assertNotNull(this.department.getParentDepartment());
    }

    @Test
    public void findByName_notPresent() throws Exception {
        Department dept = this.repository.findByName(NOT_PRESENT_VALUE);
        Assert.assertNull(dept);
    }

    @Test
    public void findByParentDeptId() throws Exception {
        List<Department> depts = this.repository
                .findByParentDepartmentId(this.department.getParentDepartment().getId());
        Assert.assertNotNull(depts);
        Assert.assertEquals(1, depts.size());
        Department dept = depts.get(0);
        Assert.assertEquals(this.department.getName(), dept.getName());
        Assert.assertNotNull(this.department.getParentDepartment());
    }

    @Test
    public void findByParentDeptId_unknownId() throws Exception {
        List<Department> depts = this.repository.findByParentDepartmentId(random.nextInt());
        Assert.assertNotNull(depts);
        Assert.assertEquals(0, depts.size());
    }

    @Test
    public void update() throws Exception {
        Department dept = this.repository.findByName(this.department.getName());
        dept.setName(SOME_NEW_NAME);
        this.repository.saveAndFlush(dept);

        dept = null;
        dept = this.repository.findByName(SOME_NEW_NAME);
        Assert.assertNotNull(dept);
        Assert.assertEquals(SOME_NEW_NAME, dept.getName());
    }

}
