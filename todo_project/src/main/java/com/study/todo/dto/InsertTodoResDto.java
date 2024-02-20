package com.study.todo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class InsertTodoResDto {
	
	private int successCount;
	private int todoInt;
	private int todoMonth;
	private int todoDay;
	private String todoContent;
}
