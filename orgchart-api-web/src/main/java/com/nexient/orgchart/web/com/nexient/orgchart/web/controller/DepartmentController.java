package com.nexient.orgchart.web.com.nexient.orgchart.web.controller;

import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mrangel on 7/26/2016.
 */

@RestController
@RequestMapping("/depts")
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getAllActiveDepartments(){
        return deptService.findAllActiveDepartments();
    }

    @RequestMapping(value = "/{deptId}", method = RequestMethod.GET)
    public Department readDepartment(@PathVariable int deptId){
     return deptService.findDepartmentByID(deptId);
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    public List<Department>findAllArchivedDepartments(){
        return deptService.findAllInactiveDepartments();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Department createDepartment(@Valid Department dept){
        return deptService.storeOrUpdate(dept);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Department updateDepartment(@Valid Department dept){
        return deptService.storeOrUpdate(dept);
    }

    @RequestMapping(value = "/{departmentId}", method = RequestMethod.DELETE)
    public boolean deleteDepartment(@PathVariable int departmentId){
        return deptService.removeDepartment(departmentId);
    }

}
