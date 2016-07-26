package com.nexient.orgchart.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * EmployeeEntity entity. @author dhoover
 */
@Entity
@Table(name = "EMPLOYEE")
public class EmployeeEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "JOB_TITLE_ID")
	private JobTitleEntity jobTitle;

	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_ID")
	private DepartmentEntity department;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MANAGER_ID")
	private EmployeeEntity manager;

	@Column(name = "SKYPE_NAME", unique = true, nullable = false)
	@NotNull
	private String skypeName;

	@Column(name = "FIRST_NAME", nullable = false, length = 20)
	private String firstName;

	@Column(name ="MIDDLE_INITIAL", length = 1)
	private Character middleInitial;

	@Column(name = "LAST_NAME", nullable = false, length = 45)
	private String lastName;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "IS_MANAGER", nullable = false)
	private Boolean isManager = false;

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

}