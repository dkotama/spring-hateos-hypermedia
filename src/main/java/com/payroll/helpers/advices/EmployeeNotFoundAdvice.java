package com.payroll.helpers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.payroll.helpers.exceptions.EmployeeNotFoundException;

@ControllerAdvice
class EmployeeNotFoundAdvice {
	@ResponseBody // Write this advice to response body
	@ExceptionHandler(EmployeeNotFoundException.class) // only if EmployeeNotFoundTriggered
	@ResponseStatus(HttpStatus.NOT_FOUND) // return 404
	String employeeNotFoundHandler(EmployeeNotFoundException ex) {
		return ex.getMessage();
	}
	
}
