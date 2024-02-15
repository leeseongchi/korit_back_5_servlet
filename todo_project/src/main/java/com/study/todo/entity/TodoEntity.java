package com.study.todo.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoEntity {
	private int todoId;
	private int todoMonth;
	private int todoDay;
	private int todoContent;
}
