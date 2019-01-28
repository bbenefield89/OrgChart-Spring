package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class JobTitleMapper implements EntityModelMapper<JobTitleEntity, JobTitle> {

    private String assertionErrorMessage = "[Assertion Error] - JobTitle is required; must not be null.";

    @Override
    public JobTitle entityToModel(JobTitleEntity jobTitleEntity) {
        Assert.notNull(jobTitleEntity, assertionErrorMessage);

        JobTitle jobTitle = new JobTitle();
        jobTitle.setId(jobTitleEntity.getId());
        jobTitle.setName(jobTitleEntity.getName());
        jobTitle.setIsActive(jobTitleEntity.getIsActive());

        return jobTitle;
    }

    @Override
    public JobTitleEntity modelToEntity(JobTitle jobTitle) {
        Assert.notNull(jobTitle, assertionErrorMessage);

        JobTitleEntity jobTitleEntity = new JobTitleEntity();
        jobTitleEntity.setId(jobTitle.getId());
        jobTitleEntity.setName(jobTitle.getName());
        jobTitleEntity.setIsActive(jobTitle.getIsActive());

        return jobTitleEntity;
    }
}
