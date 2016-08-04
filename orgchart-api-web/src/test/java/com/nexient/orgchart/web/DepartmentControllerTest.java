package com.nexient.orgchart.web;


import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.DepartmentService;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mrangel on 8/1/2016.
 */
public class DepartmentControllerTest {

    @InjectMocks
    DepartmentController controller;

    private MockMvc mvc;

    @Mock
    private DepartmentService departmentService;

    private List<Department> departmentList;

    private Department department;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        department = Models.department();
        department.setId(Models.DEPT_ID);
        departmentList= new ArrayList<>();
        departmentList.add(department);

        mvc= MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void readDepartment() throws Exception{
        given(this.departmentService.findDepartmentByID(Models.DEPT_ID))
                .willReturn(department);

        MvcResult result = this.mvc.perform(get("/depts/"+department.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONObject mvc_result = new JSONObject(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }
//
//    @Test
//    public void getAllActiveDepartments() throws Exception{
//        given(this.departmentService.findAllActiveDepartments())
//                .willReturn(departmentList);
//        MvcResult result = this.mvc.perform(get("/depts").accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andReturn();
//
//        JSONObject mvc_result = new JSONObject(result.getResponse().getContentAsString());
//        Assert.assertNotNull(mvc_result);
//    }

    @Test
    public void getAllInactiveDepartments() throws Exception{
        given(this.departmentService.findAllInactiveDepartments())
                .willReturn(departmentList);
        this.mvc.perform(get("/depts/archives").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createDepartment() throws Exception{
        given(this.departmentService.storeOrUpdate(department))
                .willReturn(department);
        this.mvc.perform(post("/depts").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateDepartment() throws Exception{
        given(this.departmentService.storeOrUpdate(department))
                .willReturn(department);
        this.mvc.perform(put("/depts").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteDepartment() throws Exception{
        given(this.departmentService.removeDepartment(department.getId()))
                .willReturn(true);
        this.mvc.perform(delete("/depts/"+department.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
