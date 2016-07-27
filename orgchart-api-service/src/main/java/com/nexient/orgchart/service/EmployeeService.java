package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EmployeeMapper employeeMapper;

	public List<Employee> findAll() {
		List<Employee> empModels = new ArrayList<>();

		for(EmployeeEntity emp : employeeRepository.findAll()){
			empModels.add(employeeMapper.entityToModel(emp));
		}

		return empModels;
	}

	public List<Employee> findByIsActive(){

		List<Employee> empModels = new ArrayList<>();

		for(EmployeeEntity emp: employeeRepository.findByIsActiveIsTrue()){
			empModels.add(employeeMapper.entityToModel(emp));
		}

		return empModels;

	}
	public Employee findEmployeeById(Integer id) {

		return employeeMapper.entityToModel(this.employeeRepository.findOne(id));
	}

	public Employee storeOrUpdate(Employee employee) {
		Assert.notNull(employee);

		return employeeMapper.entityToModel(this.employeeRepository.save(employeeMapper.modelToEntity(employee)));
	}

	public boolean removeEmployee(Employee employee) {
		Assert.notNull(employee);
		employee.setIsActive(false);
		EmployeeEntity empEnt = employeeMapper.modelToEntity(employee);

		for(EmployeeEntity emp : employeeRepository.findByManager(empEnt)){
			emp.setManager(null);
			employeeRepository.save(emp);
		}

		for(DepartmentEntity dept : departmentRepository.findByManager(employeeMapper.modelToEntity(employee))){
			dept.setManager(null);
			departmentRepository.save(dept);
		}

		storeOrUpdate(employee);

		return !(employee.getIsActive());


	}
}
