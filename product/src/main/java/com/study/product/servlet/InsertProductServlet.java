package com.study.product.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.study.product.dao.ProductDao;
import com.study.product.entity.Product;

@WebServlet("/product")
public class InsertProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InsertProductServlet() {
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
		
		Product product = gson.fromJson(builder.toString(), Product.class);
		System.out.println(product);
		System.out.println(product.getName());
		System.out.println(product.getPrice());
		System.out.println(product.getSize());
		
		ProductDao productDao = ProductDao.getInstance();
		
		Product findProduct = productDao.findProductByName(product.getName());
		
		if(findProduct != null) {
			Map<String, Object> errorMap = new HashMap<>();
			errorMap.put("errorMessage", "이미 등록된 이름입니다");
			
			response.setStatus(400);
			response.setContentType("application/json");
			response.getWriter().println(gson.toJson(errorMap));
			return;
		}
		
		int successCount = productDao.saveProduct(product);
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("status", 201);
		responseMap.put("data", "응답데이터");
		responseMap.put("successCount", successCount);
		
		response.setStatus(201);
		response.setContentType("application/json");
		
		PrintWriter writer = response.getWriter();
		writer.println(gson.toJson(responseMap));
	}

}
