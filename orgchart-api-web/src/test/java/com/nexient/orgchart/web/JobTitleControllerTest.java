package com.nexient.orgchart.web;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.JobTitleService;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by dhoover on 7/29/2016.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(JobTitleController.class)
public class JobTitleControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JobTitleService jobTitleService;

    private List<JobTitle> titles;

    private JobTitle jobTitle;

    @Before
    public void setup(){
        jobTitle= Models.jobTitle();
        jobTitle.setId(Models.JOB_TITLE_ID);
        titles = new ArrayList<>();
        titles.add(jobTitle);
    }

    @Test
    public void getAllActiveJobTitles() throws Exception{
        given(this.jobTitleService.findJobTitleByID(Models.JOB_TITLE_ID))
                .willReturn(jobTitle);
        this.mvc.perform(get("/titles").accept(MediaType.ALL))
                .andExpect(status().isOk());
    }


}
