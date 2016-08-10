package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by mrangel on 7/26/2016.
 */
@Component
public class DepartmentMapper implements EntityModelMapper<DepartmentEntity, Department> {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Department entityToModel(DepartmentEntity entity) {
//        Assert.notNull(entity);
        Department deptModel= new Department();
//        deptModel.setId(entity.getId());
//        deptModel.setName(entity.getName());
//        if(entity.getManager()!= null) {
//            deptModel.setManager(employeeMapper.entityToModel(entity.getManager()));
//        }
//        if(entity.getParentDepartment()!= null) {
//            deptModel.setParentDepartment(entityToModel(entity.getParentDepartment()));
//        }
//        deptModel.setIsActive(entity.getIsActive());
        return deptModel;
    }

    @Override
    public DepartmentEntity modelToEntity(Department model) {
//        Assert.notNull(model);
        DepartmentEntity deptModel= new DepartmentEntity();
//        deptModel.setId(model.getId());
//        deptModel.setName(model.getName());
//        if(model.getManager()!= null) {
//            deptModel.setManager(employeeMapper.modelToEntity(model.getManager()));
//        }
//        if(model.getParentDepartment()!= null) {
//            deptModel.setParentDepartment(modelToEntity(model.getParentDepartment()));
//        }
//        deptModel.setIsActive(model.getIsActive());
        return deptModel;
    }

    public EmployeeMapper getEmployeeMapper() {
        return employeeMapper;
    }

    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }
}
