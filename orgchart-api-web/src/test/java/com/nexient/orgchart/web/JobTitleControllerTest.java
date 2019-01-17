package com.nexient.orgchart.web;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.JobTitleService;
import com.nexient.orgchart.web.controller.JobTitleController;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class JobTitleControllerTest {

    private MockMvc mockMvc;
    private JobTitle jobTitle;
    private List<JobTitle> jobTitleList = new ArrayList<>();

    @InjectMocks
    private JobTitleController jobTitleController;

    @Mock
    private JobTitleService jobTitleService;

    @BeforeClass
    public void before() {
        MockitoAnnotations.initMocks(this);

        jobTitle = Models.jobTitle();
        jobTitle.setId(Models.JOB_TITLE_ID);

        jobTitleList.add(jobTitle);

        mockMvc = MockMvcBuilders.standaloneSetup(jobTitleController).build();
    }

    @Test
    public void testGetAllActiveJobTitles() throws Exception {
//        given(jobTitleService.findAllActiveJobTitles()).willReturn(jobTitleList);
        when(jobTitleService.findAllActiveJobTitles()).thenReturn(jobTitleList);

        MvcResult mvcResult = mockMvc.perform(get("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());

        Assert.assertNotNull(jsonArray);
    }

    @Test
    public void testReadJobTitle() throws Exception {
        when(jobTitleService.findJobTitleByID(jobTitle.getId())).thenReturn(jobTitle);

        MvcResult mvcResult = mockMvc.perform(get("/titles/" + jobTitle.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject jsonObject = new JSONObject(mvcResult.getResponse().getContentAsString());

        Assert.assertNotNull(jsonObject);
    }

    @Test
    public void testFindAllArchivedJobTitles() throws Exception {
        when(jobTitleService.findAllInactiveJobTitles()).thenReturn(jobTitleList);

        MvcResult mvcResult = mockMvc.perform(get("/titles").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray jsonArray = new JSONArray(mvcResult.getResponse().getContentAsString());

        Assert.assertNotNull(jsonArray);
    }

    @Test
    public void testCreateJobTitle() throws Exception {
        when(jobTitleService.storeOrUpdate(jobTitle)).thenReturn(jobTitle);

        this.mockMvc.perform(post("/titles").accept(MediaType.APPLICATION_JSON)
                .content(this.jobTitleCurl())
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Content-type", "application/json")
                .header("Accept", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateJobTitle() throws Exception {
        when(jobTitleService.storeOrUpdate(jobTitle)).thenReturn(jobTitle);

        this.mockMvc.perform(post("/titles").accept(MediaType.APPLICATION_JSON)
                .content(this.jobTitleCurl())
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Content-type", "application/json")
                .header("Accept", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteJobTitle() throws Exception {
        when(jobTitleService.removeJobTitle(jobTitle.getId())).thenReturn(true);

        MvcResult mvcResult = mockMvc.perform(delete("/titles/" + jobTitle.getId()).
                accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        String mvc_result = mvcResult.getResponse().getContentAsString();
        Assert.assertNotNull(mvc_result);
        Assert.assertEquals(mvc_result, "true");
    }

    private String jobTitleCurl(){
        String curl = "{\n" +
                "  \"id\": 0,\n" +
                "  \"is_active\": true,\n" +
                "  \"name\": \"string\"\n" +
                "}";
        return curl;
    }

}
