package com.nexient.orgchart.web;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.JobTitleService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

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
        //TODO Keep working here
    }

    @Test
    public void testGetAllActiveJobTitles(){

    }

}
