package com.parshuram.inventoryservice.service;

import java.util.List;

import com.parshuram.inventoryservice.binding.EmployeeDto;
import com.parshuram.inventoryservice.entity.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(EmployeeDto employeeDto);
	
	public Employee updateEmployee(EmployeeDto employeeDto,Long id);
	
	public List<Employee> getAllEmployeeDetails();
	
	public void deleteEmployee(Long id);
	
	public List<Employee> getEmployeeByCity(String city);

	public Employee getEmployeeByName(String firstName);
	
	public List<Employee> getEmployeeByLastName(String lastName);
	
	public Employee getEmployeeById(Long id);
	
}
