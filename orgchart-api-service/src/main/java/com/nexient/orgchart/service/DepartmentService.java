package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
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

	public List<DepartmentEntity> findAllDepartments() {
		return this.deptRepository.findAll();
	}

	public List<DepartmentEntity> findAllActiveDepartments() {
		return this.deptRepository.findByIsActiveIsTrue();
	}

	public DepartmentEntity findDepartmentByID(Integer departmentId) {
		return this.deptRepository.findOne(departmentId);
	}

	public void removeDepartment(DepartmentEntity departmentEntity) {
		departmentEntity.setIsActive(false);
		storeOrUpdateDepartment(departmentEntity);
	}

	public DepartmentEntity storeOrUpdateDepartment(DepartmentEntity departmentEntity) {

		return this.deptRepository.save(departmentEntity);
	}

}
