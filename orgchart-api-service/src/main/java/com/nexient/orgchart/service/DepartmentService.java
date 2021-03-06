package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;

import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentService {

    private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentRepository deptRepository;

    @Autowired
    private EmployeeRepository empRepository;

    @Autowired
    DepartmentMapper departmentMapper;

    public List<Department> findAllDepartments() {
        List<Department> deptsModel = new ArrayList<>();

        for (DepartmentEntity de : deptRepository.findAll()) {
            deptsModel.add(departmentMapper.entityToModel(de));
        }

        return deptsModel;
    }

    public List<Department> findAllActiveDepartments() {
        List<DepartmentEntity> deptsEntity = this.deptRepository.findByIsActiveIsTrue();
        List<Department> deptsModel = new ArrayList<>();

        for (DepartmentEntity de : deptsEntity) {
            deptsModel.add(departmentMapper.entityToModel(de));
        }

        return deptsModel;
    }

    public Department findDepartmentByID(Integer departmentId) {

        return departmentMapper.entityToModel(this.deptRepository.findOne(departmentId));
    }

    public Department storeOrUpdate(Department department) {
        Assert.notNull(department, "Department can not be null");
        DepartmentEntity departmentEntity = departmentMapper.modelToEntity(department);
        return departmentMapper.entityToModel(deptRepository.save(departmentEntity));
    }

    public boolean removeDepartment(Integer id) {
        Assert.notNull(deptRepository.findOne(id), "");

        DepartmentEntity entity = deptRepository.findOne(id);
        entity.setIsActive(false);

        this.setParentDepartmentToNullByParent(deptRepository.findByParentDepartment(entity));

        DepartmentEntity dept = deptRepository.save(entity);

        return !(dept.getIsActive());
    }

    public List<DepartmentEntity> setParentDepartmentToNullByParent(List<DepartmentEntity> depts) {
        List<DepartmentEntity> deptsWithNullParent = new ArrayList<>();

        for (DepartmentEntity child : depts) {
            child.setParentDepartment(null);
            deptsWithNullParent.add(child);
        }
        return deptsWithNullParent;
    }

    public List<Department> findAllInactiveDepartments() {
        List<Department> depts = new ArrayList<>();

        for (DepartmentEntity department : this.deptRepository.findByIsActiveIsFalse()) {
            depts.add(departmentMapper.entityToModel(department));
        }

        return depts;
    }

}
