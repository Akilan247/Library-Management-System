package library;

import java.sql.*;
import java.util.ArrayList;

public class Book implements BookAbstract {
	
	private String bookName;
	private String Author;
	private int bookId;
	String category;
	
	
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	private int getBookId() {
		return bookId;
	}
	public String getAuthor() {
		return Author;
	}
	public void setAuthor(String author) {
		Author = author;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public void booksAvailable() throws SQLException {
		
		String query = "select *from book_details";
		
		GetConnection connection = new GetConnection();
		Connection conn = connection.getconnection();
		Statement st = conn.createStatement();
		ResultSet rst = st.executeQuery(query);
		
		System.out.println("\t\t__________________________________________________________________________________________________________");
		System.out.println("\n\n\t\t\t\t   *****BOOK AVAILABLE*****");
		System.out.println("\t\t__________________________________________________________________________________________________________");
		System.out.println("\t\tBOOK_ID"+""+"\tBOOK NAME"+""+"\t\t\t\tAUTHOR NAME"+"   "+"\t\tCATEGORY");
		System.out.println("\t\t__________________________________________________________________________________________________________");
		while(rst.next()) {
			System.out.println("\t\t__________________________________________________________________________________________________________");
			System.out.println("\t\t" + rst.getInt(1)+" \t"+rst.getString(2));
			System.out.print("\t\t\t\t\t\t\t\t"+rst.getString(3)+"\t\t "+rst.getString(4));
			System.out.println();
		}
		System.out.println("\t\t__________________________________________________________________________________________________________");
		conn.close();
		
	}
	
	public void takingBook() throws SQLException {
		Demo d = new Demo();
		try {
			System.out.println("\t\t---------------------------------------------------------------------------------------------------------");
			System.out.print("\n\n\t\tEnter 'BOOK ID' you want: ");
			bookId = d.getinput.nextInt();
		}catch (Exception e) {
			System.out.println("please Enter the correct input type");
		}
		
		String[] data = retriveBook(getBookId());
		setAuthor(data[0]);
		category = data[1];
		
		String query = "delete from book_details where book_id = ?";
		GetConnection connection = new GetConnection ();
		Connection conn =  connection.getconnection();
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1,bookId);
		int row = pst.executeUpdate();
		
		if(row == 1) {
			System.out.println("");
			bookIssued(getBookId(),getAuthor(),category);
		}
		conn.close();
	}
	
	//Retrive the book details for updating "issued_book" Table
    public String[] retriveBook(int id) throws SQLException {
		
		
		String author = "";
		String category = "";
		String query = "select author_name,category from book_details where book_id = ?";
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
	
	public void bookIssued(int id,String author,String category) throws SQLException {
        
	    String query = "insert into issued_books values(?,?,?)";
	    
        GetConnection connection = new GetConnection();
        Connection conn = connection.getconnection();
        PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1,id);
		pst.setString(2,author);
		pst.setString(3, category);
	    int rows = pst.executeUpdate();
	    
	    if(rows == 1) {
	    	System.out.println("\n\t\tSuccessfull!!!..");
	    }
		
		
		conn.close();
	}
	
	
/*Returning the book when book returned by the user it will update in
 *  book_details table automatically
 * */	

	public void returnBook() throws SQLException {
		Demo d = new Demo();
		System.out.print("\n\t\tEnter Book Id: ");
		bookId = d.getinput.nextInt();
		setBookId(bookId);
		System.out.print("\n\t\tEnter Book Name: ");
		setBookName(d.getinput.next());
		
		String[] data = retriveBookDetail(getBookId()); //getting the data of 'Author and Category' from the 'issued' Tale
		setAuthor(data[0]);
		category = data[1];
		
		deleteReturnedBook(getBookId()); //Delete the data from issued Table
		
		addBook(getBookId(),getBookName(),getAuthor(),category); //Add the returned Book details in 'Book Table'
		
	}
	
	public void deleteReturnedBook(int bookId) throws SQLException {
		String query = "delete from issued_books where book_id = ?";
		GetConnection connection = new GetConnection ();
		Connection conn =  connection.getconnection();
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1,bookId);
		int row = pst.executeUpdate();
		conn.close();
		
	}
	public String[] retriveBookDetail(int id) throws SQLException {
		
		
		String author = "";
		String category = "";
		String query = "select author_name,category from issued_books where book_id = ?";
		
		ReturnBooks  returnBook= new ReturnBooks();
		//getting data from returned Book for Updating The "Book Table"
		return returnBook.bookDetailToInsert(id,author,category,query);
		
	}

	public void addBook(int id,String bookname,String author,String category) throws SQLException {
            
		    String query = "insert into book_details values(?,?,?,?)";
		    
		    ReturnBooks returnBook = new ReturnBooks();
		    returnBook.insertToBookTable(id,bookName,author,category,query);
            
		}
	}


