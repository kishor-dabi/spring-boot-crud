package com.test.demo.exception;

public class CustomeException extends Exception {

	int code;
	String message;
	
	public CustomeException(int i, String message) {
		this.code = i;
		this.message = message;
	}
	
}
