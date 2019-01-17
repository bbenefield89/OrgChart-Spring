package com.nexient.orgchart.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

import com.nexient.orgchart.data.entity.JobTitleEntity;

@Entity
@Table(name = "employees")
public class EmployeeEntity extends BaseEntity {

    @Column(name = "first_name")
    @NotEmpty
    @Size(min = 1)
	private String firstName;

    @Column(name = "middle_initial")
    @NotEmpty
    @Size(min = 1)
	private char middleInitial;

    @Column(name = "last_name")
    @NotEmpty
    @Size(min = 1)
	private String lastName;

    @Column(name = "email")
    @NotEmpty
    @Size(min = 1)
	private String email;

    @Column(name = "skype_name")
    @NotEmpty
    @Size(min = 1)
	private String skypeName;

    @ManyToOne
    @JoinColumn(name = "jobtitle_id")
    private JobTitleEntity jobTitle;

    @ManyToOne
    @JoinColumn(name = "manager")
    @NotNull
    private EmployeeEntity manager;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @NotEmpty
    private DepartmentEntity department;

    @OneToMany
    @NotNull
    private Set<EmployeeEntity> ManagedEmployees;

    @OneToMany
    @NotNull
    private Set<DepartmentEntity> ManagedDepartments;

    @Column(name = "is_manager")
    @NotNull
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