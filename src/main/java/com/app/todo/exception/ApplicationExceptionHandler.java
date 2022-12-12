package com.app.todo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

	public ApplicationExceptionHandler() {
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvaildArgs(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();

		ex.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		return errorMap;

	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleInvaliduser(UserNotFoundException ex) {
		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("errorMessage", ex.getMessage());

		return errorMap;

	}
	
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DuplicateRecordFound.class)
	public Map<String, String> handleInvaliduser(DuplicateRecordFound ex) {
		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("errorMessage", ex.getMessage());

		return errorMap;

	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public Map<String, String> handleInvaliduser(HttpMessageNotReadableException ex) {
		Map<String, String> errorMap = new HashMap<>();

		errorMap.put("errorMessage", ex.getMessage());

		return errorMap;

	}
	
	

}
