package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.stereotype.Component;

/**
 * Created by mrangel on 7/26/2016.
 */

@Component
public class DepartmentMapper implements EntityModelMapper<DepartmentEntity, Department> {

    EmployeeMapper em = new EmployeeMapper();

    @Override
    public Department entityToModel(DepartmentEntity entity) {
        Department deptModel= new Department();
        deptModel.setId(entity.getId());
        deptModel.setName(entity.getName());
        if(entity.getManager()!= null) {
            deptModel.setManager(em.entityToModel(entity.getManager()));
        }
        if(entity.getParentDepartment()!= null) {
            deptModel.setParentDepartment(entityToModel(entity.getParentDepartment()));
        }
        deptModel.setIsActive(entity.getIsActive());
        return deptModel;
    }

    @Override
    public DepartmentEntity modelToEntity(Department model) {
        DepartmentEntity deptModel= new DepartmentEntity();
        deptModel.setId(model.getId());
        deptModel.setName(model.getName());
        if(model.getManager()!= null) {
            deptModel.setManager(em.modelToEntity(model.getManager()));
        }
        if(model.getParentDepartment()!= null) {
            deptModel.setParentDepartment(modelToEntity(model.getParentDepartment()));
        }
        deptModel.setIsActive(model.getIsActive());
        return deptModel;

    }

}
