package library;

import java.sql.SQLException;
import java.util.Scanner;

public class userOperation {
	
	Scanner getinput = new Scanner(System.in);
	Book book = new Book();
	int choice;
	
	public void useroperation() throws SQLException {
		
		System.out.println("\t\t__________________________________________________________________________________________________________");
		
		int count = 0;
		
		while(true) {
			
			if(count!=0) {
				
				System.out.print("\n\t\tDo You Want Continue 'YES' or 'NO': ");
				String cont = getinput.next();
				
				if(cont.equalsIgnoreCase("YES")){
					
				}else {
					System.exit(0);
					break;
				}
			}
			
			count++;
			
			System.out.println("\n\t\t1.BORROWING THE BOOK \n\t\t2.RETURNING THE BOOK \n\t\t3.EXIT");
			System.out.print("\n\t\tEnter your choice: ");
			
			choice = getinput.nextInt();
			
			switch(choice) {
			
			case 1:
				book.booksAvailable();
				book.takingBook();
				book.booksAvailable();
				break;
				
			case 2:
				book.returnBook();
				book.booksAvailable();
				break;
				
			case 3:
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid choice!!");
				break;
			}
		}
		
	}
	
}
