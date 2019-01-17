package com.nexient.orgchart.web.controller;

import com.nexient.orgchart.model.Employee;
import com.nexient.orgchart.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/emps")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping
    public List<Employee> getAllActiveEmployees() {
        return employeeService.findAllActiveEmployees();
    }

    @GetMapping("/archives")
    public List<Employee> findAllArchivedEmployees() {
        return employeeService.findAllInactiveEmployees();
    }

    @RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
    public Employee createOrUpdateEmployee(@RequestBody Employee employee) {
        return employeeService.storeOrUpdate(employee);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{employeeId}")
    public boolean deleteEmployee(@PathVariable Integer employeeId) {
        return employeeService.removeEmployee(employeeId);
    }

}
