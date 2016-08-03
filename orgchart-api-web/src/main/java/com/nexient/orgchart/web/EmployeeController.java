package com.nexient.orgchart.web;

import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mrangel on 7/26/2016.
 */

@RestController
@RequestMapping("/emps")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> getAllActiveEmployees(){
        return empService.findAllActiveEmployees();
    }

    @RequestMapping(value = "/{empId}", method = RequestMethod.GET)
    public Employee readEmployee(@PathVariable int empId){
        return empService.findEmployeeById(empId);
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    public List<Employee> findAllArchivedEmployees(){
        return empService.findAllInactiveEmployees();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Employee createEmployee(@Valid Employee emp){
        return empService.storeOrUpdate(emp);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Employee updateEmployee(@Valid Employee emp){
        return empService.storeOrUpdate(emp);
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
    public boolean deleteEmployee(@PathVariable int employeeId){
        return empService.removeEmployee(employeeId);
    }

}
