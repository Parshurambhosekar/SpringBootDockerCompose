package com.parshuram.inventoryservice.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.parshuram.inventoryservice.binding.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
		
			String message = exception.getMessage();
			
			ApiResponse response=new ApiResponse(message);
		
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgNotValidException(MethodArgumentNotValidException exception){
		
		Map<String,String> rsp=new HashMap<>();
		
		exception.getAllErrors().forEach(errors->{
			
			String field = ((FieldError)errors).getField();
			
			String message = errors.getDefaultMessage();
			
			rsp.put(field, message);
		});
		
		return new ResponseEntity<>(rsp, HttpStatus.BAD_REQUEST);
	}
	
	
	

}
