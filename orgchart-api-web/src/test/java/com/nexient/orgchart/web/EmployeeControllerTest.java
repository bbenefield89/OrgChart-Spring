package com.nexient.orgchart.web;


import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.model.Models;
import com.nexient.orgchart.service.EmployeeService;
import com.nexient.orgchart.web.controller.EmployeeController;
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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by mrangel on 8/1/2016.
 */
public class EmployeeControllerTest {

    @InjectMocks
    EmployeeController controller;

    private MockMvc mvc;

    @Mock
    private EmployeeService employeeService;

    private List<Employee> employeeList;

    private Employee employee;

    @BeforeClass
    public void before(){
        MockitoAnnotations.initMocks(this);
        employee = Models.employee();
        employee.setId(Models.EMPLOYEE_ID);
        employeeList = new ArrayList<>();
        employeeList.add(employee);

        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void readJobTitle() throws Exception{
        given(this.employeeService.findEmployeeById(Models.EMPLOYEE_ID))
                .willReturn(employee);
       MvcResult result = this.mvc.perform(get("/emps/"+ employee.getId()).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        JSONObject mvc_result = new JSONObject(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }

    @Test
    public void getAllActiveJobTitles() throws Exception{
        given(this.employeeService.findAllActiveEmployees())
                .willReturn(employeeList);
        MvcResult result = this.mvc.perform(get("/emps").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray mvc_result = new JSONArray(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }

    @Test
    public void findAllArchivedJobTitles() throws Exception{
        given(this.employeeService.findAllInactiveEmployees())
                .willReturn(employeeList);
        MvcResult result = this.mvc.perform(get("/emps/archives").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        JSONArray mvc_result = new JSONArray(result.getResponse().getContentAsString());
        Assert.assertNotNull(mvc_result);
    }

    @Test
    public void createJobTitle() throws Exception{
        given(this.employeeService.storeOrUpdate(employee))
                .willReturn(employee);
        this.mvc.perform(post("/emps").accept(MediaType.APPLICATION_JSON)
                .content(this.employeeCurl())
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Content-type", "application/json")
                .header("Accept", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void updateJobTitle() throws Exception{
        given(this.employeeService.storeOrUpdate(employee))
                .willReturn(employee);
        this.mvc.perform(put("/emps").accept(MediaType.APPLICATION_JSON)
                .content(this.employeeCurl())
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Content-type", "application/json")
                .header("Accept", "application/json"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteJobTitle() throws Exception{

        given(this.employeeService.removeEmployee(employee.getId()))
                .willReturn(true);
        MvcResult result = this.mvc.perform(delete("/emps/"+employee.getId()).accept(MediaType.APPLICATION_JSON)
                .header("Accept-Language", "en-US,en;q=0.5")
                .header("Content-type", "application/json")
                .header("Accept", "application/json"))
                .andDo(print())
                .andExpect(status().isNoContent())
                .andReturn();
        String mvc_result = result.getResponse().getContentAsString();
        Assert.assertNotNull(mvc_result);
        Assert.assertEquals(mvc_result, "true");
    }

    private String employeeCurl(){
        String curl = "{\n" +
                "  \"email\": \"string\",\n" +
                "  \"first_name\": \"string\",\n" +
                "  \"id\": 0,\n" +
                "  \"is_active\": true,\n" +
                "  \"is_manager\": true,\n" +
                "  \"job_title\": {\n" +
                "    \"id\": 0,\n" +
                "    \"is_active\": true,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"last_name\": \"string\",\n" +
                "  \"middle_initial\": {},\n" +
                "  \"skype_name\": \"string\"\n" +
                "}";
        return curl;
    }
}
