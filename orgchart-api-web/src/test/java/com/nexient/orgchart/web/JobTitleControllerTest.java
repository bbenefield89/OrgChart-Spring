package com.nexient.orgchart.web;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.data.repository.JobTitleRepository;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.JobTitleService;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dhoover on 7/29/2016.
 */

@WebAppConfiguration
@ContextConfiguration
public class JobTitleControllerTest {

    private JobTitle jobTitle;
    private ArrayList<JobTitle> findAllJobTitlesList;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Mock
    private JobTitleService mockTitleService;

    @Mock
    private JobTitleRepository mockTitleRepository;

    @InjectMocks
    private JobTitleController controller = new JobTitleController();

    @BeforeClass
    public void before() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
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
    public void testGetAllActiveJobTitles() throws Exception {

        List<JobTitle> jobTitleList = this.mockTitleService.findAllActiveJobTitles();

        Assert.assertNotNull(jobTitleList);
        Assert.assertFalse(jobTitleList.isEmpty());
    }

    @Test
    public void readJobTitleTest() {
        JobTitle title = this.mockTitleService.findJobTitleByID(this.jobTitle.getId());

        Assert.assertNotNull(title);
        Assert.assertEquals(this.jobTitle.getId(), title.getId());
        Assert.assertEquals(this.jobTitle.getName(), title.getName());
    }

    @Test
    public void findAllInactiveJobTitlesTest() {
        List<JobTitle> titles = this.mockTitleService.findAllInactiveJobTitles();

        Assert.assertNotNull(titles);
        Assert.assertFalse(titles.isEmpty());
    }

    @Test
    public void createOrUpdateTest() throws Exception {

        this.mockMvc.perform(post("titles"))
                .andDo(print())
                .andExpect(status().isOk()).andExpect(content()
                .contentType("application/json;charset=UTF-8"))
                .andExpect(content().json("{}"))
                .andDo(print()).andReturn();

        JobTitle title = this.mockTitleService.storeOrUpdate(jobTitle);

        Assert.assertNotNull(title);
        Assert.assertEquals(title.getId(), jobTitle.getId());
    }

    @Test
    public void deleteTest() {
        boolean hasDeleted = this.mockTitleService.removeJobTitle(jobTitle.getId());

        Assert.assertNotNull(hasDeleted);
        Assert.assertTrue(hasDeleted);
    }

}
