package library;

import java.sql.SQLException;
import java.util.*;
import java.util.Scanner;

class Beginning{
	int choice;
	Scanner sc = new Scanner(System.in);
    Book book = new Book();
    User user = new User();
    
	public void starting() throws SQLException {
		
		System.out.println("\n\t\t\t\t*******LIBRARY MANAGEMENT SYSTEM*******");
		
		while(true) {
			
			System.out.println("\t\t--------------------------------------------------------------------------------------------------------------");
			System.out.println("\n\t\t1.Login"
					+ "\n\t\t2.New user");
			System.out.print("\t\tEnter your choice: ");
			int choice = sc.nextInt();
			switch(choice) {
			case 1:
				if(user.login()) {
					
					userOperation op = new userOperation();
					op.useroperation();
					break;
					
				}else {
					System.out.println("\t\ttry again");
				}
				
				break;
			 case 2:
				user.newUser();
				break;
			}
		}
	}
}

public class Demo {
	
	static Scanner getinput = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {
		
		Beginning beginning = new Beginning();
		beginning.starting();
		
		
		
	}
	
}
