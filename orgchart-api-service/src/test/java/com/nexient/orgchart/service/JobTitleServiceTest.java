package com.nexient.orgchart.service;

import com.nexient.orgchart.data.entity.EmployeeEntity;
import com.nexient.orgchart.data.entity.Entities;
import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.EmployeeRepository;
import com.nexient.orgchart.data.repository.JobTitleRepository;
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
    JobTitleMapper mockJobTitleMapper = new JobTitleMapper();

    @Mock
    private JobTitleRepository repo;

    @Mock
    private EmployeeRepository empRepo;

    private JobTitleEntity titleEntity;
    private EmployeeEntity employeeEntity;
    private List<JobTitleEntity> listOfFoundTitles;
    private List<EmployeeEntity> listOfFoundEmployees;

    @BeforeTest
    public void before() throws Exception {
        MockitoAnnotations.initMocks(this);

        listOfFoundTitles = new ArrayList<>();
        titleEntity = Entities.jobTitle();
        titleEntity.setId(Entities.JOB_TITLE_ID);
        listOfFoundTitles.add(titleEntity);

        employeeEntity = Entities.employee();
        employeeEntity.setId(Entities.EMPLOYEE_ID);
        employeeEntity.setJobTitle(titleEntity);
        listOfFoundEmployees = new ArrayList<EmployeeEntity>();
        listOfFoundEmployees.add(employeeEntity);

        when(repo.findAll()).thenReturn(this.listOfFoundTitles);
        when(repo.findOne(Entities.JOB_TITLE_ID)).thenReturn(this.titleEntity);
        when(repo.save(any(JobTitleEntity.class))).thenReturn(this.titleEntity);
        when(repo.findByIsActiveIsTrue()).thenReturn(this.listOfFoundTitles);
        when(empRepo.findByJobTitle(any(JobTitleEntity.class))).thenReturn(this.listOfFoundEmployees);
        when(repo.findByIsActiveIsFalse()).thenReturn(this.listOfFoundTitles);

    }

    @Test
    public void findAllJobTitlesTest() {
        List<JobTitle> titles = this.jobTitleService.findAll();
        Assert.assertNotNull(titles);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void findAllInactiveJobTitlesTest() {
        List<JobTitle> titles = this.jobTitleService.findAllInactiveJobTitles();
        Assert.assertNotNull(titles);
        Assert.assertTrue(titles.size() > 0);
    }

    @Test
    public void findAllActiveJobTitlesTest() {
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
        JobTitle title = this.jobTitleService.storeOrUpdate(mockJobTitleMapper.entityToModel(this.titleEntity));
        Assert.assertNotNull(title);
        Assert.assertNotNull(title.getId());
        Assert.assertEquals(this.titleEntity.getId(), title.getId());
    }

    @Test
    public void removeJobTitleTest() {
        this.titleEntity.setIsActive(true);
        boolean isRemoved = this.jobTitleService.removeJobTitle((this.titleEntity.getId()));

        Assert.assertNotNull(isRemoved);
        Assert.assertTrue(isRemoved);
    }

    @Test
    public void removeJobTitleTest_False() {
        this.titleEntity.setIsActive(true);

        doAnswer(new Answer<JobTitleEntity>() {
            @Override
            public JobTitleEntity answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                JobTitleEntity jobby = (JobTitleEntity) args[0];
                jobby.setIsActive(true);
                return jobby;
            }
        }).when(this.repo).save(any(JobTitleEntity.class));
        Assert.assertNotNull(this.jobTitleService.removeJobTitle(this.titleEntity.getId()));
        Assert.assertFalse(this.jobTitleService.removeJobTitle(this.titleEntity.getId()));
    }

    @Test
    public void testSetEmployeesJobTitleWithJobTitleToNull(){
        List<EmployeeEntity> emps = jobTitleService.setEmployeesJobTitleWithJobTitleToNull(listOfFoundEmployees);
        Assert.assertNotNull(emps);
        Assert.assertTrue(emps.size() > 0);

        for(EmployeeEntity e: emps){
            Assert.assertNull(e.getJobTitle());
        }
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void storeJobTitle_Null() {
        this.jobTitleService.storeOrUpdate(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeJobTitle_Null() {
        this.jobTitleService.removeJobTitle(null);
    }

}
