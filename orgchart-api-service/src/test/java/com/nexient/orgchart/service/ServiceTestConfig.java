package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.DepartmentEntity;
import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.DepartmentRepository;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import com.nexient.orgchart.mapper.DepartmentMapper;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.mapper.JobTitleMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kskronek on 5/24/2016.
 */
@Configuration
@ComponentScan("com.nexient.orgchart.service")
public class ServiceTestConfig {


	private List<DepartmentEntity> listOfFoundDepts;
	private List<JobTitleEntity> listOfFoundTitles;
	private List<EmployeeEntity> listOfFoundEmployees;

	private DepartmentEntity mockDepartment;
	private JobTitleEntity mockTitle;
	private EmployeeEntity mockEmployee;

    private DepartmentMapper mockDepartmentMapper;
    private EmployeeMapper mockEmployeeMapper;
    private JobTitleMapper mockJobTitleMapper;

	@PostConstruct
	private void init() {
		listOfFoundDepts = new ArrayList<DepartmentEntity>();
		mockDepartment = Entities.department(Entities.DEPT_ID);
        mockDepartmentMapper = new DepartmentMapper();
		listOfFoundDepts.add(mockDepartment);

		listOfFoundTitles = new ArrayList<JobTitleEntity>();
        mockJobTitleMapper = new JobTitleMapper();
        mockTitle = Entities.jobTitle();
		mockTitle.setId(Entities.JOB_TITLE_ID);
		listOfFoundTitles.add(mockTitle);

		listOfFoundEmployees = new ArrayList<EmployeeEntity>();
		mockEmployee = Entities.employee(Entities.EMPLOYEE_ID);
        mockEmployeeMapper = new EmployeeMapper();
		listOfFoundEmployees.add(mockEmployee);
	}

	@Bean
	DepartmentEntity getDepartment() {
		return this.mockDepartment;
	}

	@Bean
    DepartmentMapper getMockDepartmentMapper(){ return this.mockDepartmentMapper; }

	@Bean
	DepartmentRepository getDepartmentRepository() {
		DepartmentRepository repo = mock(DepartmentRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundDepts);
		when(repo.findOne(Entities.DEPT_ID)).thenReturn(this.mockDepartment);
		when(repo.save(any(DepartmentEntity.class))).thenReturn(this.mockDepartment);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundDepts);
        when(repo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundDepts);
        when(repo.findByParentDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundDepts);
		return repo;
	}

	@Bean
	JobTitleEntity getJobTitle() {
		return this.mockTitle;
	}

	@Bean
    JobTitleMapper getMockJobTitleMapper(){return this.mockJobTitleMapper;};

	@Bean
	JobTitleRepository getJobTitleRepository() {
		JobTitleRepository repo = mock(JobTitleRepository.class);
		when(repo.findAll()).thenReturn(this.listOfFoundTitles);
		when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.mockTitle);
		when(repo.save(any(JobTitleEntity.class))).thenReturn(this.mockTitle);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
        return repo;
	}

	@Bean
	EmployeeEntity getEmployee() {
		return this.mockEmployee;
	}

	@Bean
    EmployeeMapper getMockEmployeeMapper(){ return this.mockEmployeeMapper; }

	@Bean
	EmployeeRepository getEmployeeRepository() {
		EmployeeRepository repo = mock(EmployeeRepository.class);
		when(repo.findAll()).thenReturn(listOfFoundEmployees);
		when(repo.findOne(Entities.EMPLOYEE_ID)).thenReturn(mockEmployee);
		when(repo.save(any(EmployeeEntity.class))).thenReturn(mockEmployee);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundEmployees);
        when(repo.findByDepartment(any(DepartmentEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(repo.findByManager(any(EmployeeEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(repo.findByJobTitle(any(JobTitleEntity.class))).thenReturn(this.listOfFoundEmployees);
        return repo;
	}

}
