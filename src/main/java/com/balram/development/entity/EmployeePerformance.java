package com.balram.development.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.data.domain.Persistable;

import java.io.Serializable;

@Entity
@Table(name = "EMPLOYEE_PERFORMANCE")
@Data
public class EmployeePerformance implements Persistable<Long>, Serializable {

	private static final long serialVersionUID = 1L;

	private @Id
	@GeneratedValue Long id;
	private String name;
	private String role;

	public EmployeePerformance(String name, String role) {
		this.name = name;
		this.role = role;
	}

	// Getters and setters

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id == null;
	}
}