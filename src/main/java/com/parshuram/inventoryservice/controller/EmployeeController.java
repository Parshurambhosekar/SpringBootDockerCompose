package com.parshuram.inventoryservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parshuram.inventoryservice.binding.ApiResponse;
import com.parshuram.inventoryservice.binding.EmployeeDto;
import com.parshuram.inventoryservice.constants.AppConstants;
import com.parshuram.inventoryservice.entity.Employee;
import com.parshuram.inventoryservice.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/")
	public ResponseEntity<Employee> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
		
		log.info("Employee Dto Data from URL {} ",employeeDto);
		
		Employee createEmployee = employeeService.createEmployee(employeeDto);
		
		log.info("Employee Controller rsp from Service {} ",createEmployee);
		
		return new ResponseEntity<>(createEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@Valid @RequestBody EmployeeDto employeeDto,@PathVariable Long id){
		
		log.info("Employee Updated Based On The Id...."+id);
		
		log.info("Employee Dto Data from UR {} ",employeeDto);
		
		Employee updateEmployee = employeeService.updateEmployee(employeeDto, id);
		
		log.info("Employee Controller rsp from Service {} with updated Employee",updateEmployee);
		
		return new ResponseEntity<>(updateEmployee, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Employee>> getListOfAllEmployees(){
		
		List<Employee> allEmployeeDetails = employeeService.getAllEmployeeDetails();
		
		log.info("All Employee Details {} controller",allEmployeeDetails);
		
		return new ResponseEntity<>(allEmployeeDetails, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteReponse(@PathVariable Long id){
		
		log.info("Controller delete method() "+id);
		
		employeeService.deleteEmployee(id);
		
		return new ResponseEntity<>(new ApiResponse(AppConstants.MESSAGE), HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Employee>> getEmployeeByCity(@PathVariable String city){
		
		log.info("controller method() city"+city);
		
		List<Employee> employeeByCity = employeeService.getEmployeeByCity(city);
		
		log.info("Employee Data by City Controller Method() {} ",employeeByCity);
		
		return new ResponseEntity<>(employeeByCity, HttpStatus.FOUND);
	}
	
	@GetMapping("/firstname/{name}")
	public ResponseEntity<Employee> getEmployeeByFirstName(@PathVariable(name = "name") String firstName) {
		
		log.info("Controller method() FirstName"+firstName);
		
		Employee employeeByName = employeeService.getEmployeeByName(firstName);
		
		log.info("Employee Data By FirstName Controller Method() {} ",employeeByName);
		
		return new ResponseEntity<>(employeeByName, HttpStatus.OK);
	}
	
	@GetMapping("/lastname/{name}")
	public ResponseEntity<List<Employee>> getEmployeeByLastName(@PathVariable(name = "name") String lastName){
		
		log.info("Controller method() LastName"+lastName);
		
		List<Employee> employeeByLastName = employeeService.getEmployeeByLastName(lastName);
		
		log.info("Employee Data By LastName Controller Method() {} ",employeeByLastName);
		
		return new ResponseEntity<>(employeeByLastName, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		
		log.info("Controller Method() Id"+id);
		
		Employee employeeById = employeeService.getEmployeeById(id);
		
		log.info("Employee Data By Id Controller Method() {} ",employeeById);
		
		return new ResponseEntity<>(employeeById, HttpStatus.FOUND);
		
	}
	
	
	
}
