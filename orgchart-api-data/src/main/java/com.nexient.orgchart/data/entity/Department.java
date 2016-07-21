package com.nexient.orgchart.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import static javax.swing.text.StyleConstants.Size;

@Entity
@Table(name = "DEPARTMENT")
public class Department extends BaseEntity {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MANAGER_ID")
	private Employee manager;

	@Column(name = "NAME", nullable = false, length = 50, unique = true)
	@NotNull
	@NotEmpty
	@Size(min = 1, max = 45)
	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PARENT_DEPARTMENT_ID", referencedColumnName = "ID")
	private Department parentDepartment;

	public String getName() {
		return this.name;
	}

	public Department getParentDepartment() {
		return this.parentDepartment;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setParentDepartment(Department department) {
		this.parentDepartment = department;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}
}