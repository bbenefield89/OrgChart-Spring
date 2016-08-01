package com.nexient.orgchart.web;

import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.DepartmentService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by mrangel on 8/1/2016.
 */
public class DepartmentControllerTest {

    private Department department;
    private ArrayList<Department> findAllDepartmentList;

    @Mock
    private DepartmentService mockDepartmentService;

    @InjectMocks
    private DepartmentController controller = new DepartmentController();

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        this.department = Models.department();
        this.department.setId(Models.DEPT_ID);
        this.findAllDepartmentList = new ArrayList<>();
        this.findAllDepartmentList.add(department);

        when(this.mockDepartmentService.findAllActiveDepartments()).thenReturn(this.findAllDepartmentList);
        when(this.mockDepartmentService.findDepartmentByID(anyInt())).thenReturn(this.department);
        when(this.mockDepartmentService.findAllInactiveDepartments()).thenReturn(this.findAllDepartmentList);
        when(this.mockDepartmentService.storeOrUpdate(any(Department.class))).thenReturn(this.department);
        when(this.mockDepartmentService.removeDepartment(this.department.getId())).thenReturn(true);
    }

    @Test
    public void getAllActiveDepartmentTest(){
        List<Department> deptList = this.mockDepartmentService.findAllActiveDepartments();

        Assert.assertNotNull(deptList);
        Assert.assertFalse(deptList.isEmpty());
    }

    @Test
    public void readDepartmentTest(){
        Department dept = this.mockDepartmentService.findDepartmentByID(department.getId());

        Assert.assertNotNull(dept);
        Assert.assertEquals(this.department.getId(), dept.getId());
        Assert.assertEquals(this.department.getName(), dept.getName());
    }

    @Test
    public void findAllInactiveDepartmentTest(){
        List<Department> dept= this.mockDepartmentService.findAllInactiveDepartments();

        Assert.assertNotNull(dept);
        Assert.assertFalse(dept.isEmpty());
    }

    @Test
    public void createOrUpdateTest(){
        Department dept = this.mockDepartmentService.storeOrUpdate(department);

        Assert.assertNotNull(dept);
        Assert.assertEquals(department.getId(), dept.getId());
    }

    @Test
    public void deleteTest(){
        boolean hasDeleted = this.mockDepartmentService.removeDepartment(department.getId());

        Assert.assertNotNull(hasDeleted);
        Assert.assertTrue(hasDeleted);
    }
}
