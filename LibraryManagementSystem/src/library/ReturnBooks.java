package library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBooks {
	Book book = new Book();
	
	public void insertToBookTable(int id,String bookname,String author,String category,String query) throws SQLException {
		GetConnection connection = new GetConnection();
        Connection conn = connection.getconnection();
        PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1,id);
		pst.setString(2, bookname);
		pst.setString(3,author);
		pst.setString(4, category);
	    int rows = pst.executeUpdate();
	    
	    System.out.println("\t\t"+rows + " row is affected");
		
		
		conn.close();
	}

	public String[] bookDetailToInsert(int id,String author,String category,String query) throws SQLException {
		GetConnection connection = new GetConnection();
		Connection conn = connection.getconnection();
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, id);
		ResultSet rst = pst.executeQuery();

		while (rst.next()) {
				 author = rst.getString("author_name");
				 category = rst.getString("category");
				 
				 return new String[]{author,category};
		}
		conn.close();
		return null;
		
	}
}
