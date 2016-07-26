package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<EmployeeEntity> findAll() {

		return this.repository.findAll();
	}

	public List<EmployeeEntity> findByIsActive(){

		return this.repository.findByIsActiveIsTrue();

	}
	public EmployeeEntity findEmployeeById(Integer id) {

		return this.repository.findOne(id);
	}

	public EmployeeEntity storeOrUpdate(EmployeeEntity employee) {

		return this.repository.save(employee);
	}

	public void removeEmployee(EmployeeEntity employee) {

		employee.setIsActive(false);
	}
}
