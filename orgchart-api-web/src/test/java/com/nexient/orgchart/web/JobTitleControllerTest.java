package com.nexient.orgchart.web;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.JobTitleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * Created by dhoover on 7/29/2016.
 */
public class JobTitleControllerTest {

    private JobTitle jobTitle;
    private ArrayList<JobTitle> findAllJobTitlesList;

    @Mock
    private JobTitleService mockTitleService;

    @InjectMocks
    private JobTitleController controller = new JobTitleController();

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        this.jobTitle = Models.jobTitle();
        this.jobTitle.setId(Models.JOB_TITLE_ID);
        findAllJobTitlesList = new ArrayList<>();
        findAllJobTitlesList.add(jobTitle);

        when(this.mockTitleService.findAllActiveJobTitles()).thenReturn(findAllJobTitlesList);
        when(this.mockTitleService.findJobTitleByID(anyInt())).thenReturn(jobTitle);
        when(this.mockTitleService.findAllInactiveJobTitles()).thenReturn(findAllJobTitlesList);
        when(this.mockTitleService.storeOrUpdate(any(JobTitle.class))).thenReturn(jobTitle);
        when(this.mockTitleService.removeJobTitle(jobTitle.getId())).thenReturn(true);
    }

    @Test
    public void testGetAllActiveJobTitles(){
        List<JobTitle> jobTitleList = this.mockTitleService.findAllActiveJobTitles();

        Assert.assertNotNull(jobTitleList);
        Assert.assertFalse(jobTitleList.isEmpty());
    }

    @Test
    public void readJobTitleTest(){
        JobTitle title= this.mockTitleService.findJobTitleByID(this.jobTitle.getId());

        Assert.assertNotNull(title);
        Assert.assertEquals(this.jobTitle.getId(), title.getId());
        Assert.assertEquals(this.jobTitle.getName(), title.getName());
    }

    @Test
    public void findAllInactiveJobTitlesTest(){
        List<JobTitle> titles= this.mockTitleService.findAllInactiveJobTitles();

        Assert.assertNotNull(titles);
        Assert.assertFalse(titles.isEmpty());
    }

    @Test
    public void createOrUpdateTest(){
        JobTitle title = this.mockTitleService.storeOrUpdate(jobTitle);

        Assert.assertNotNull(title);
        Assert.assertEquals(title.getId(), jobTitle.getId());
    }

    @Test
    public void deleteTest(){
        boolean hasDeleted= this.mockTitleService.removeJobTitle(jobTitle.getId());

        Assert.assertNotNull(hasDeleted);
        Assert.assertTrue(hasDeleted);
    }

}
