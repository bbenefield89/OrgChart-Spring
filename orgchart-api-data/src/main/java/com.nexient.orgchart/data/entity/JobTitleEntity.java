package com.nexient.orgchart.data.entity;

import java.util.Set;

public class JobTitleEntity extends BaseEntity {
    private String name;
    private Set<EmployeeEntity> titleEmployees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeEntity> getTitleEmployees() {
        return titleEmployees;
    }

    public void setTitleEmployees(Set<EmployeeEntity> titleEmployees) {
        this.titleEmployees = titleEmployees;
    }
}