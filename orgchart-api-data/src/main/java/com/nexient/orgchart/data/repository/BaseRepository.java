package com.nexient.orgchart.data.repository;

/**
 * Created by mrangel on 7/19/2016.
 */
import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.nexient.orgchart.data.entity.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    List<T> findByIsActiveIsTrue();

}