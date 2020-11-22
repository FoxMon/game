package chatTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class ServerBackground {

	private ServerSocket serverSocket;
	private Socket socket;
	
	private Map<String, DataOutputStream> clientsMap = new HashMap<String, DataOutputStream>();
	
	private ServerGUI gui;
	
	private String msg;
	
	public final void setGUI(ServerGUI gui) { // set server gui
		this.gui = gui;
	}
	
	public void init() throws IOException { // init
		Collections.synchronizedMap(clientsMap);
		serverSocket = new ServerSocket(7777);
		
		while(true) {
			System.out.println("Server wait...");
			socket = serverSocket.accept();
			System.out.println(socket.getInetAddress() + "enter...");
			
			Receiver receiver = new Receiver(socket);
			receiver.start();
		}
	}
	
	public void addClient(String nick, DataOutputStream out) throws IOException { // add
		sendMessage(nick + " enter...");
		clientsMap.put(nick, out);
	}
	
	public void removeClient(String nick) { // out
		sendMessage(nick + " out...");
		clientsMap.remove(nick);
	}
	
	public void sendMessage(String msg) { // send
		Iterator<String> iter = clientsMap.keySet().iterator();
		String key = "";
		
		while(iter.hasNext()) {
			key = iter.next();
			
			try {
				clientsMap.get(key).writeUTF(msg);
			} catch(IOException e) {
				System.out.println("Send error... " + e.getMessage());
			}
		}
	}
	
	class Receiver extends Thread {
		
		private DataInputStream in;
		private DataOutputStream out;
		
		private String nick;
		
		public Receiver(Socket socket) throws IOException {
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			nick = in.readUTF();
			
			addClient(nick, out);
		}
		
		@Override
		public void run() {
			try {
				msg = in.readUTF();
				
				sendMessage(msg);
				gui.appendMessage(msg);
			} catch(IOException e) {
				removeClient(nick);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		ServerBackground serverBackground = new ServerBackground();
		serverBackground.init();
	}
}
