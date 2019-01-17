package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import com.nexient.orgchart.mapper.JobTitleMapper;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JobTitleService {

    @Autowired
    private JobTitleRepository jobTitleRepository;

    @Autowired
    private JobTitleMapper jobTitleMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<JobTitle> findAll() {
        List<JobTitleEntity> jobTitleEntities = jobTitleRepository.findAll();
        List<JobTitle> jobTitles = jobTitleEntities.stream()
                .map(jobTitleEntity -> jobTitleMapper.entityToModel(jobTitleEntity))
                .collect(Collectors.toList());

        return jobTitles;
    }

    public List<JobTitle> findAllInactiveJobTitles() {
        List<JobTitleEntity> inactiveJobTitleEntities = jobTitleRepository.findByIsActiveIsFalse();
        List<JobTitle> inactiveJobTitles = inactiveJobTitleEntities.stream()
                .map(inactiveJobTitleEntity -> jobTitleMapper.entityToModel(inactiveJobTitleEntity))
                .collect(Collectors.toList());

        return inactiveJobTitles;
    }

    public List<JobTitle> findAllActiveJobTitles() {
        List<JobTitleEntity> activeJobTitleEntities = jobTitleRepository.findByIsActiveIsTrue();
        List<JobTitle> activeJobTitles = activeJobTitleEntities.stream()
                .map(inactiveJobTitleEntity -> jobTitleMapper.entityToModel(inactiveJobTitleEntity))
                .collect(Collectors.toList());

        return activeJobTitles;
    }

    public JobTitle findJobTitleByID(Integer jobTitleId) {
        JobTitleEntity jobTitleEntity = jobTitleRepository.findOne(jobTitleId);
        JobTitle jobTitle = jobTitleMapper.entityToModel(jobTitleEntity);

        return jobTitle;
    }

    public JobTitle storeOrUpdate(JobTitle jobTitle) {
        JobTitleEntity jobTitleEntity = jobTitleMapper.modelToEntity(jobTitle);
        jobTitleRepository.save(jobTitleEntity);
        return jobTitle;
    }

    public boolean removeJobTitle(Integer jobTitleId) {
        JobTitleEntity jobTitleEntity = jobTitleRepository.findOne(jobTitleId);

        Assert.notNull(
                jobTitleEntity,
                String.format("[Assertion Error] - Job title with ID of \"%c\" does not exist.", jobTitleId)
        );

        jobTitleEntity.setIsActive(false);
        return true;
    }

    public List<EmployeeEntity> setEmployeesJobTitleWithJobTitleToNull(List<EmployeeEntity> employeeEntities) {
        List<EmployeeEntity> employeeEntitiesWithJobTitle = employeeEntities.stream()
                .filter(employeeEntity -> employeeEntity.getJobTitle() != null)
                .collect(Collectors.toList());

        List<EmployeeEntity> employeeEntitiesWithoutJobTitle = employeeEntitiesWithJobTitle.stream()
                .map(employeeEntity -> {
                    employeeEntity.setJobTitle(null);
                    return employeeEntity;
                })
                .collect(Collectors.toList());

        return employeeEntitiesWithoutJobTitle;
    }
}
