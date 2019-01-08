package com.nexient.orgchart.data.entity;

public class EmployeeEntity extends BaseEntity {
	private String firstName;
	private char middleInitial;
	private String lastName;
	private String email;
	private String skypeName;
	private boolean isManager;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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