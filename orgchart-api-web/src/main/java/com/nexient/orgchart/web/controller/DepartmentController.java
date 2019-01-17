package com.nexient.orgchart.web.controller;

import com.nexient.orgchart.model.Department;
import com.nexient.orgchart.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/depts")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<Department> getAllActiveDepartments() {
        return departmentService.findAllActiveDepartments();
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable Integer departmentId) {
        return departmentService.findDepartmentByID(departmentId);
    }

    @GetMapping("/archives")
    public List<Department> getAllArchivedDepartments() {
        return departmentService.findAllInactiveDepartments();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.storeOrUpdate(department);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{departmentId}")
    public boolean deleteDepartment(@PathVariable Integer departmentId) {
        return departmentService.removeDepartment(departmentId);
    }

}
