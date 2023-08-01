package management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class BankOperationImpl extends BankOperation {

    @Override 
    public void createAccount(Scanner scanner,Connection con) throws SQLException, InterruptedException  {
        
    	User user = new User();
    	
        System.out.print("Enter the account number: ");
        user.setAccountNumber(scanner.next());
        System.out.print("Enter the account holder name: ");
        user.setAccountName(scanner.next());
        System.out.print("Enter your age: ");
        user.setAge(scanner.nextInt());
        System.out.print("Enter your Mobile Number: ");
        user.setMobileNumber(scanner.nextLong());
        System.out.print("Enter your email id : ");
        user.setEmail(scanner.next());
        System.out.print("Enter your residential address: ");
        user.setAddress(scanner.next());
        System.out.print("Enter the initial balance: ");
        user.setBalance(scanner.nextDouble());
        System.out.print("Enter the branch name: ");
        user.setBranch(scanner.next());
        System.out.print("Enter your account type: ");
        user.setType(scanner.next());

       
        String sql = "INSERT INTO useraccount (`accountNumber`, `userName`, `age`, `mobileNumber`, `email`, `address`, `balance`, `type`, `branch`) VALUES (?,?,?,?,?,?,?,?,?);";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1,  user.getAccountNumber());
        statement.setString(2,  user.getAccountName());
        statement.setInt(3,  user.getAge());
        statement.setLong(4,  user.getMobileNumber());
        statement.setString(5,  user.getEmail());
        statement.setString(6,  user.getAddress());
        statement.setDouble(7,  user.getBalance());
        statement.setString(8,  user.getBranch());
        statement.setString(9,  user.getType());
        statement.executeUpdate();

        System.out.println("Account created successfully!\n");
        Thread.sleep(2000);
    }

    @Override
    public void update(Map<String, Integer> limit, Scanner scanner, Connection con) throws SQLException, InterruptedException {
    	
        System.out.print("Enter the account number: ");
        String accountNumber = scanner.next();
        if(limit.containsKey(accountNumber)) {
        	int count = limit.get(accountNumber);
        	if(count>=3) {
        		System.out.println("Your today's limit for withdraw amount has been exceeded!!!");
        		return;
        	}else {
        	limit.put(accountNumber, count+1);
        	}
        }
        else {
        	limit.put(accountNumber, 1);
        }
        
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();

        String sql = "UPDATE useraccount SET balance = balance - ? WHERE accountNumber = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDouble(1, amount);
        statement.setString(2, accountNumber);
        statement.executeUpdate();

        System.out.println("Amount withdrawn successfully!\n");
        Thread.sleep(2000);
    }


    @Override
    public void update(Scanner scanner, Connection con) throws SQLException, InterruptedException {
        
    	System.out.print("Enter the account number: ");
        String accountNumber = scanner.next();
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();

        String sql = "UPDATE useraccount SET balance = balance + ? WHERE accountNumber = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setDouble(1, amount);
        statement.setString(2, accountNumber);
        statement.executeUpdate();

        System.out.println("Amount deposited successfully!\n");
        Thread.sleep(2000);
		
	}
    @Override
    public void checkBalance(Scanner sc,Connection con) throws SQLException, InterruptedException {
    	
    	System.out.print("Enter the account number: ");
        String accountNumber = sc.next();
        String sql = "SELECT balance FROM useraccount WHERE accountNumber = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, accountNumber);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.print(resultSet.getDouble(1)+"\n");
        } else {
        	System.out.print("Invalid Account Number!!!");
        }
        Thread.sleep(2000);
    }
    @Override
    public void details(Scanner sc, Connection con) throws SQLException, InterruptedException{
    	System.out.print("Enter the account number: ");
        String accountNumber = sc.next();
        
        String sql = "SELECT * FROM useraccount a join branch b on a.branch=b.branch WHERE accountNumber = ?";
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, accountNumber);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.print("Account Number : " + resultSet.getString(1) + "\n");
            System.out.print("User's Name : " + resultSet.getString(2) + "\n");
            System.out.print("Account Type : " + resultSet.getString(8) + "\n");
            System.out.print("User's Email : " + resultSet.getString(5) + "\n");
            System.out.print("User's Mobile : " + resultSet.getLong(4) + "\n");
            System.out.print("Branch Id : " + resultSet.getString(10) + "\n");
            System.out.print("Branch Name : " + resultSet.getString(11) + "\n");
        } else {
        	System.out.print("Invalid Account Number!!!");
        }
        Thread.sleep(2000);
    }

}
