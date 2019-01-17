package com.nexient.orgchart.data.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "job_title")
public class JobTitleEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    @NotEmpty
    private String name;

    @OneToMany
    @JoinColumn(name = "employee_id")
    private Set<EmployeeEntity> titleEmployees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<EmployeeEntity> getTitleEmployees() {
        return titleEmployees;
    }

    public void setTitleEmployees(Set<EmployeeEntity> titleEmployees) {
        this.titleEmployees = titleEmployees;
    }

}