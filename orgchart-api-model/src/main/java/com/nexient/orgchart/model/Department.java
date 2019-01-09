package com.nexient.orgchart.model;

/**
 * Created by mrangel on 7/20/2016.
 */
public class Department {

    private Department parentDepartment;
    private Employee manager;
    private String name;
    private int id;
    private boolean isActive;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
