package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {

    public Department findByName(String name);

    List<Department> findByParentDepartmentId(Integer id);
}
