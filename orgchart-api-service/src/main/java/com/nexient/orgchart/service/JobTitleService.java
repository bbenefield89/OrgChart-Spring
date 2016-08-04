package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import com.nexient.orgchart.mapper.JobTitleMapper;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Service("jobTitleService")
public class JobTitleService {

    @Autowired
    JobTitleRepository jobRepository;

    @Autowired
    EmployeeRepository empRepository;

    @Autowired
    JobTitleMapper mapper;

    public List<JobTitle> findAll() {
        List<JobTitle> jtModel = new ArrayList<>();

        for (JobTitleEntity title : jobRepository.findAll()) {
            jtModel.add(mapper.entityToModel(title));
        }

        return jtModel;
    }

    public List<JobTitle> findAllActiveJobTitles() {
        List<JobTitle> jtModel = new ArrayList<>();

        for (JobTitleEntity title : jobRepository.findByIsActiveIsTrue()) {
            jtModel.add(mapper.entityToModel(title));
        }

        return jtModel;
    }

    public List<JobTitle> findAllInactiveJobTitles() {
        List<JobTitle> jobTitleModel = new ArrayList<>();

        for (JobTitleEntity title : this.jobRepository.findByIsActiveIsFalse()) {
            jobTitleModel.add(mapper.entityToModel(title));
        }
        return jobTitleModel;
    }

    public JobTitle findJobTitleByID(Integer id) {
        return mapper.entityToModel(this.jobRepository.findOne(id));
    }

    public JobTitle storeOrUpdate(JobTitle jobTitle) {
        JobTitleEntity titleEntity = mapper.modelToEntity(jobTitle);
        return mapper.entityToModel(this.jobRepository.save(titleEntity));
    }

    public boolean removeJobTitle(Integer id) {
        Assert.notNull(jobRepository.findOne(id));

        JobTitleEntity titleEntity = jobRepository.findOne(id);
        titleEntity.setIsActive(false);

        for (EmployeeEntity emp : empRepository.findByJobTitle(titleEntity)) {
            emp.setJobTitle(null);
        }

        JobTitle title = storeOrUpdate(mapper.entityToModel(titleEntity));

        return !(title.getIsActive());
    }

}
