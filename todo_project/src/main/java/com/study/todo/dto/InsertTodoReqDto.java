package com.study.todo.dto;

import com.study.todo.vo.TodoVo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertTodoReqDto {
	
	private int todoMonth;
	private int todoDay;
	private String todoContent;
	
	public TodoVo toVo() {
		return TodoVo.builder()
				.todoMonth(todoMonth)
				.todoDay(todoDay)
				.todoContent(todoContent)
				.build();
	}
}
