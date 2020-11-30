package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server implements Runnable {

	private Vector<Service> allUser; // all user
	private Vector<Service> waitUser; // waiting user
	private Vector<Room> roomUser; //  room user
	
	
	public Server() { // constructor
		allUser = new Vector<>();
		waitUser = new Vector<>();
		roomUser = new Vector<>();
		
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		try {
			// current IP + port number 5678 -> socket service
			ServerSocket serverSocket = new ServerSocket(5678);
			
			System.out.println("Server start...");
			
			while(true) { // server now waiting for client
				Socket socket = serverSocket.accept(); // waiting for client
				Service service = new Service(socket, this);
			}
		} catch(IOException e) {
			System.out.println("Server error... " + e.getMessage());
		}
	}
	// getter and setter
	
	public Vector<Service> getAllUser() {
		return allUser;
	}

	public void setAllUser(Vector<Service> allUser) {
		this.allUser = allUser;
	}

	public Vector<Service> getWaitUser() {
		return waitUser;
	}

	public void setWaitUser(Vector<Service> waitUser) {
		this.waitUser = waitUser;
	}

	public Vector<Room> getRoomUser() {
		return roomUser;
	}

	public void setRoomUser(Vector<Room> roomUser) {
		this.roomUser = roomUser;
	}

	public static void main(String[] args) {
		
		new Server();
	}
}
