package com.nexient.orgchart.mapper;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import org.springframework.stereotype.Component;

/**
 * Created by mrangel on 7/26/2016.
 */

@Component
public class JobTitleMapper implements EntityModelMapper<JobTitleEntity, JobTitle>{

    @Override
    public JobTitle entityToModel(JobTitleEntity entity) {
        JobTitle jobTitleModel = new JobTitle();
        jobTitleModel.setId(entity.getId());
        jobTitleModel.setIsActive(entity.getIsActive());
        jobTitleModel.setName(entity.getName());
        return jobTitleModel;
    }

    @Override
    public JobTitleEntity modelToEntity(JobTitle model) {
        JobTitleEntity jobTitleEntity= new JobTitleEntity();
        jobTitleEntity.setId(model.getId());
        jobTitleEntity.setName(model.getName());
        jobTitleEntity.setIsActive(model.getIsActive());
        return null;
    }

}
