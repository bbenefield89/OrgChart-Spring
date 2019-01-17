package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by dhoover on 7/26/2016.
 */
public class EmployeeMapperTest {

    @InjectMocks
    private EmployeeMapper empMapper;

    @Mock
    private JobTitleMapper titleMapper;

    @Mock
    private DepartmentMapper deptMapper;

    private EmployeeEntity employeeEntity;
    private Employee employeeModel;
    private DepartmentEntity departmentEntity;
    private Department departmentModel;
    private JobTitleEntity titleEntity;
    private JobTitle titleModel;
    private EmployeeEntity managerEntity;
    private Employee managerModel;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);

        this.employeeEntity = Entities.employee();
        this.employeeEntity.setId(Entities.EMPLOYEE_ID);
        this.employeeModel = Models.employee();
        this.employeeModel.setId(Models.EMPLOYEE_ID);

        this.departmentEntity = Entities.department();
        this.departmentEntity.setId(Entities.DEPT_ID);
        this.departmentModel = Models.department();
        this.departmentModel.setId(Models.DEPT_ID);

        this.titleEntity = Entities.jobTitle();
        this.titleEntity.setId(Entities.JOB_TITLE_ID);
        this.titleModel = Models.jobTitle();
        this.titleModel.setId(Models.JOB_TITLE_ID);

        this.managerEntity = Entities.manager();
        this.managerEntity.setId(Entities.MANAGER_ID);
        this.managerModel = Models.manager();
        this.managerModel.setId(Models.MANAGER_ID);

        when(this.deptMapper.entityToModel(any(DepartmentEntity.class))).thenReturn(Models.department());
        when(this.deptMapper.modelToEntity(any(Department.class))).thenReturn(Entities.department());
        when(this.titleMapper.entityToModel(any(JobTitleEntity.class))).thenReturn(Models.jobTitle());
        when(this.titleMapper.modelToEntity(any(JobTitle.class))).thenReturn(Entities.jobTitle());
    }

    @Test
    public void testEmployeeEntityToModel(){
        this.employeeEntity.setDepartment(departmentEntity);
        this.employeeEntity.setJobTitle(titleEntity);
        this.employeeEntity.setManager(managerEntity);
        this.employeeModel = empMapper.entityToModel(this.employeeEntity);
        Assert.assertNotNull(this.employeeModel);
        Assert.assertNotNull(this.employeeModel.getIsManager());
        Assert.assertNotNull(this.employeeModel.getManager());
        Assert.assertNotNull(this.employeeModel.getDepartment());
        Assert.assertNotNull(this.employeeModel.getJobTitle());
        Assert.assertEquals(this.employeeEntity.getEmail(), this.employeeModel.getEmail());
        Assert.assertNotNull(this.employeeModel.getLastName());
        Assert.assertEquals(this.employeeEntity.getMiddleInitial(), this.employeeModel.getMiddleInitial());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEntityToModelNullEntity(){
        empMapper.entityToModel(null);
    }

    @Test
    public void testEmployeeModelToEntityManagerNonNull(){
        this.employeeModel.setDepartment(departmentModel);
        this.employeeModel.setJobTitle(titleModel);
        this.employeeModel.setManager(managerModel);
        this.employeeEntity = empMapper.modelToEntity(this.employeeModel);
        Assert.assertNotNull(this.employeeEntity);
        Assert.assertNotNull(this.employeeEntity.getIsManager());
        Assert.assertNotNull(this.employeeEntity.getManager());
        Assert.assertNotNull(this.employeeEntity.getDepartment());
        Assert.assertNotNull(this.employeeEntity.getJobTitle());
        Assert.assertNotNull(this.employeeEntity.getSkypeName());
        Assert.assertNotNull(this.employeeEntity.getFirstName());
        Assert.assertEquals(this.employeeEntity.getEmail(), this.employeeModel.getEmail());
        Assert.assertNotNull(this.employeeEntity.getLastName());
        Assert.assertEquals(this.employeeEntity.getMiddleInitial(), this.employeeModel.getMiddleInitial());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testModelToEntityNullModel() throws Exception{
        empMapper.modelToEntity(null);
    }

    @Test
    public void testSetAndGetDepartmentMapper(){
        empMapper.setDepartmentMapper(this.deptMapper);
        Assert.assertNotNull(empMapper.getDepartmentMapper());
    }

    @Test
    public void testSetAndGetJobTitleMapper(){
        empMapper.setJobTitleMapper(this.titleMapper);
        Assert.assertNotNull(empMapper.getJobTitleMapper());
    }

}
