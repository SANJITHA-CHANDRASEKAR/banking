package management;
import java.sql.*;
import java.util.*;

public class Bank extends BankOperationImpl{
	public static void main(String[] args)  throws Exception{
		
		Bank obj = new Bank();
		Scanner sc = new Scanner(System.in);
		
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/banking";
		String username = "root";
		String password = "sanjitha2903";
		Connection con = DriverManager.getConnection(url, username, password);
		
		Map<String,Integer> limit = new HashMap<String,Integer>();
        int choice =0;
        while(choice!=6) {
        	System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Account details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
	        choice = sc.nextInt();
	
	        switch (choice) {
	            case 1:
	                obj.createAccount(sc, con);
	                break;
	            case 2:
	            	obj.update(sc,con);
	                break;
	            case 3:
	            	obj.update(limit,sc,con);
	                break;
	            case 4:
	                obj.checkBalance(sc,con);
	                break;
	            case 5:
	                obj.details(sc,con);
	                break;
	            case 6:
	                System.out.println("Exiting...");
	                break;
	            default:
	                System.out.println("Invalid choice!");
	                break;
	        }
        }

    }
}
