package com.app.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.dto.ToDoCreateRequest;
import com.app.todo.dto.ToDoUpdateRequest;
import com.app.todo.entity.ToDo;
import com.app.todo.exception.DuplicateRecordFound;
import com.app.todo.exception.UserNotFoundException;
import com.app.todo.service.ToDoService;

import jakarta.validation.Valid;

@RestController()
@RequestMapping("/todo")
public class ToDoController {

	@Autowired
	private ToDoService todoService;

	@GetMapping("/all")
	public ResponseEntity<List<ToDo>> getAll() {
		return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{userId_taskId}")
	public ResponseEntity<ToDo> getByUserAndTaskId(@PathVariable(name = "userId_taskId") String userIdTaskId)
			throws UserNotFoundException {
		return new ResponseEntity<>(todoService.findById(userIdTaskId), HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ToDo>> getByUserId(@PathVariable(name = "userId") String userId)
			throws UserNotFoundException {
		return new ResponseEntity<>(todoService.findAllByUserId(userId), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<ToDo> create(@RequestBody @Valid ToDoCreateRequest toDoRequest) throws DuplicateRecordFound {
		return new ResponseEntity<>(todoService.save(toDoRequest), HttpStatus.CREATED);
	}

	@PutMapping("/{userId_taskId}")
	public ResponseEntity<ToDo> updateById(@PathVariable(name = "userId_taskId") String userIdTaskId,
			@RequestBody @Valid ToDoUpdateRequest doUpdateRequest) throws UserNotFoundException, NumberFormatException, DuplicateRecordFound {
		return new ResponseEntity<>(todoService.update(userIdTaskId, doUpdateRequest), HttpStatus.CREATED);
	}

	@DeleteMapping("/{userId_taskId}")
	public ResponseEntity<String> deleteId(@PathVariable(name = "userId_taskId") String userIdTaskId) throws NumberFormatException, UserNotFoundException {
		todoService.deleteById(userIdTaskId);
		return new ResponseEntity<>("TODO with id:" + userIdTaskId + " deleted successfully", HttpStatus.OK);
	}
}