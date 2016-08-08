package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface EmployeeRepository extends BaseRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findByDepartment(DepartmentEntity departmentEntity);

    EmployeeEntity findByEmail(String email);

    List<EmployeeEntity> findByManager(EmployeeEntity manager);

    List<EmployeeEntity> findByJobTitle(JobTitleEntity jobTitle);

    List<EmployeeEntity> findByIsManager(boolean isManager);

}
