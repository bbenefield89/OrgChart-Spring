package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.Entities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by mrangel on 7/22/2016.
 */
@ContextConfiguration(classes= TestJpaConfig.class)
@Transactional
public class DepartmentRepositoryTest extends AbstractTransactionalTestNGSpringContextTests {
    private static Random random = new Random();

    private static final String SOME_NEW_NAME = "Some New Name";

    private static final String NOT_PRESENT_VALUE = "XXX";

    private static final Integer NOT_PRESENT_ID = -666;

    private DepartmentEntity departmentEntity;
    private DepartmentEntity parent;

    @Autowired
    private DepartmentRepository repository;

    @BeforeClass
    public void before() throws Exception {
        super.springTestContextPrepareTestInstance();
        this.parent = Entities.department();
        this.parent = this.repository.saveAndFlush(parent);

        this.departmentEntity = Entities.department(this.parent);
        this.departmentEntity = this.repository.saveAndFlush(departmentEntity);
    }

    @Test
    public void created() {
       Assert.assertNotNull(this.parent);
        Assert.assertNotNull(this.parent.getId());
        Assert.assertNotNull(this.departmentEntity);
        Assert.assertNotNull(this.departmentEntity.getId());
    }

    @Test(expectedExceptions = DataIntegrityViolationException.class)
    public void duplicateName() throws Exception {
        DepartmentEntity dept = Entities.department();
        dept.setName(this.departmentEntity.getName());
        this.repository.save(dept);
    }

    @Test
    public void findByIsActiveIsTrue() throws Exception {
        List<DepartmentEntity> depts = this.repository.findByIsActiveIsTrue();
        Assert.assertNotNull(depts);
        Assert.assertTrue(0 < depts.size());
    }

    @Test
    public void findAll_notNull() throws Exception {
        List<DepartmentEntity> depts = this.repository.findAll();
        Assert.assertNotNull(depts);
        Assert.assertTrue(0 < depts.size());
    }

    @Test
    public void findByDeptId() throws Exception {
        DepartmentEntity dept = this.repository.findOne(this.departmentEntity.getId());
        Assert.assertNotNull(dept);
        Assert.assertEquals(this.departmentEntity.getName(), dept.getName());
        Assert.assertNotNull(this.departmentEntity.getParentDepartment());
    }

    @Test
    public void findById_notPresent() throws Exception {
        DepartmentEntity dept = this.repository.findOne(NOT_PRESENT_ID);
        Assert.assertNull(dept);
    }

    @Test(expectedExceptions = InvalidDataAccessApiUsageException.class)
    public void findById_null() throws Exception {
        DepartmentEntity dept = this.repository.findOne((Integer) null);
        Assert.assertNull(dept);
    }

    @Test
    public void findByName() throws Exception {
        DepartmentEntity dept = this.repository.findByName(this.departmentEntity.getName());
        Assert.assertNotNull(dept);
        Assert.assertEquals(this.departmentEntity.getName(), dept.getName());
        Assert.assertNotNull(this.departmentEntity.getParentDepartment());
    }

    @Test
    public void findByName_notPresent() throws Exception {
        DepartmentEntity dept = this.repository.findByName(NOT_PRESENT_VALUE);
        Assert.assertNull(dept);
    }

    @Test
    public void findByParentDeptId() throws Exception {
        List<DepartmentEntity> depts = this.repository
                .findByParentDepartment(this.departmentEntity.getParentDepartment());
        Assert.assertNotNull(depts);
        Assert.assertEquals(1, depts.size());
        DepartmentEntity dept = depts.get(0);
        Assert.assertEquals(this.departmentEntity.getName(), dept.getName());
        Assert.assertNotNull(this.departmentEntity.getParentDepartment());
    }

    @Test
    public void findByParentDept_unknown() throws Exception {
        DepartmentEntity parDept = new DepartmentEntity();
        parDept.setId(-66);
        List<DepartmentEntity> depts = this.repository.findByParentDepartment(parDept);
        Assert.assertNotNull(depts);
        Assert.assertEquals(0, depts.size());
    }

    @Test
    public void update() throws Exception {
        DepartmentEntity dept = this.repository.findByName(this.departmentEntity.getName());
        dept.setName(SOME_NEW_NAME);
        this.repository.saveAndFlush(dept);

        dept = null;
        dept = this.repository.findByName(SOME_NEW_NAME);
        Assert.assertNotNull(dept);
        Assert.assertEquals(SOME_NEW_NAME, dept.getName());
    }

}
