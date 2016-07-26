package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.model.Employee;
import org.springframework.stereotype.Component;

/**
 * Created by mrangel on 7/26/2016.
 */
@Component
public class EmployeeMapper implements EntityModelMapper<EmployeeEntity, Employee> {

    DepartmentMapper dm = new DepartmentMapper();
    JobTitleMapper jtm = new JobTitleMapper();

    @Override
    public Employee entityToModel(EmployeeEntity entity) {
        Employee empModel = new Employee();
        empModel.setId(entity.getId());
        empModel.setIsManager(entity.getIsManager());
        empModel.setManager(entityToModel(entity.getManager()));
        empModel.setSkypeName(entity.getSkypeName());
        empModel.setEmail(entity.getEmail());
        empModel.setDepartment(dm.entityToModel(entity.getDepartment()));
        empModel.setFirstName(entity.getFirstName());
        empModel.setLastName(entity.getLastName());
        empModel.setMiddleInitial(entity.getMiddleInitial());
        empModel.setIsActive(entity.getIsActive());
        empModel.setJobTitle(jtm.entityToModel(entity.getJobTitle()));
        return empModel;
    }

    @Override
    public EmployeeEntity modelToEntity(Employee model) {
        EmployeeEntity empEntity = new EmployeeEntity();
        empEntity.setId(model.getId());
        empEntity.setIsManager(model.getIsManager());
        empEntity.setManager(modelToEntity(model.getManager()));
        empEntity.setSkypeName(model.getSkypeName());
        empEntity.setEmail(model.getEmail());
        empEntity.setDepartment(dm.modelToEntity(model.getDepartment()));
        empEntity.setFirstName(model.getFirstName());
        empEntity.setLastName(model.getLastName());
        empEntity.setMiddleInitial(model.getMiddleInitial());
        empEntity.setIsActive(model.getIsActive());
        empEntity.setJobTitle(jtm.modelToEntity(model.getJobTitle()));
        return empEntity;
    }

}
