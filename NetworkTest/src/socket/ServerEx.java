package socket;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerEx {

	static HashMap<String, Object> hash;
	
	public static void main(String[] args) {
		
		BufferedReader in = null;
		BufferedWriter out = null;
		
		ServerSocket listener = null;
		Socket socket = null;
		
		Scanner scan = new Scanner(System.in);
		
		try {
			listener = new ServerSocket(9999);
			
			System.out.println("Connecting...");
			
			socket = listener.accept(); // waiting for client
			
			System.out.println("Success!");
			
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			while(true) {
				String inputMessage = in.readLine();
				
				if(inputMessage.equalsIgnoreCase("bye")) {
					System.out.println("Bye...");
					break;
				}
				
				System.out.println("Client: " + inputMessage);
				System.out.print("Dispatch: ");
				String outputMessage = scan.nextLine();
				
				out.write(outputMessage + "\n");
				out.flush();
			}
		} catch(IOException e) {
			System.out.println("Server Open error... " + e.getMessage());
		} finally {
			try {
				scan.close();
				socket.close();
				listener.close();
			} catch(IOException e) {
				System.out.println("Server Close error... " + e.getMessage());
			}
		}
	}
}
