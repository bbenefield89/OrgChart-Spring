package com.nexient.orgchart.web;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;

import com.nexient.orgchart.service.JobTitleService;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dhoover on 7/29/2016.
 */

public class JobTitleControllerTest {

    @InjectMocks
    JobTitleController controller;

    private MockMvc mvc;

    @Mock
    private JobTitleService jobTitleService;

    private List<JobTitle> titles;

    private JobTitle jobTitle;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        jobTitle= Models.jobTitle();
        jobTitle.setId(Models.JOB_TITLE_ID);
        titles = new ArrayList<>();
        titles.add(jobTitle);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void readJobTitle() throws Exception{
        given(this.jobTitleService.findJobTitleByID(Models.JOB_TITLE_ID))
                .willReturn(jobTitle);
        this.mvc.perform(get("/titles/"+jobTitle.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllActiveJobTitles() throws Exception{
        given(this.jobTitleService.findAllActiveJobTitles())
                .willReturn(titles);
        this.mvc.perform(get("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void findAllArchivedJobTitles() throws Exception{
        given(this.jobTitleService.findAllInactiveJobTitles())
                .willReturn(titles);
        this.mvc.perform(get("/titles/archives").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createJobTitle() throws Exception{
        given(this.jobTitleService.storeOrUpdate(jobTitle))
                .willReturn(jobTitle);
        this.mvc.perform(post("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateJobTitle() throws Exception{
        given(this.jobTitleService.storeOrUpdate(jobTitle))
                .willReturn(jobTitle);
        this.mvc.perform(put("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteJobTitle() throws Exception{
        int id= jobTitle.getId();
        given(this.jobTitleService.removeJobTitle(jobTitle.getId()))
                .willReturn(true);
        this.mvc.perform(delete("/titles/"+id).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
