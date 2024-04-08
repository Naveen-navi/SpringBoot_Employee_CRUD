package com.Employee_CRUD.rest;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Employee_CRUD.dao.EmployeeDAO;
import com.Employee_CRUD.entity.Employee;
import com.Employee_CRUD.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
	
	private EmployeeService employeeService;
	
	public EmployeeRestController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	
	//Get single employee data
	
	@GetMapping("/employees/{employeeId}")
	public Employee getEmployee(@PathVariable int employeeId) {
		Employee theEmployee = employeeService.findById(employeeId);
		
		if(theEmployee == null) {
			throw new RuntimeException("No data Found -"  +employeeId);
		}
		
		return theEmployee;
	}
	
	//Now we need to save data to DB so we use "POST" method
	
	@PostMapping("/employees")
	public Employee addEmployee(@RequestBody Employee theEmployee) {
		
		//Also add incase they pass the id in JSOn,,, set the id to 0
		// This is to force a save of new item
		
		theEmployee.setId(0);
		Employee dbEmployee = employeeService.save(theEmployee);
		
		return dbEmployee;
	}
	
	// Now lets update data by using PUT mapping
	@PutMapping("/employees")
	public Employee updateEmployee(@RequestBody Employee theEmployee) {
		Employee dbEmployee = employeeService.save(theEmployee);
		
		return dbEmployee;
	}
	
	//Delete the data from the DB using DELETE mapping
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmployee(@PathVariable int employeeId) {
		Employee tempEmployee = employeeService.findById(employeeId);
		
		if(tempEmployee == null) {
			throw new RuntimeException("No data found - " +employeeId);
		}
		
		employeeService.deleteById(employeeId);
		
		return "Deleted Employee ID - " +employeeId;
	}
}
