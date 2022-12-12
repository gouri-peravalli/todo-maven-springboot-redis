package com.app.todo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@RedisHash(value = "ToDo")
@AllArgsConstructor(staticName = "bind")
@NoArgsConstructor
public class ToDo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Indexed
	private Integer userId;

	@Id
	private Integer taskId;
	
	private String description;
	
	private LocalDate  dueDate;
	
	private TaskState  state;
	

	@Override
	public String toString() {
		return "TODO [taskId=" + taskId + ", userId=" + userId + ", description=" + description + ", dueDate=" + dueDate
				+ ", state=" + state + "]";
	}



	public ToDo(Integer taskId, String description, LocalDate dueDate, TaskState state) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.dueDate = dueDate;
		this.state = state;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ToDo other = (ToDo) obj;
		return Objects.equals(description, other.description) && Objects.equals(dueDate, other.dueDate)
				&& state == other.state && Objects.equals(userId, other.userId) ;
	}



	@Override
	public int hashCode() {
		return Objects.hash(description, dueDate, state, userId);
	}
	
	
	
	
}
