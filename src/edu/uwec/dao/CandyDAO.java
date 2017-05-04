package edu.uwec.dao;

import java.sql.Connection;
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
}
