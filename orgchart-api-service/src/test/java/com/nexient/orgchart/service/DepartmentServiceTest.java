package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.model.Department;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.BeanUtils;
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

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;// = new DepartmentMapper();

    @Spy
    private EmployeeMapper employeeMapper;// = new EmployeeMapper();

    private Department department;
    private DepartmentEntity departmentEntity;
    private List<DepartmentEntity> listOfFoundDepts;

    @BeforeTest
    public void before() {
        MockitoAnnotations.initMocks(this);

        DepartmentEntity parentDepartment = Entities.department();

        departmentEntity = Entities.department();
        departmentEntity.setId(Entities.DEPT_ID);
        departmentEntity.setParentDepartment(parentDepartment);

        listOfFoundDepts = new ArrayList<>();
        listOfFoundDepts.add(departmentEntity);

        department = new Department();
        BeanUtils.copyProperties(departmentEntity, department);

        departmentMapper.setEmployeeMapper(employeeMapper);

        when(departmentRepository.findAll()).thenReturn(this.listOfFoundDepts);
        when(departmentRepository.findOne(Entities.DEPT_ID)).thenReturn(this.departmentEntity);
        when(departmentRepository.save(any(DepartmentEntity.class))).thenReturn(this.departmentEntity);
        when(departmentRepository.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
        when(departmentRepository.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);
        when(departmentRepository.findByParentDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundDepts);
        when(departmentRepository.findByIsActiveIsFalse()).thenReturn(this.listOfFoundDepts);

        when(departmentMapper.modelToEntity(department)).thenReturn(this.departmentEntity);
        when(departmentMapper.entityToModel(departmentEntity)).thenReturn(department);
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
        when(departmentRepository.save(departmentEntity)).thenReturn(departmentEntity);

        Department dept = this.departmentService.storeOrUpdate(department);
        Assert.assertNotNull(dept);
        Assert.assertEquals(Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
    }

    @Test
    public void removeDepartment() {
        this.departmentEntity.setIsActive(true);
        this.departmentEntity.setParentDepartment(Entities.department());
        this.departmentEntity.setManager(Entities.manager());

        Assert.assertTrue(departmentService.removeDepartment(this.departmentEntity.getId()));
    }

    @Test
    public void removeDepartment_False() {
        this.departmentEntity.setIsActive(true);

        doAnswer(new Answer<DepartmentEntity>() {
            @Override
            public DepartmentEntity answer(InvocationOnMock invocation) {
                Object[] args = invocation.getArguments();
                DepartmentEntity depy = (DepartmentEntity) args[0];
                depy.setIsActive(true);
                return depy;
            }
        }).when(this.departmentRepository).save(any(DepartmentEntity.class));

        Assert.assertFalse(this.departmentService.removeDepartment(departmentEntity.getId()));
    }

    @Test
    public void updateDepartment() {
        Integer departmentId = departmentEntity.getId();

        when(departmentRepository.findOne(departmentId)).thenReturn(departmentEntity);
        when(departmentMapper.entityToModel(departmentEntity)).thenReturn(department);

        departmentService.storeOrUpdate(departmentMapper.entityToModel(this.departmentEntity));
        Department departmentByID = departmentService.findDepartmentByID(departmentId);
        Assert.assertEquals(this.departmentEntity.getName(), departmentByID.getName());
    }

    @Test
    public void findAllActiveDepartments() {
        List<Department> depts = departmentService.findAllActiveDepartments();
        Assert.assertNotNull(depts);
        Assert.assertTrue(depts.size() > 0);
    }

    @Test
    public void findAllInactiveDepartments() {
        List<Department> depts = departmentService.findAllInactiveDepartments();
        Assert.assertNotNull(depts);
        Assert.assertTrue(depts.size() > 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeDepartment_Null() {
        this.departmentService.storeOrUpdate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeDepartment_Null() {
        this.departmentService.removeDepartment(null);
    }

    @Test
    public void testSetParentDepartmentToNullByParent() {
        List<DepartmentEntity> depts = departmentService.setParentDepartmentToNullByParent(listOfFoundDepts);
        Assert.assertNotNull(depts);
        Assert.assertTrue(depts.size() > 0);

        for (DepartmentEntity d : depts) {
            Assert.assertNull(d.getParentDepartment());
        }
    }

}


//package com.nexient.orgchart.service;
//
//import com.nexient.orgchart.data.entity.DepartmentEntity;
//import com.nexient.orgchart.data.entity.EmployeeEntity;
//import com.nexient.orgchart.data.entity.Entities;
//import com.nexient.orgchart.data.repository.DepartmentRepository;
//import com.nexient.orgchart.mapper.DepartmentMapper;
//import com.nexient.orgchart.mapper.EmployeeMapper;
//import com.nexient.orgchart.model.Department;
//import org.mockito.*;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//import org.testng.Assert;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.doAnswer;
//import static org.mockito.Mockito.when;
//import static org.testng.Assert.assertEquals;
//import static org.testng.Assert.assertNotNull;
//
//public class DepartmentServiceTest {
//
//    @InjectMocks
//    private DepartmentService departmentService;
//
//    @Spy
//    private DepartmentMapper departmentMapper = new DepartmentMapper();
//
//    @Spy
//    private EmployeeMapper employeeMapper = new EmployeeMapper();
//
//    @Mock
//    private DepartmentRepository departmentRepository;
//
//    private DepartmentEntity departmentEntity;
//    private DepartmentEntity parentDepartment;
//    private List<DepartmentEntity> listOfFoundDepts;
//
//    @BeforeTest
//    public void before() {
//        MockitoAnnotations.initMocks(this);
//
//        parentDepartment = Entities.departmentEntity();
//
//        departmentEntity = Entities.departmentEntity();
//        departmentEntity.setId(Entities.DEPT_ID);
//        departmentEntity.setParentDepartment(parentDepartment);
//
//        listOfFoundDepts = new ArrayList<>();
//        listOfFoundDepts.add(departmentEntity);
//
//        departmentMapper.setEmployeeMapper(employeeMapper);
//
//        when(departmentRepository.findAll()).thenReturn(this.listOfFoundDepts);
//        when(departmentRepository.findOne(Entities.DEPT_ID)).thenReturn(this.departmentEntity);
//        when(departmentRepository.save(any(DepartmentEntity.class))).thenReturn(this.departmentEntity);
//        when(departmentRepository.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
//        when(departmentRepository.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);
//        when(departmentRepository.findByParentDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundDepts);
//        when(departmentRepository.findByIsActiveIsFalse()).thenReturn(this.listOfFoundDepts);
//    }
//
//    @Test
//    public void findAllDepartments() {
//        List<Department> depts = this.departmentService.findAllDepartments();
//        assertNotNull(depts);
//        Assert.assertTrue(depts.size() > 0);
//    }
//
//    @Test
//    public void findDepartment() {
//        Department dept = this.departmentService.findDepartmentByID(Entities.DEPT_ID);
//        assertNotNull(dept);
//        assertEquals(Entities.DEPT_ID, dept.getId());
//    }
//
//    @Test
//    public void storeDepartment() {
//        Department dept = this.departmentService.storeOrUpdate(departmentMapper.entityToModel(this.departmentEntity));
//        Assert.assertNotNull(dept);
//        Assert.assertEquals(Entities.DEPT_ID, dept.getId(), "Expected " + Entities.DEPT_ID + " but got " + dept.getId());
//    }
//
//    @Test
//    public void removeDepartment() {
//        this.departmentEntity.setIsActive(true);
//        this.departmentEntity.setParentDepartment(Entities.departmentEntity());
//        this.departmentEntity.setManager(Entities.manager());
//
//        Assert.assertTrue(departmentService.removeDepartment(this.departmentEntity.getId()));
//    }
//
//    @Test
//    public void removeDepartment_False() {
//        this.departmentEntity.setIsActive(true);
//
//        doAnswer(new Answer<DepartmentEntity>() {
//            @Override
//            public DepartmentEntity answer(InvocationOnMock invocation) throws Throwable {
//                Object[] args = invocation.getArguments();
//                DepartmentEntity depy = (DepartmentEntity) args[0];
//                depy.setIsActive(true);
//                return depy;
//            }
//        }).when(this.departmentRepository).save(any(DepartmentEntity.class));
//
//        Assert.assertFalse(this.departmentService.removeDepartment(departmentEntity.getId()));
//    }
//
//    @Test
//    public void updateDepartment() {
//        departmentEntity.setName("Other Dept Name");
//
//        departmentService.storeOrUpdate(departmentMapper.entityToModel(this.departmentEntity));
//        Assert.assertEquals(this.departmentEntity.getName(), departmentService.findDepartmentByID(departmentEntity.getId()).getName());
//    }
//
//    @Test
//    public void findAllActiveDepartments() {
//        List<Department> depts = departmentService.findAllActiveDepartments();
//        Assert.assertNotNull(depts);
//        Assert.assertTrue(depts.size() > 0);
//    }
//
//    @Test
//    public void findAllInactiveDepartments() {
//        List<Department> depts = departmentService.findAllInactiveDepartments();
//        Assert.assertNotNull(depts);
//        Assert.assertTrue(depts.size() > 0);
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void storeDepartment_Null() {
//        this.departmentService.storeOrUpdate(null);
//
//    }
//
//    @Test(expectedExceptions = IllegalArgumentException.class)
//    public void removeDepartment_Null() {
//        this.departmentService.removeDepartment(null);
//    }
//
//    @Test
//    public void testSetParentDepartmentToNullByParent(){
//        List<DepartmentEntity> depts = departmentService.setParentDepartmentToNullByParent(listOfFoundDepts);
//        Assert.assertNotNull(depts);
//        Assert.assertTrue(depts.size() > 0);
//
//        for(DepartmentEntity d: depts){
//            Assert.assertNull(d.getParentDepartment());
//        }
//    }
//
//}
