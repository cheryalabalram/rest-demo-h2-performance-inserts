package com.balram.development.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private @Id @GeneratedValue Long id;
	private String name;
	private String role;

	Employee() {
	}

	public Employee(String name, String role) {

		this.name = name;
		this.role = role;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRole() {
		return this.role;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
	}
}