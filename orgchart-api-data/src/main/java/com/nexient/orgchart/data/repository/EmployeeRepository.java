package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {

    List<Employee> findByDepartment(Department department);

    Employee findByEmail(String email);

    List<Employee> findByManager(Employee manager);

    List<Employee> findByJobTitle(JobTitle jobTitle);

    List<Employee> findByIsManager(boolean isManager);

}
