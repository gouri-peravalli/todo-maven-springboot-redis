package com.app.todo.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.app.todo.entity.ToDo;

@Repository
public class ToDoRepository {

	private HashOperations<String, String, ToDo> hashOperations;

	public ToDoRepository(RedisTemplate<String, ToDo> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	public ToDo create(ToDo todo) {
		hashOperations.putIfAbsent("ToDo", todo.getUserId() + "_" + todo.getTaskId(), todo);
		return todo;
	}

	public ToDo get(String key) {
		return (ToDo) hashOperations.get("ToDo", key);
	}

	public Map<String, ToDo> getAll() {
		return hashOperations.entries("ToDo");
	}

	public ToDo update(ToDo todo) {
		hashOperations.put("ToDo", todo.getUserId() + "_" + todo.getTaskId(), todo);
		return todo;
	}

	public void delete(String userId) {
		hashOperations.delete("ToDo", userId);
	}
}
