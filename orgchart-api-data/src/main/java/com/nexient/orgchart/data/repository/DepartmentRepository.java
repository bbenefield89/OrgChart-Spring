package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {

}
