package com.Employee_CRUD.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Employee_CRUD.entity.Employee;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{
	
	//Define field Name
	private EntityManager entityManager;
	
	
	@Autowired
	public EmployeeDAOImpl(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {
		// Create a query
		TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
				
		// Execute the Query
		List<Employee> employees = theQuery.getResultList();
				
		// Return
		return employees;
	}

	@Override
	public Employee findById(int theId) {
		//Get Employee
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//Return Employee
		return theEmployee;
	}

	@Override
	public Employee save(Employee theEmployee) {
		// Save or Update the employee
		Employee dbEmployee = entityManager.merge(theEmployee);
		return dbEmployee;
	}

	@Override
	public void deleteById(int theId) {
		//find the Employee Id
		
		Employee theEmployee = entityManager.find(Employee.class, theId);
		
		//Delete now
		entityManager.remove(theEmployee);
		
	}

}
