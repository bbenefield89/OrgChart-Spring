package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.JobTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kskronek on 5/24/2016.
 */
@Repository
public interface JobTitleRepository extends BaseRepository<JobTitle, Integer> {

}
