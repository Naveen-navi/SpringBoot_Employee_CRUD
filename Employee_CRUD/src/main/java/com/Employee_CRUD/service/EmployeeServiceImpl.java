package com.Employee_CRUD.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Employee_CRUD.dao.EmployeeDAO;
import com.Employee_CRUD.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAO  employeeDAO;
	
	public EmployeeServiceImpl(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	@Override
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@Override
	public Employee findById(int theId) {
		// TODO Auto-generated method stub
		return employeeDAO.findById(theId);
	}
	
	@Transactional
	@Override
	public Employee save(Employee theEmployee) {
		return employeeDAO.save(theEmployee);
	}
	
	
	@Transactional
	@Override
	public void deleteById(int theId) {
		employeeDAO.deleteById(theId); 
		
	}

}
