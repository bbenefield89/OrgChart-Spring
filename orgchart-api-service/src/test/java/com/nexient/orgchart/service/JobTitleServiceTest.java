package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitle;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(classes = ServiceTestConfig.class)
public class JobTitleServiceTest extends AbstractTestNGSpringContextTests {

    @Autowired
    JobTitleService jobTitleService;

    @Autowired
    private JobTitle mockTitle;

    private ArrayList<JobTitle> findAllJobTitleList = new ArrayList<JobTitle>();

//	@BeforeClass
//	public void before() throws Exception {
//
//	}

    @Test
    public void findAllJobTitlesTest() {
        List<JobTitle> titles = this.jobTitleService.findAll();
        assertNotNull(titles);
    }

    @Test
    public void findAllActiveJobTitlesTest(){
        List<JobTitle> titles = this.jobTitleService.findAllActiveJobTitles();
        Assert.assertNotNull(titles);
    }

    @Test
    public void findJobTitleByIDTest() {

        JobTitle title = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
        Assert.assertNotNull(title);
        Assert.assertEquals(title.getId(), Entities.JOB_TITLE_ID);
    }

    @Test
    public void storeJobTitleTest() {
        // TODO check if id is valid before updating
        // TODO throw entity out of sync exception if date stamps don't match
        Integer id = this.jobTitleService.saveOrUpdate(this.mockTitle);
        Assert.assertEquals(this.mockTitle.getId(), id);
    }

    @Test
    public void removeJobTitleTest(){
        this.mockTitle.setIsActive(true);
        Integer id = this.jobTitleService.removeJobTitle(this.mockTitle);
        Assert.assertEquals(this.mockTitle.getId(), id);
        Assert.assertFalse(this.mockTitle.getIsActive());
    }

}
