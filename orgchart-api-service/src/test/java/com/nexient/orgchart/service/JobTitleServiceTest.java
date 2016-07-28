package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import com.nexient.orgchart.mapper.EmployeeMapper;
import com.nexient.orgchart.mapper.JobTitleMapper;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class JobTitleServiceTest {

   @InjectMocks
    JobTitleService jobTitleService;

    @Spy
    JobTitleMapper mockJobTitleMapper= new JobTitleMapper();

    private JobTitleEntity title;

    @Mock
    EmployeeEntity mockEmployee;

    @Mock
    private JobTitleRepository repo;

    @Mock
    private EmployeeRepository empRepo;

    private List<JobTitleEntity> listOfFoundTitles;

    private List<EmployeeEntity> listOfFoundEmployees;
	@BeforeTest
	public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        listOfFoundTitles = new ArrayList<>();
        title = Entities.jobTitle();
        title.setId(Entities.JOB_TITLE_ID);
        listOfFoundTitles.add(title);

        listOfFoundEmployees = new ArrayList<EmployeeEntity>();
        listOfFoundEmployees.add(mockEmployee);

        when(repo.findAll()).thenReturn(this.listOfFoundTitles);
        when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.title);
        when(repo.save(any(JobTitleEntity.class))).thenReturn(this.title);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
        when(empRepo.findByJobTitle(any(JobTitleEntity.class))).thenReturn(this.listOfFoundEmployees);

	}

    @Test
    public void findAllJobTitlesTest() {
        List<JobTitle> titles = this.jobTitleService.findAll();
        Assert.assertNotNull(titles);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void findAllActiveJobTitlesTest(){
        List<JobTitle> titles = this.jobTitleService.findAllActiveJobTitles();
        Assert.assertNotNull(titles);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void findJobTitleByIDTest() {

        JobTitle title = this.jobTitleService.findJobTitleByID(Entities.JOB_TITLE_ID);
        Assert.assertNotNull(title);
        Assert.assertEquals(title.getId(), Models.JOB_TITLE_ID);
    }

    @Test
    public void storeJobTitleTest() {
        JobTitleEntity title = this.jobTitleService.storeOrUpdate(mockJobTitleMapper.entityToModel(this.title));
        Assert.assertNotNull(title);
        Assert.assertEquals(this.title.getId(), title.getId());
    }

    @Test
    public void removeJobTitleTest(){
        this.title.setIsActive(true);

        doAnswer(new Answer<JobTitleEntity>() {
            @Override
            public JobTitleEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                JobTitleEntity jobby = (JobTitleEntity) args[0];
                jobby.setIsActive(false);
                return jobby;
            }
        }).when(this.repo).save(any(JobTitleEntity.class));

        Assert.assertTrue(this.jobTitleService.removeJobTitle(mockJobTitleMapper.entityToModel(this.title)));
    }

    @Test
    public void removeJobTitleTest_False(){
        this.title.setIsActive(true);

        doAnswer(new Answer<JobTitleEntity>() {
            @Override
            public JobTitleEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                JobTitleEntity jobby = (JobTitleEntity) args[0];
                jobby.setIsActive(true);
                return jobby;
            }
        }).when(this.repo).save(any(JobTitleEntity.class));

        Assert.assertFalse(this.jobTitleService.removeJobTitle(mockJobTitleMapper.entityToModel(this.title)));

    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeJobTitle_Null()throws Exception{
        this.jobTitleService.storeOrUpdate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeJobTitle_Null()throws Exception{
        this.jobTitleService.removeJobTitle(null);
    }
}
