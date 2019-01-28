package com.nexient.orgchart.data.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by kskronek on 5/24/2016.
 */
@MappedSuperclass
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

	@PrePersist
	public void preInsert() {
		if (isActive == null) {
			isActive = true;
		}
	}

}
