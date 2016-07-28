package com.nexient.orgchart.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JOB_TITLE")
public class JobTitleEntity extends BaseEntity {

	@Column(name = "NAME", unique = true, nullable = false)
	@NotNull
	@NotEmpty
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "jobTitle")
	private Set<EmployeeEntity> employees = new HashSet<EmployeeEntity>(0);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmployeeEntity> getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set<EmployeeEntity> employees) {
		this.employees = employees;
	}
}