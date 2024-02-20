package com.study.todo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.todo.config.DBConnectionMgr;
import com.study.todo.vo.TodoVo;

public class TodoDao {
	
	private static TodoDao instance;
	private DBConnectionMgr pool;
	
	private TodoDao() {
		
	}
	
	public static TodoDao getInstance() {
		if(instance == null) {
			instance = new TodoDao();
		}
		return instance;
	}
	
	public List<TodoVo> getTodoList() {
		List<TodoVo> list = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "Select * from todo_tb";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				TodoVo todoVo = TodoVo.builder()
						.todoId(rs.getInt(1))
						.todoMonth(rs.getInt(2))
						.todoDay(rs.getInt(3))
						.todoContent(rs.getString(4))
						.build();
				
				list.add(todoVo);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return list;
	}
}
