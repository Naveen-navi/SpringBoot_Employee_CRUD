package com.Employee_CRUD_Spring_Data_JPA.service;

import java.util.List;

import com.Employee_CRUD_Spring_Data_JPA.entity.Employee;

public interface EmployeeService {
	List<Employee> findAll();
	
	Employee findById(int theId);
	
	Employee save(Employee theEmployee);
	
	void deleteById(int theId);
}
