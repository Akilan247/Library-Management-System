package library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	
	private String url = "jdbc:mysql://localhost:3306/Books";
	private String username = "root";
	private String password = "Akilan#24710";
	
	public Connection getconnection() throws SQLException {
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
	}
	
}
