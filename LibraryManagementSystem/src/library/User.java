package library;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;

import library.Demo;

public class User {
	private String username;
	private String password;
	private String number;
	
	Demo d = new Demo();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public boolean login() throws SQLException {
		System.out.println("\n\t\tEnter the details for login");
		System.out.print("\n\t\tEnter the user name : ");
		setUsername(d.getinput.nextLine());
		
		System.out.print("\n\t\tEnter Your Password: ");
		setPassword(d.getinput.nextLine());
		
		 return userCheck(this.username,this.password);

	}
	
	public boolean userCheck(String username,String password) throws SQLException {
		
		String realPassword = "";
		String query = " select pass from user where username = ?";
		
		GetConnection connection = new GetConnection();
		Connection conn = connection.getconnection();
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1,username);
		ResultSet rst = st.executeQuery();
		
		if(rst.next()) {
			
			  realPassword = rst.getString(1);
			  
			  if(realPassword.equalsIgnoreCase(password)) {
				  return true;
			  }else {
				  System.out.println("\t\t=>wrong password..");
				  
			  }
				
			}
		return false;
	}
	

	public void newUser() throws SQLException {

		System.out.println("\t\tCreate your Account");

		System.out.print("\n\t\tEnter the user name : ");
		setUsername(d.getinput.nextLine());

		System.out.print("\n\t\tEnter Your Password: ");
		setPassword(d.getinput.nextLine());

		insertUserDetails(getUsername(), getPassword());

	}
	
	public void insertUserDetails(String username, String password) throws SQLException {

		String query = "insert into user values(?,?)";

		GetConnection connection = new GetConnection();
		Connection conn = connection.getconnection();
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, username);
		st.setString(2, password);
		int row = st.executeUpdate();

		if (row == 1) {
			System.out.println("\t\tAccount successfully created!!!!");
		} else {
			System.out.println("\t\tTry again...");
		}

	}
}
