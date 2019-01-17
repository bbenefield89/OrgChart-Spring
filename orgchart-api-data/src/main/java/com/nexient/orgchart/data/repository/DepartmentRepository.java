package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface DepartmentRepository extends BaseRepository<DepartmentEntity, Integer> {

    DepartmentEntity findByName(String name);

    List<DepartmentEntity> findByManager(EmployeeEntity employeeEntity);

    List<DepartmentEntity> findByParentDepartment(DepartmentEntity departmentEntity);


}
