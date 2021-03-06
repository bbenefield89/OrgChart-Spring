package com.nexient.orgchart.data.repository;

import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mrangel on 7/22/2016.
 */

@ContextConfiguration(classes = TestJpaConfig.class)
@Transactional
public class JobTitleRepositoryTest extends AbstractTransactionalTestNGSpringContextTests{
    private static final String SOME_NEW_NAME = "Some New Name";

    private static final String NOT_PRESENT_VALUE = "XXX";

    private JobTitleEntity jobTitle;

    @Autowired
    JobTitleRepository jobTitleRepo;

    @BeforeClass
    public void before() throws Exception {
        super.springTestContextPrepareTestInstance();
        this.jobTitle = Entities.jobTitle();
        this.jobTitle.setId(this.jobTitleRepo.save(this.jobTitle).getId());
    }

    @Test
    public void testInstantiation() {
        Assert.assertNotNull(jobTitleRepo);
    }

    @Test
    public void created() {
        Assert.assertNotNull(this.jobTitle);
        Assert.assertNotNull(this.jobTitle.getId());
    }

    @Test(expectedExceptions = DataIntegrityViolationException.class)
    public void duplicateName() {
        JobTitleEntity title = new JobTitleEntity();
        title.setName(jobTitle.getName());
        jobTitleRepo.save(title);
    }

    @Test
    public void findAll_notNull() throws Exception {
        System.out.println(this.jobTitleRepo.toString());
        List<JobTitleEntity> titles = this.jobTitleRepo.findAll();
        Assert.assertNotNull(titles);
        Assert.assertTrue(0 < titles.size());
    }

    @Test
    public void findByIsActiveIsTrue() throws Exception {
        jobTitle.setIsActive(true);
        jobTitleRepo.save(jobTitle);
        List<JobTitleEntity> titles = this.jobTitleRepo.findByIsActiveIsTrue();
        Assert.assertNotNull(titles);
        Assert.assertTrue(!titles.isEmpty());
    }

    @Test
    public void findByName() throws Exception {
        JobTitleEntity title = this.jobTitleRepo.findByName(this.jobTitle.getName());
        Assert.assertNotNull(title);
        Assert.assertEquals(this.jobTitle.getName(), title.getName());
    }

    @Test
    public void findByName_null() throws Exception {
        JobTitleEntity title = this.jobTitleRepo.findByName(NOT_PRESENT_VALUE);
        Assert.assertNull(title);
    }

    @Test
    public void update() throws Exception {
        JobTitleEntity title = this.jobTitleRepo.findByName(this.jobTitle.getName());
        title.setName(SOME_NEW_NAME);
        this.jobTitleRepo.save(title);
        title = null;
        title = this.jobTitleRepo.findByName(SOME_NEW_NAME);
        Assert.assertNotNull(title);
        Assert.assertEquals(SOME_NEW_NAME, title.getName());
    }
}