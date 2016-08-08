package com.nexient.orgchart.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * EmployeeEntity entity. @author dhoover
 */
@Entity
@Table(name = "employee")
public class EmployeeEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "job_title_id")
	private JobTitleEntity jobTitle;

	@ManyToOne
	@JoinColumn(name = "department_id")
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manager_id")
	private EmployeeEntity manager;

	@Column(name = "skype_name", unique = true, nullable = false)
	@NotNull
	private String skypeName;

	@Column(name = "first_name", nullable = false, length = 20)
	private String firstName;

	@Column(name ="middle_initial", length = 1)
	private Character middleInitial;

	@Column(name = "last_name", nullable = false, length = 45)
	private String lastName;

	@Column(name = "email", length = 100)
	private String email;

	@Column(name = "is_manager", nullable = false)
	private Boolean isManager;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<DepartmentEntity> managedDepartments = new HashSet<>(0);

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "manager")
	private Set<EmployeeEntity> managedEmployees = new HashSet<>(0);

	public boolean getIsManager() {
		return isManager;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
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

	public DepartmentEntity getDepartment() {
		return department;
	}

	public void setDepartment(DepartmentEntity departmentEntity) {
		this.department = departmentEntity;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

	public Character getMiddleInitial() {
	    return middleInitial;
	}

	public void setMiddleInitial(Character middleInitial) {
		this.middleInitial = middleInitial;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public JobTitleEntity getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitleEntity jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Set<DepartmentEntity> getManagedDepartments() {
		return managedDepartments;
	}

	public void setManagedDepartments(Set<DepartmentEntity> departments) {
		this.managedDepartments = departments;
	}

	public Set<EmployeeEntity> getManagedEmployees() {
		return managedEmployees;
	}

	public void setManagedEmployees(Set<EmployeeEntity> employees) {
		this.managedEmployees = employees;
	}
}