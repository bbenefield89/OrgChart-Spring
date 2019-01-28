package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.JobTitleEntity;

public interface JobTitleRepository extends BaseRepository<JobTitleEntity, Integer> {

    JobTitleEntity findByName(String name);

}
