package com.study.product.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.product.config.DBConfig;
import com.study.product.entity.Product;

public class ProductDao {
	
	private static ProductDao instance;
	
	private ProductDao() {
		
	}
	
	public static ProductDao getInstance() {
		if(instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}
	
	public Product findProductByName(String name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Product product = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String url = "select * from product_tb where product_name = ?";
			pstmt = con.prepareStatement(url);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = Product.builder()
						.productId(rs.getInt(1))
						.name(rs.getString(2))
						.price(rs.getInt(3))
						.size(rs.getString(4))
						.build();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return product;
	}
	
	public int saveProduct(Product product) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int successCount = 0;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String url = "insert into product_tb(product_name, product_price, product_size) values(?, ?, ?)";
			pstmt = con.prepareStatement(url);
			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getSize());
			successCount = pstmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return successCount;
	}

	public List<Product> getProductListAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Product> products = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con = DriverManager.getConnection(DBConfig.URL, DBConfig.USERNAME, DBConfig.PASSWORD);
			String url = "select * from product_tb";
			pstmt = con.prepareStatement(url);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Product product = Product.builder()
						.productId(rs.getInt(1))
						.name(rs.getString(2))
						.price(rs.getInt(3))
						.size(rs.getString(4))
						.build();
				
				products.add(product);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con != null) {
					con.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return products;
	}
}
