package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class EmployeeMapper implements EntityModelMapper<EmployeeEntity, Employee> {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private JobTitleMapper jobTitleMapper;

    public void setDepartmentMapper(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    public void setJobTitleMapper(JobTitleMapper jobTitleMapper) {
        this.jobTitleMapper = jobTitleMapper;
    }

    /**
     * @Todo: Needs to be refactored.
     */
//    @Override
//    public Employee entityToModel(EmployeeEntity employeeEntity) {
//        Assert.notNull(employeeEntity, "Employee entity cannot be null.");
//
//        Employee employee = new Employee();
//
//        if (employeeEntity.getDepartment() != null) {
//            Department department = departmentMapper.entityToModel(employeeEntity.getDepartment());
//            if (department != null) {
//                employee.setDepartment(department);
//            }
//        }
//
//        if (employeeEntity.getManager() != null) {
//            EmployeeEntity employeeEntity_Manager = employeeEntity.getManager();
//            if (employeeEntity_Manager != null) {
//                Employee employee_Manager = entityToModel(employeeEntity_Manager);
//                employee.setManager(employee_Manager);
//            }
//        }
//
//        if (employeeEntity.getJobTitle() != null) {
//            JobTitle jobTitle = jobTitleMapper.entityToModel(employeeEntity.getJobTitle());
//            if (jobTitle != null) {
//                employee.setJobTitle(jobTitle);
//            }
//        }
//
//        employee.setId(employeeEntity.getId());
//        employee.setEmail(employeeEntity.getEmail());
//        employee.setFirstName(employeeEntity.getFirstName());
//        employee.setMiddleInitial(employeeEntity.getMiddleInitial());
//        employee.setLastName(employeeEntity.getLastName());
//        employee.setIsManager(employeeEntity.getIsManager());
//        employee.setSkypeName(employeeEntity.getSkypeName());
//
//        return employee;
//    }

    @Override
    public Employee entityToModel(EmployeeEntity employeeEntity) {
        Assert.notNull(employeeEntity, "Employee entity cannot be null.");

        Employee employee = new Employee();

        if (employeeEntity.getDepartment() != null) {
            Department department = departmentMapper.entityToModel(employeeEntity.getDepartment());
            if (department != null) {
                employee.setDepartment(department);
            }
        }

        if (employeeEntity.getManager() != null) {
            EmployeeEntity employeeEntity_Manager = employeeEntity.getManager();
            if (employeeEntity_Manager != null) {
                Employee employee_Manager = entityToModel(employeeEntity_Manager);
                employee.setManager(employee_Manager);
            }
        }

        if (employeeEntity.getJobTitle() != null) {
            JobTitle jobTitle = jobTitleMapper.entityToModel(employeeEntity.getJobTitle());
            if (jobTitle != null) {
                employee.setJobTitle(jobTitle);
            }
        }

        employee.setId(employeeEntity.getId());
        employee.setEmail(employeeEntity.getEmail());
        employee.setFirstName(employeeEntity.getFirstName());
        employee.setMiddleInitial(employeeEntity.getMiddleInitial());
        employee.setLastName(employeeEntity.getLastName());
        employee.setIsManager(employeeEntity.getIsManager());
        employee.setSkypeName(employeeEntity.getSkypeName());
        employee.setIsActive(employeeEntity.getIsActive());

        return employee;
    }

    @Override
    public EmployeeEntity modelToEntity(Employee employee) {
        Assert.notNull(employee, "Employee model cannot be null.");

        EmployeeEntity employeeEntity = new EmployeeEntity();
        DepartmentEntity departmentEntity = departmentMapper.modelToEntity(employee.getDepartment());
        JobTitleEntity jobTitleEntity = jobTitleMapper.modelToEntity(employee.getJobTitle());
        Employee employeeManager = employee.getManager();

        if (employeeManager != null) {
            EmployeeEntity employeeManagerEntity = modelToEntity(employeeManager);
            employeeEntity.setManager(employeeManagerEntity);
        }

        employeeEntity.setId(employee.getId());
        employeeEntity.setEmail(employee.getEmail());
        employeeEntity.setFirstName(employee.getFirstName());
        employeeEntity.setMiddleInitial(employee.getMiddleInitial());
        employeeEntity.setLastName(employee.getLastName());
        employeeEntity.setDepartment(departmentEntity);
        employeeEntity.setJobTitle(jobTitleEntity);
        employeeEntity.setIsManager(employee.getIsManager());
        employeeEntity.setSkypeName(employee.getSkypeName());
        employeeEntity.setIsActive(employee.getIsActive());

        return employeeEntity;
    }

    public DepartmentMapper getDepartmentMapper() {
        return departmentMapper;
    }

    public JobTitleMapper getJobTitleMapper() {
        return jobTitleMapper;
    }
}
