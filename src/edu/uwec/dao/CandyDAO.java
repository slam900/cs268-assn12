package edu.uwec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.uwec.model.CandyProduct;

public class CandyDAO {
	public List<CandyProduct> getProducts() throws SQLException {
		ArrayList<CandyProduct> products = new ArrayList<CandyProduct>();
		Connection conn = DarioMySQLConnectionManager.getConnection();
		Statement stmt = conn.createStatement();
		String sql = "SELECT prod_id, prod_desc, prod_price FROM candy_product ORDER BY prod_desc";
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
			products.add(new CandyProduct(rs.getInt("prod_id"), rs.getString("prod_desc"), rs.getDouble("prod_price")));
		
		rs.close();
		stmt.close();
		
		return products;
	}
	
	public CandyProduct getProduct(int id) throws SQLException {
		List<CandyProduct> products = getProducts();
		
		for (CandyProduct product : products)
			if (product.getId() == id)
				return product;
				
		return null;
	}
	
	public boolean updateProduct(CandyProduct product) throws SQLException {
		List<CandyProduct> products = getProducts();

		for (CandyProduct p : products) {
			if (p.getId() == product.getId()) {
				Connection conn = DarioMySQLConnectionManager.getConnection();
				String sql = "UPDATE candy_product SET prod_desc = ?, prod_price = ? WHERE prod_id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, product.getDescription());
				pstmt.setDouble(2, product.getPrice());
				pstmt.setInt(3, product.getId());
				int updatedRows = pstmt.executeUpdate();
				
				return updatedRows > 0;
			}
		}
		return false;
	}
	
	public boolean addProduct(CandyProduct product) throws SQLException {
		List<CandyProduct> products = getProducts();

		for (CandyProduct p : products)
			if (p.getId() == product.getId())
				return false;
			
		Connection conn = DarioMySQLConnectionManager.getConnection();
		String sql = "INSERT INTO candy_product VALUES(?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);

		pstmt.setInt(1, product.getId());
		pstmt.setString(2, product.getDescription());
		pstmt.setDouble(3, product.getPrice());
		pstmt.setDouble(4, product.getPrice());
		int updatedRows = pstmt.executeUpdate();
		
		return updatedRows > 0;
	}
	
	public boolean deleteProduct(int id) throws SQLException {
		List<CandyProduct> products = getProducts();
		
		for (CandyProduct product : products) {
			if (product.getId() == id) {
				Connection conn = DarioMySQLConnectionManager.getConnection();
				String sql = "DELETE FROM candy_product WHERE prod_id = ?";
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, id);
				int updatedRows = pstmt.executeUpdate();
				
				return updatedRows > 0;
			}
		}
		return false;
	}
}
