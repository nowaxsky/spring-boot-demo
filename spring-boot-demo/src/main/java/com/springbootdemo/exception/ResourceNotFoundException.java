package com.springbootdemo.exception;

public class ResourceNotFoundException extends Exception/*RuntimeException*/ {
	
	private String resourceId;

	public ResourceNotFoundException(String resourceId, String message) {
		super(message);
		this.resourceId = resourceId;
	}

}
