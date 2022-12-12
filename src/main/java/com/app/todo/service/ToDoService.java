package com.app.todo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.todo.dto.ToDoCreateRequest;
import com.app.todo.dto.ToDoUpdateRequest;
import com.app.todo.entity.ToDo;
import com.app.todo.exception.DuplicateRecordFound;
import com.app.todo.exception.UserNotFoundException;
import com.app.todo.repository.ToDoRepository;

import jakarta.validation.Valid;

@Service
public class ToDoService {

	@Autowired
	private ToDoRepository todoRepository;

	public List<ToDo> findAll() {
		List<ToDo> todos = new ArrayList<>();
		todoRepository.getAll().forEach((k, v) -> todos.add(v));
		return todos;
	}

	public List<ToDo> findAllByUserId(String userId) {
		List<ToDo> todos = new ArrayList<>();
		todoRepository.getAll().forEach((k, v) -> todos.add(v));
		return todos.stream().filter(e -> String.valueOf(e.getUserId()).equalsIgnoreCase(userId)).collect(Collectors.toList());
	}

	public ToDo findById(String key) throws UserNotFoundException {
		var todoExists = todoRepository.get(key);
		if (todoExists != null) {
			return todoExists;
		} else {
			throw new UserNotFoundException("user id not present");
		}
	}

	public ToDo save(ToDoCreateRequest toDoRequest) throws DuplicateRecordFound {
		var todos = findAll();
		var maxTaskId = 0;
		if (!todos.isEmpty()) {
			maxTaskId = todos.stream().map(data -> data.getTaskId()).max(Integer::compare).get();
		}
		var toDo = ToDo.bind(toDoRequest.getUserId(), maxTaskId + 1, toDoRequest.getDescription(),
				toDoRequest.getDueDate(), toDoRequest.getState());

		var dataPresent = todos.stream().filter(data -> data.equals(toDo)).findAny();

		if (!dataPresent.isPresent()) {
			return todoRepository.create(toDo);
		} else {
			throw new DuplicateRecordFound("duplicate Task found");
		}

	}

	public ToDo update(String userIdTaskId, @Valid ToDoUpdateRequest doUpdateRequest)
			throws UserNotFoundException, DuplicateRecordFound {

		String[] strArray = userIdTaskId.split("_");

		var todoExists = todoRepository.get(userIdTaskId);

		if (todoExists != null) {

			var todoExistsByTaskId = findAll().stream().filter(data -> data.getTaskId().equals(Integer.valueOf(strArray[1])))
					.findAny();

			if (todoExistsByTaskId.isPresent()) {

				var toDo = ToDo.bind(Integer.valueOf(strArray[0]), Integer.valueOf(strArray[1]),
						doUpdateRequest.getDescription(), doUpdateRequest.getDueDate(), doUpdateRequest.getState());

				if (todoExistsByTaskId.get().equals(toDo)) {
					throw new DuplicateRecordFound("No changes found to update");
				}
				return todoRepository.update(toDo);
			} else {
				throw new DuplicateRecordFound("Task id not found to update Record");
			}

		} else {
			throw new UserNotFoundException("user id not present");
		}
	}

	public void deleteById(String userIdTaskId) throws UserNotFoundException {
		var todoExists = todoRepository.get(userIdTaskId);
		if (todoExists != null) {
			todoRepository.delete(userIdTaskId);
		} else {
			throw new UserNotFoundException("user id not present");
		}

	}

}
