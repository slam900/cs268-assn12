package edu.uwec.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DarioMySQLConnectionManager {
	//Single connection provided to clients
	private static Connection conn;
		
	//Called by clients to obtain the single database connection
	public static Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/dariomysql");
				conn = ds.getConnection();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
