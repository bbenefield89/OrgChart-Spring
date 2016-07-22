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
		List<Employee> emps = empRepository.findByDepartment(department);
		List<Department> depts = deptRepository.findByParentDepartmentId(department.getId());
		if (depts.size() > 0) {
			log.error("Cannot delete department: " + department.getName() + ". Child department(s) exist");
			//throw new InvalidDeleteException();
		}
		if (emps.size() > 0) {
			log.error("Cannot delete department: " + department.getName() + ". Employees are in this department");
			//throw new InvalidDeleteException();
		}
		this.deptRepository.delete(department);
	}

	public void setRepository(DepartmentRepository repository) {
		this.deptRepository = repository;
	}

	public Department storeDepartment(Department department) {

		//Assert.hasLength(department.getName(), "No Department name provided");

		if (department.getParentDepartment() == null || department.getParentDepartment().getId() == -1) {
			department.setParentDepartment(null);
		}

		return this.deptRepository.save(department);
	}

	public Department updateDepartment(Department department) throws Exception {
		if (!parametersAreValid(department)) {
			throw new Exception("Invalid update parameters");
		}
		return this.storeDepartment(department);
	}

	private boolean parametersAreValid(Department department) {
		Department dept = deptRepository.findOne(department.getId());

		if (department.getId() != null && department.getId() != -1) {
			if(department.getParentDepartment() != null && department.getParentDepartment().getId() == -1){
				department.setParentDepartment(dept.getParentDepartment());
			}
			if (department.getParentDepartment() == null || department.getParentDepartment().getId() == -2) {
				department.setParentDepartment(null);
			}
			if (department.getName() == null || department.getName().isEmpty()) {
				log.info("No new name provided for department: " + dept.getName());
				department.setName(dept.getName());
				log.info("Reset name to " + dept.getName());
			}
			return true;
		}
		return false;
	}

}
