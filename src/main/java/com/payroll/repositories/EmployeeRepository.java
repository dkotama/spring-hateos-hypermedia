package com.payroll.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payroll.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
