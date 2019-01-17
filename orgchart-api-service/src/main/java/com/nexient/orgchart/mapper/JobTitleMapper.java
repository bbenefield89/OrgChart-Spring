package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.util.Assert;

public class JobTitleMapper {

    private String assertionErrorMessage = "[Assertion Error] - JobTitle is required; must not be null.";

    public JobTitle entityToModel(JobTitleEntity jobTitleEntity) {
        Assert.notNull(jobTitleEntity, assertionErrorMessage);

        JobTitle jobTitle = new JobTitle();
        jobTitle.setName(jobTitleEntity.getName());
        jobTitle.setId(jobTitleEntity.getId());

        return jobTitle;
    }

    public JobTitleEntity modelToEntity(JobTitle jobTitle) {
        Assert.notNull(jobTitle, assertionErrorMessage);

        JobTitleEntity jobTitleEntity = new JobTitleEntity();
        jobTitleEntity.setName(jobTitle.getName());
        return jobTitleEntity;
    }
}
