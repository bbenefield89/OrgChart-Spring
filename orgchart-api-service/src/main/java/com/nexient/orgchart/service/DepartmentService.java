package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentService {
	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private EmployeeRepository empRepository;

	public List<Department> findAllDepartments() {
		return this.deptRepository.findAll();
	}

	public List<Department> findAllActiveDepartments() {
		return this.deptRepository.findByIsActiveIsTrue();
	}

	public Department findDepartmentByID(Integer departmentId) {
		return this.deptRepository.findOne(departmentId);
	}

	public void removeDepartment(Department department) {
		department.setIsActive(false);
		storeOrUpdateDepartment(department);
	}

	public Department storeOrUpdateDepartment(Department department) {

		return this.deptRepository.save(department);
	}

}
