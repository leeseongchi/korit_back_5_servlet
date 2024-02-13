package after_study.connect.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import after_study.connect.entity.Student;

@WebServlet("/data/addition")
public class DataInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DataInsertServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StringBuilder builder = new StringBuilder();
		
		String readData = null;
		
		BufferedReader reader = request.getReader();
		
		while((readData = reader.readLine()) != null) {
			builder.append(readData);
		}
		
		Gson gson = new Gson();
		Student student = gson.fromJson(builder.toString(), Student.class);
		
		Connection con = null;
		String sql = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://mysql-db.cfyce4k2m3rb.ap-northeast-2.rds.amazonaws.com/db_study";
			String username = "aws";
			String password = "1q2w3e4r!!";		
			
			con = DriverManager.getConnection(url, username, password);
			sql = "insert into student_tb(student_name, student_age) values(? , ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, student.getName());
			pstmt.setInt(2, student.getAge());
			successCount = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {					
					pstmt.close();
				}
				if(con != null) {					
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
	
	}

}
