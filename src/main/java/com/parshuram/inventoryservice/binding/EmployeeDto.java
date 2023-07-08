package com.parshuram.inventoryservice.binding;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
	
	private Long id;
	@NotEmpty(message = "Employee FirstName is Mandatory")
	private String firstName;
	@NotEmpty(message = "Employee LastName is Mandatory")
	private String lastName;
	@NotEmpty(message = "Employee City is Required")
	private String city;
	@NotNull
	@Min(value = 25000,message = "Salary Must given minimum 25000")
	private Double salary;

}
