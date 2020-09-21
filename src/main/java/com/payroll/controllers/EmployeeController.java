package com.payroll.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payroll.ApplicationRoutePath;
import com.payroll.assemblers.EmployeeModelAssembler;
import com.payroll.entities.Employee;
import com.payroll.helpers.exceptions.EmployeeNotFoundException;
import com.payroll.repositories.EmployeeRepository;


@RestController
public class EmployeeController {
	private final EmployeeRepository repository;
	private final EmployeeModelAssembler assembler;
	private final String EMPLOYEE_PATH = ApplicationRoutePath.EMPLOYEE;
	
	public EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	// Aggregate Route
	@GetMapping("/"+EMPLOYEE_PATH)
	public CollectionModel<EntityModel<Employee>> all() {
		List<EntityModel<Employee>> employees = repository.findAll().stream()
				.map(assembler::toModel) // Java 8 method references
				.collect(Collectors.toList());
		
		return CollectionModel.of(employees,
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all()).withSelfRel());
	}
	
	@PostMapping("/"+EMPLOYEE_PATH)
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return repository.save(newEmployee);
	}
	
	// Single item
	@GetMapping("/" + EMPLOYEE_PATH + "/{id}")
	public EntityModel<Employee> getOne(@PathVariable Long id) {
		Employee employee = repository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException(id));
		
		return this.assembler.toModel(employee);
	}
	
	@PutMapping("/"+ EMPLOYEE_PATH +"/{id}")
	public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return repository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setRole(newEmployee.getRole());
					
					return repository.save(employee);
				})
				.orElseGet(() -> { // if not exist create
					newEmployee.setId(id);
					return repository.save(newEmployee);
				});
	}
	
}
