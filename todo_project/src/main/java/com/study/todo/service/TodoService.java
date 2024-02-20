package com.study.todo.service;

import com.study.todo.dao.TodoDao;
import com.study.todo.vo.TodoVo;

public class TodoService {
	
	private static TodoService instance;
	private static TodoDao todoDao;
	private static TodoVo todoVo;
	
	private TodoService() {
		todoDao = TodoDao.getInstance();
	}
	
	public static TodoService getInstance() {
		if(instance == null) {
			instance = new TodoService();
		}
		return instance;
	}
}
