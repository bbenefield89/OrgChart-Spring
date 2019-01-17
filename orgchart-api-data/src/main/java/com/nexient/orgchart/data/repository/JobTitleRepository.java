package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface JobTitleRepository extends BaseRepository<JobTitleEntity, Integer> {

    JobTitleEntity findByName(String name);
    JobTitleEntity save(JobTitleEntity jobTitleEntity) throws DataIntegrityViolationException;
}
