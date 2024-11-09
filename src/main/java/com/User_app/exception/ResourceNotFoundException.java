package com.User_app.exception;

public class ResourceNotFoundException extends RuntimeException {
	
//	long userId;
//	
//	public ResourceNotFoundException(long userId) {
//		super("user not found with this id : "+userId);
//		this.userId = userId;
//	}
	
	String fieldName;
	String resourceName;
	long fieldValue;
	
	public ResourceNotFoundException(String fieldName, String resourceName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName, fieldName, fieldValue));
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldValue = fieldValue;
	}

}
