package com.parshuram.inventoryservice.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.parshuram.inventoryservice.binding.EmployeeDto;
import com.parshuram.inventoryservice.constants.AppConstants;
import com.parshuram.inventoryservice.entity.Employee;
import com.parshuram.inventoryservice.exception.ResourceNotFoundException;
import com.parshuram.inventoryservice.repository.EmployeeRepository;
import com.parshuram.inventoryservice.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	private Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee createEmployee(EmployeeDto employeeDto) {

		var employee = new Employee();

		logger.info("Employee Dto Data {} ", employeeDto);

		BeanUtils.copyProperties(employeeDto, employee);

		logger.info("Employee Dto Converted into Employee {}", employee);

		return employeeRepository.save(employee);

	}

	@Override
	public Employee updateEmployee(EmployeeDto employeeDto, Long id) {

		var employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.EMPLOYEE_ID, id));

		logger.info("Employee Details for id" +id);

		logger.info("Employee Details with that Id {} is Found ", employee);

		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setCity(employeeDto.getCity());
		employee.setSalary(employeeDto.getSalary());

		logger.info("Updated Employee Details {} ", employee);

		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployeeDetails() {

		List<Employee> listOFEmployee = employeeRepository.findAll();

		logger.info("List of All {} Employees...", listOFEmployee);

		return listOFEmployee;
	}

	@Override
	public void deleteEmployee(Long id) {

		if (employeeRepository.existsById(id)) {

			logger.info("Employee is Found with" + id);

			employeeRepository.deleteById(id);
		} else {

			logger.info("Exception Thrown..." + id + " Not Found");

			throw new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.EMPLOYEE_ID, id);
		}

	}

	@Override
	public List<Employee> getEmployeeByCity(String city) {

		var employee = employeeRepository.findByCity(city);
		
		logger.info("Employee With city" + city);

		logger.info("Employee Details {} found entered city is", employee);

		if (employee == null) {

			logger.info("Exception Thrown" + city + " Not Found");

			throw new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.CITY, city);
		}

		return employee;
	}

	@Override
	public Employee getEmployeeByName(String firstName) {

		Optional<Employee> findByFirstName = employeeRepository.findByFirstName(firstName);

		logger.info("Employee Finding with " + firstName);
		
		if (findByFirstName.isPresent()) {

			Employee employee = findByFirstName.get();

			logger.info("Employee Details get with Name {}", employee);

			return employee;
		} else {

			logger.info("Exception Thrown.." + firstName + " Not found");

			throw new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.FIRSTNAME, firstName);
		}

	}

	@Override
	public List<Employee> getEmployeeByLastName(String lastName) {

		List<Employee> listOfEmployees = employeeRepository.findAll();

		logger.info("All Employee Details {} ", listOfEmployees);

		List<Employee> empWithLastName = listOfEmployees.stream()
				.filter(emp -> emp.getLastName().equalsIgnoreCase(lastName)).collect(Collectors.toList());

		logger.info("Employee Finding with LastName" + lastName);

		if (empWithLastName.isEmpty()) {

			throw new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.LASTNAME, lastName);
		}

		logger.info("Employee Data With lastName  {} ", empWithLastName);

		return empWithLastName;
	}

	@Override
	public Employee getEmployeeById(Long id) {

		var employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(AppConstants.EMPLOYEE, AppConstants.EMPLOYEE_ID, id));

		logger.info("Employee data with id" + id);

		logger.info("Employee Details {} ", employee);

		return employee;
	}

}
