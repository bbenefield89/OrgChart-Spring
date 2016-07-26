package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository jobRepository;

	public List<JobTitleEntity> findAll() {
		return this.jobRepository.findAll();
	}

	public List<JobTitleEntity> findAllActiveJobTitles() {
		return this.jobRepository.findByIsActiveIsTrue();
	}

	public JobTitleEntity findJobTitleByID(Integer id) {
		return this.jobRepository.findOne(id);
	}

	public Integer saveOrUpdate(JobTitleEntity jobTitle) {
		return this.jobRepository.save(jobTitle).getId();
	}

	public Integer removeJobTitle(JobTitleEntity jobTitle) {
		jobTitle.setIsActive(false);
		return saveOrUpdate(jobTitle);
	}

}
