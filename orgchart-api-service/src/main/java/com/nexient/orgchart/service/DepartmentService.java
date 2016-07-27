package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;

import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
public class DepartmentService {


	private static final Logger log = LoggerFactory.getLogger(DepartmentService.class);

	@Autowired
	private DepartmentRepository deptRepository;

	@Autowired
	private EmployeeRepository empRepository;

	@Autowired
	DepartmentMapper departmentMapper;

	public List<Department> findAllDepartments() {
		List<Department> deptsModel = new ArrayList<>();

		for(DepartmentEntity de: deptRepository.findAll()){
			deptsModel.add(departmentMapper.entityToModel(de));
		}

		return deptsModel;
	}

	public List<Department> findAllActiveDepartments() {
		List<DepartmentEntity> deptsEntity = this.deptRepository.findByIsActiveIsTrue();
		List<Department> deptsModel = new ArrayList<>();

		for(DepartmentEntity de: deptsEntity){
			deptsModel.add(departmentMapper.entityToModel(de));
		}

		return deptsModel;
	}

	public Department findDepartmentByID(Integer departmentId) {

		return departmentMapper.entityToModel(this.deptRepository.findOne(departmentId));
	}

	public boolean removeDepartment(Department department) {
		Assert.notNull(department);
		department.setIsActive(false);

		for(DepartmentEntity child: deptRepository.findByParentDepartmentId(department.getId())){
			child.setParentDepartment(null);
			deptRepository.save(child);
		}

		storeOrUpdateDepartment(department);

		return !(department.getIsActive());
	}

	public Department storeOrUpdateDepartment(Department department) {
		Assert.notNull(department);

		DepartmentEntity departmentEntity = departmentMapper.modelToEntity(department);

		return departmentMapper.entityToModel(this.deptRepository.save(departmentEntity));
	}

}
