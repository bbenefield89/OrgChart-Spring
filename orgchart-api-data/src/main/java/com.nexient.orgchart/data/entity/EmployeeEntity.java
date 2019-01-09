package com.nexient.orgchart.data.entity;

import java.util.Set;

public class EmployeeEntity extends BaseEntity {
	private String firstName;
	private char middleInitial;
	private String lastName;
	private String email;
	private String skypeName;
    private JobTitleEntity jobTitle;
    private EmployeeEntity manager;
    private DepartmentEntity department;
    private Set<EmployeeEntity> ManagedEmployees;
    private Set<DepartmentEntity> ManagedDepartments;
	private boolean isManager;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public char getMiddleInitial() {
        return middleInitial;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public JobTitleEntity getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitleEntity jobTitle) {
        this.jobTitle = jobTitle;
    }

    public EmployeeEntity getManager() {
        return manager;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public Set<EmployeeEntity> getManagedEmployees() {
        return ManagedEmployees;
    }

    public void setManagedEmployees(Set<EmployeeEntity> managedEmployees) {
        ManagedEmployees = managedEmployees;
    }

    public Set<DepartmentEntity> getManagedDepartments() {
        return ManagedDepartments;
    }

    public void setManagedDepartments(Set<DepartmentEntity> managedDepartments) {
        ManagedDepartments = managedDepartments;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean manager) {
        isManager = manager;
    }
}