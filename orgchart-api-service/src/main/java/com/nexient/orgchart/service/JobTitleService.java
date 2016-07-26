package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.JobTitle;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("jobTitleService")
public class JobTitleService {

	@Autowired
	JobTitleRepository jobRepository;

	public List<JobTitle> findAll() {
		return this.jobRepository.findAll();
	}

	public List<JobTitle> findAllActiveJobTitles() {
		return this.jobRepository.findByIsActiveIsTrue();
	}

	public JobTitle findJobTitleByID(Integer id) {
		return this.jobRepository.findOne(id);
	}

	public Integer saveOrUpdate(JobTitle jobTitle) {
		return this.jobRepository.save(jobTitle).getId();
	}

	public Integer removeJobTitle(JobTitle jobTitle) {
		jobTitle.setIsActive(false);
		return saveOrUpdate(jobTitle);
	}

}
