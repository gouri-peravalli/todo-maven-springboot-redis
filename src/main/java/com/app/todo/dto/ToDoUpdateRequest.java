package com.app.todo.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.app.todo.entity.TaskState;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "bind")
@Data
public class ToDoUpdateRequest {
	
	@NotNull(message="Description mandatory")
	@NotBlank
	private String description;
	
	@NotNull(message="dueDate mandatory")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate  dueDate;
	
	@NotNull(message="TaskStatus mandatory")
	private TaskState  state;
	

}
