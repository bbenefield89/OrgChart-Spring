package com.nexient.orgchart.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by mrangel on 7/20/2016.
 */
public class Department extends BaseModel {

    private Department parentDepartment;
    private Employee manager;
    private String name;

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
