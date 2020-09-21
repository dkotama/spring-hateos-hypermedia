package com.payroll.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.payroll.ApplicationRoutePath;
import com.payroll.controllers.EmployeeController;
import com.payroll.entities.Employee;

@Component // spring boot convention to create class when application start
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>>{

	@Override
	public EntityModel<Employee> toModel(Employee employee) {
		return EntityModel.of(employee, 
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getOne(employee.getId())).withSelfRel(), // eclipse not auto importing WebMvcLinkBuilder.linkto()
				WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).all()).withRel(ApplicationRoutePath.EMPLOYEE));

	}
	
}
