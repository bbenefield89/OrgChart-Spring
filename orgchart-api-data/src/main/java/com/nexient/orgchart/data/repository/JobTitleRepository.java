package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface JobTitleRepository extends BaseRepository<JobTitleEntity, Integer> {

    public JobTitleEntity findByName(String name);
}
