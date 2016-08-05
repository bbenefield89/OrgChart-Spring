package com.nexient.orgchart.web;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;

import com.nexient.orgchart.service.JobTitleService;


import com.nexient.orgchart.web.com.nexient.orgchart.web.controller.JobTitleController;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

    @Mock
    private JobTitleService jobTitleService;

    private MockMvc mvc;
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
        MvcResult result = this.mvc.perform(get("/titles/" + jobTitle.getId()).accept(MediaType.APPLICATION_JSON).content(jobTitle.toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject mvc_result = new JSONObject(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }

    @Test
    public void readJobTitle_nonValue() throws Exception{
        given(this.jobTitleService.findJobTitleByID((Integer) null))
                .willReturn(null);
        this.mvc.perform(get("/titles/" + null).accept(MediaType.APPLICATION_JSON).content(jobTitle.toString()))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

    }

    @Test
    public void getAllActiveJobTitles() throws Exception{
        given(this.jobTitleService.findAllActiveJobTitles())
                .willReturn(titles);
        MvcResult result = this.mvc.perform(get("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray mvc_result = new JSONArray(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }

    @Test
    public void findAllArchivedJobTitles() throws Exception{
        given(this.jobTitleService.findAllInactiveJobTitles())
                .willReturn(titles);
        MvcResult result = this.mvc.perform(get("/titles/archives").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray mvc_result = new JSONArray(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
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
        MvcResult result = this.mvc.perform(delete("/titles/"+id).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String mvc_result = result.getResponse().getContentAsString();
        Assert.assertNotNull(mvc_result);
        Assert.assertEquals(mvc_result, "true");
    }

}
