package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> findAll() {

		return this.repository.findAll();
	}

	public List<Employee> findByIsActive(){

		return this.repository.findByIsActiveIsTrue();

	}
	public Employee findEmployeeById(Integer id) {

		return this.repository.findOne(id);
	}

	public Employee storeOrUpdate(Employee employee) {

		return this.repository.save(employee);
	}

	public void removeEmployee(Employee employee) {

		employee.setIsActive(false);
	}
}
