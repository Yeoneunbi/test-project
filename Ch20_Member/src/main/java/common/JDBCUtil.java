package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	public static Connection getConnection() {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "madang", "madang");
				
				return con;
			} catch (Exception e) {
				System.out.println("connection error");
			}
			return null;
	}
	
	public static void close(PreparedStatement stmt, Connection con) {

		try {
			if(stmt != null) {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			}
			
			if(con != null) {
				if(!con.isClosed()) {
					stmt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection close error");
		}
	}
	
	public static void close(PreparedStatement stmt, Connection con, ResultSet rs) {
	
		try {
			close(stmt,con);
			if(rs!=null) {
				if(!rs.isClosed()) {
					rs.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("Connection close error");
		}
	}

}
