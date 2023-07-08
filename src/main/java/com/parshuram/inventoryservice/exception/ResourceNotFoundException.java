package com.parshuram.inventoryservice.exception;

import com.parshuram.inventoryservice.constants.AppConstants;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String resourceName;
	String fieldName;
	Long fieldValue;
	String name;
	
	public ResourceNotFoundException(String resourceName,String fieldName,Long fieldValue) {
		super(String.format(AppConstants.EXCEPTION_MESSAGE,resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
	public ResourceNotFoundException(String resourceName,String fieldName,String name) {
		super(String.format(AppConstants.EXCEPTION_MESSAGE,resourceName,fieldName,name));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.name=name;
	}

}
