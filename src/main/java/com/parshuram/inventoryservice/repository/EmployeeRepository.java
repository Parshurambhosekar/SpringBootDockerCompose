package com.parshuram.inventoryservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parshuram.inventoryservice.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByCity(String city);
	
	Optional<Employee> findByFirstName(String firstName);
	
}
