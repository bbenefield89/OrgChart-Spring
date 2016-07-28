package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by mrangel on 7/26/2016.
 */
@Component
public class EmployeeMapper implements EntityModelMapper<EmployeeEntity, Employee> {


    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private JobTitleMapper jobTitleMapper;

    @Override
    public Employee entityToModel(EmployeeEntity entity) {
        Assert.notNull(entity);
        Employee empModel = new Employee();
        empModel.setId(entity.getId());
        empModel.setIsManager(entity.getIsManager());
        if( entity.getManager()!=null) {
            empModel.setManager(entityToModel(entity.getManager()));
        }
        if(entity.getDepartment()!=null){
            empModel.setDepartment(departmentMapper.entityToModel(entity.getDepartment()));
        }
        if(entity.getJobTitle()!=null){
            empModel.setJobTitle(jobTitleMapper.entityToModel(entity.getJobTitle()));

        }
        empModel.setSkypeName(entity.getSkypeName());
        empModel.setEmail(entity.getEmail());

        empModel.setFirstName(entity.getFirstName());
        empModel.setLastName(entity.getLastName());
        empModel.setMiddleInitial(entity.getMiddleInitial());
        empModel.setIsActive(entity.getIsActive());
        return empModel;
    }

    @Override
    public EmployeeEntity modelToEntity(Employee model) {
        Assert.notNull(model);
        EmployeeEntity empEntity = new EmployeeEntity();
        empEntity.setId(model.getId());
        empEntity.setIsManager(model.getIsManager());
        if( model.getManager()!=null) {
            empEntity.setManager(modelToEntity(model.getManager()));
        }
        if(model.getDepartment()!=null){
            empEntity.setDepartment(departmentMapper.modelToEntity(model.getDepartment()));
        }
        if(model.getJobTitle()!=null){
            empEntity.setJobTitle(jobTitleMapper.modelToEntity(model.getJobTitle()));
        }
        empEntity.setSkypeName(model.getSkypeName());
        empEntity.setEmail(model.getEmail());
        empEntity.setFirstName(model.getFirstName());
        empEntity.setLastName(model.getLastName());
        empEntity.setMiddleInitial(model.getMiddleInitial());
        empEntity.setIsActive(model.getIsActive());

        return empEntity;
    }

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public JobTitleMapper getJobTitleMapper() {
        return jobTitleMapper;
    }

    public void setJobTitleMapper(JobTitleMapper jobTitleMapper) {
        this.jobTitleMapper = jobTitleMapper;
    }

}
