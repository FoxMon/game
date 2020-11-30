package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnectionTest {
	
	private Connection con;
	private Statement st;
	private ResultSet rs;
	
	public DBConnectionTest() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/account?serverTimezone=Asia/Seoul", "root", "wnsgh1524");
			st = con.createStatement();
			
			System.out.println("DB Connection Success...");
		} catch(Exception e) {
			System.out.println("DB Connection error... " + e.getMessage());	
		}
	}
}
