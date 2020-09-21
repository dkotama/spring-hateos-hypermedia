package com.payroll.entities;


import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Employee {
	private @Id @GeneratedValue Long id;
	private String name;
	private String role;
	
	
	Employee() {}
	
	public Employee(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this==o)
			return true;
		
		if (!(o instanceof Employee))
			return false;
		
		Employee employee = (Employee) o;
		
		return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name);
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.role);
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", role=" + role + "]";
	}
	
	
}