package com.study.todo.vo;

import com.study.todo.dto.InsertTodoResDto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TodoVo {
	private int todoId;
	private int todoMonth;
	private int todoDay;
	private String todoContent;
	
	public InsertTodoResDto toInsertDto(int successCount) {
		return InsertTodoResDto.builder()
				.successCount(successCount)
				.todoInt(todoId)
				.todoMonth(todoMonth)
				.todoDay(todoDay)
				.todoContent(todoContent)
				.build();
	}
}
