package com.nexient.orgchart.model;

public class Employee {
    private int id;
    private Department department;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private JobTitle jobTitle;
    private String email;
    private String skypeName;
    private boolean isManager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public void setIsManager(boolean manager) {
        isManager = manager;
    }
}
