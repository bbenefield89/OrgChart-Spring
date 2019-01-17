package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;

import java.util.List;

public interface EmployeeRepository extends BaseRepository<EmployeeEntity, Integer>{

    List<EmployeeEntity> findByDepartment(DepartmentEntity departmentEntity);
    List<EmployeeEntity> findByManager(EmployeeEntity employeeEntity);
    List<EmployeeEntity> findByJobTitle(JobTitleEntity jobTitleEntity);

    EmployeeEntity findByEmail(String email);

    List<EmployeeEntity> findByIsManager(boolean b);
}
