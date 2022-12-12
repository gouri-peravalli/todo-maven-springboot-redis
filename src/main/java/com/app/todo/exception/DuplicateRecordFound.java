package com.app.todo.exception;

public class DuplicateRecordFound extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DuplicateRecordFound(String message) {
		super(message);
	}

}
