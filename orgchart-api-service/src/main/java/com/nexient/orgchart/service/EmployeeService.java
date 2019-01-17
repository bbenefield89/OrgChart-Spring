package com.nexient.orgchart.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import com.nexient.orgchart.mapper.EmployeeMapper;
import org.springframework.util.Assert;

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper employeeMapper;

    private List<Employee> employees = new ArrayList<>();

    public List<Employee> findAll() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        return convertEmployeeEntitiesToEmployeeModels(employeeEntities);
    }

    public Employee findEmployeeById(Integer employeeId) {
        EmployeeEntity employeeEntity = employeeRepository.findOne(employeeId);
        Employee employee = employeeMapper.entityToModel(employeeEntity);
        return employee;
    }

    public List<Employee> findAllActiveEmployees() {
        List<EmployeeEntity> activeEmployeeEntities = employeeRepository.findByIsActiveIsTrue();
        return convertEmployeeEntitiesToEmployeeModels(activeEmployeeEntities);
    }

    public List<Employee> findAllInactiveEmployees() {
        List<EmployeeEntity> activeEmployeeEntities = employeeRepository.findByIsActiveIsFalse();
        return convertEmployeeEntitiesToEmployeeModels(activeEmployeeEntities);
    }

    public Employee storeOrUpdate(Employee employee) {
        EmployeeEntity employeeEntity = employeeMapper.modelToEntity(employee);
        employeeRepository.save(employeeEntity);
        return employee;
    }

    private List<Employee> convertEmployeeEntitiesToEmployeeModels(List<EmployeeEntity> employeeEntities) {
        Consumer<EmployeeEntity> employeeEntitiesForEachCallBack = employeeEntity -> {
            Employee employee = employeeMapper.entityToModel(employeeEntity);
            employees.add(employee);
        };
        employeeEntities.forEach(employeeEntitiesForEachCallBack);
        return employees;
    }

    public boolean removeEmployee(Integer id) {
        EmployeeEntity employee = employeeRepository.findOne(id);

        Assert.notNull(employee, "[Assertion Error] - Employee not found in the database.");

        employee.setDepartment(null);
        employee.setManager(null);
        employee.setJobTitle(null);
        employee.setManagedDepartments(null);
        employee.setManagedEmployees(null);
        employee.setIsActive(false);
        employee.setIsManager(false);

        return true;
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
