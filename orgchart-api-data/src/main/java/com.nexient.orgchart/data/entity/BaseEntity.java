package com.nexient.orgchart.data.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by kskronek on 5/24/2016.
 */
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Keep this here for future reference (just in case)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "is_active", columnDefinition="boolean default true")
	private Boolean isActive;

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean active) {
		isActive = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
