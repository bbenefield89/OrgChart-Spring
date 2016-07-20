package com.nexient.orgchart.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mrangel on 7/20/2016.
 */
public class DepartmentModel extends BaseModel {

    private DepartmentModel parentDepartment;
    private EmployeeModel manager;
    private String name;
    private Set<DepartmentModel> departments = new HashSet<>(0);
    private Set<EmployeeModel> employees = new HashSet<>(0);

    public DepartmentModel getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(DepartmentModel parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public EmployeeModel getManager() {
        return manager;
    }

    public void setManager(EmployeeModel manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DepartmentModel> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentModel> departments) {
        this.departments = departments;
    }

    public Set<EmployeeModel> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeModel> employees) {
        this.employees = employees;
    }
}
