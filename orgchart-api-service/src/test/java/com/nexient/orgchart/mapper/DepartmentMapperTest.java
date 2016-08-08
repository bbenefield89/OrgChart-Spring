package com.nexient.orgchart.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.Models;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.exceptions.ExceptionIncludingMockitoWarnings;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by mrangel, dhoover on 7/26/2016.
 */
public class DepartmentMapperTest {

    @InjectMocks
    private DepartmentMapper deptMapper;

    @Mock
    private EmployeeMapper empMapper;

    private DepartmentEntity departmentEntity;
    private Department departmentModel;
    private DepartmentEntity parentDeptEntity;
    private Department parentDeptModel;
    private EmployeeEntity managerEntity;
    private Employee managerModel;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);

        this.departmentEntity = Entities.department();
        this.departmentEntity.setId(Entities.DEPT_ID);
        this.departmentModel = Models.department();
        this.departmentModel.setId(Models.DEPT_ID);

        this.parentDeptEntity = Entities.department();
        this.parentDeptEntity.setId(Entities.DEPT_ID);
        this.parentDeptModel = Models.department();
        this.parentDeptModel.setId(Models.DEPT_ID);

        this.managerEntity = Entities.manager();
        this.managerEntity.setId(Entities.MANAGER_ID);
        this.managerModel = Models.manager();
        this.managerModel.setId(Models.MANAGER_ID);

        this.deptMapper.setEmployeeMapper(empMapper);

        when(this.empMapper.entityToModel(any(EmployeeEntity.class))).thenReturn(Models.employee());
        when(this.empMapper.modelToEntity(any(Employee.class))).thenReturn(Entities.employee());

    }

    @Test
    public void testDepartmentEntityToModel(){
        this.departmentEntity.setManager(managerEntity);
        this.departmentEntity.setParentDepartment(parentDeptEntity);

        this.departmentModel = this.deptMapper.entityToModel(departmentEntity);
        Assert.assertNotNull(departmentModel);
        Assert.assertNotNull(this.departmentModel.getManager());
        Assert.assertNotNull(this.departmentModel.getParentDepartment());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testEntityToModelNull(){
        this.deptMapper.entityToModel(null);
    }

    @Test
    public void testDepartmentModelToEntity(){
        this.departmentModel.setManager(managerModel);
        this.departmentModel.setParentDepartment(parentDeptModel);
        this.departmentEntity = this.deptMapper.modelToEntity(departmentModel);
        Assert.assertNotNull(departmentEntity);
        Assert.assertNotNull(this.departmentEntity.getName());
        Assert.assertNotNull(this.departmentEntity.getManager());
        Assert.assertNotNull(this.departmentEntity.getParentDepartment());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testModelToEntityNull(){
        this.deptMapper.modelToEntity(null);
    }

    @Test
    public void testGetAndSetEmployeeMapper(){
        this.deptMapper.setEmployeeMapper(empMapper);
        Assert.assertNotNull(deptMapper.getEmployeeMapper());
    }

}