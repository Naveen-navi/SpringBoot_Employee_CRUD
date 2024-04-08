package com.Employee_CRUD_Spring_Data_JPA.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee_CRUD_Spring_Data_JPA.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
