package database;

public class Main {
	
	public static void main(String[] args) {
		DBConnection connection = new DBConnection();
		
		System.out.println("Is right admin? = " + connection.isAdmin("admin", "admin"));
	}
}
