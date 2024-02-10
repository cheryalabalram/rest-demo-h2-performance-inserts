package com.balram.development.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Id
	@GeneratedValue Long id;
	private String name;
	private String role;

	Employee() {
	}

	public Employee(String name, String role) {

		this.name = name;
		this.role = role;
	}
}