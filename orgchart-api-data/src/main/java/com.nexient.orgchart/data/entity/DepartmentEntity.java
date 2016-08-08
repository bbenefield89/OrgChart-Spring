package com.nexient.orgchart.data.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "department")
public class DepartmentEntity extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "manager_id")
	private EmployeeEntity manager;

	@Column(name = "name", nullable = false, length = 50, unique = true)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_department_id", referencedColumnName = "ID")
	private DepartmentEntity parentDepartment;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parentDepartment")
	private Set<DepartmentEntity> departments = new HashSet<>(0);

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	private Set<EmployeeEntity> employees = new HashSet<>(0);

	public String getName() {
		return this.name;
	}

	public DepartmentEntity getParentDepartment() {
		return this.parentDepartment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentDepartment(DepartmentEntity departmentEntity) {
		this.parentDepartment = departmentEntity;
	}

	public EmployeeEntity getManager() {
		return manager;
	}

	public void setManager(EmployeeEntity manager) {
		this.manager = manager;
	}

    public Set<DepartmentEntity> getChildDepartments() {
        return departments;
    }

    public void setChildDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }

    public Set<EmployeeEntity> getDepartmentEmployees() {
        return employees;
    }

    public void setDepartmentEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }
}