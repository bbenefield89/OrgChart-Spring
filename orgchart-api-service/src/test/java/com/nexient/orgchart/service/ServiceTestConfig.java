package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Department;
import com.nexient.orgchart.data.entity.Employee;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitle;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static javafx.beans.binding.Bindings.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kskronek on 5/24/2016.
 */
@Configuration
@ComponentScan("com.nexient.orgchart.service")
public class ServiceTestConfig {


	private List<Department> listOfFoundDepts;
	private List<JobTitle> listOfFoundTitles;
	private List<Employee> listOfFoundEmployees;

	private Department mockDepartment;
	private JobTitle mockTitle;
	private Employee mockEmployee;

	@PostConstruct
	private void init() {
		listOfFoundDepts = new ArrayList<Department>();
		mockDepartment = Entities.department(Entities.DEPT_ID);
		listOfFoundDepts.add(mockDepartment);

		listOfFoundTitles = new ArrayList<JobTitle>();
		mockTitle = Entities.jobTitle();
		listOfFoundTitles.add(mockTitle);

		listOfFoundEmployees = new ArrayList<Employee>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
		listOfFoundEmployees.add(mockEmployee);
	}

	@Bean
	Department getDepartment() {
		return this.mockDepartment;
	}

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(this.mockDepartment)).thenReturn(this.mockDepartment);
		// TODO add verify that delete was called
		//verify(repo, times(1)).delete(this.mockDepartment);
		return repo;
	}

	@Bean
	JobTitle getJobTitle() {
		return this.mockTitle;
	}

	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockTitle);
		when(repo.save(this.mockTitle)).thenReturn(this.mockTitle);
		return repo;
	}

	@Bean
	Employee getEmployee() {
		return this.mockEmployee;
	}

	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(repo.save(this.mockEmployee)).thenReturn(mockEmployee);
		return repo;
	}

}
