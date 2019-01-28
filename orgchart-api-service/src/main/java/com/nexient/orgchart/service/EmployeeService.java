package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employeeModels = mapListOfEmployeeEntitiesToEmployeeModels(employeeEntities);
        return employeeModels;
    }

    public Employee findEmployeeById(Integer employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findOne(employeeId);
        Employee employee = employeeMapper.entityToModel(employeeEntity);
        return employee;
    }

    public List<Employee> findAllActiveEmployees() {
        List<EmployeeEntity> activeEmployeeEntities = employeeRepository.findByIsActiveIsTrue();
        List<Employee> employeeModels = mapListOfEmployeeEntitiesToEmployeeModels(activeEmployeeEntities);
        return employeeModels;
    }

    public List<Employee> findAllInactiveEmployees() {
        List<EmployeeEntity> activeEmployeeEntities = employeeRepository.findByIsActiveIsFalse();
        List<Employee> employeeModels = mapListOfEmployeeEntitiesToEmployeeModels(activeEmployeeEntities);
        return employeeModels;
    }

    public Employee storeOrUpdate(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employee);
        EmployeeEntity preExistingEmployeeEntity = employeeRepository.findByEmail(employee.getEmail());
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(employeeEntity);
        Employee employeeModel = employeeMapper.entityToModel(savedEmployeeEntity);
        return employeeModel;
    }

    private List<Employee> mapListOfEmployeeEntitiesToEmployeeModels(List<EmployeeEntity> employeeEntities) {
        List<Employee> activeEmployees = employeeEntities.stream()
                .map(employeeEntity -> employeeMapper.entityToModel(employeeEntity))
                .collect(Collectors.toList());
        return activeEmployees;
    }

    public boolean removeEmployee(Integer id) {
        EmployeeEntity employee = employeeRepository.findOne(id);
        Assert.notNull(employee, "[Assertion Error] - Employee not found in the database.");

        employee.setIsActive(false);
        employeeRepository.save(employee);

        return !employee.getIsActive();
    }

    public List<DepartmentEntity> setDepartmentManagerToNullByManager(List<DepartmentEntity> departmentEntities) {
        return departmentEntities.stream().map(departmentEntity -> {
            departmentEntity.setManager(null);
            return departmentEntity;
        })
            .collect(Collectors.toList());
    }

    public List<EmployeeEntity> setEmployeeManagerToNullByManager(List<EmployeeEntity> employeeEntities) {
        return employeeEntities.stream().map(employeeEntity -> {
            employeeEntity.setManager(null);
            return employeeEntity;
        })
            .collect(Collectors.toList());
    }
}
