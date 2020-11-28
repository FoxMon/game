package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

import RhythmGame.Main;

public class Service extends Thread {

	private Room myRoom; // client room

	private Vector<Service> allUser;
	private Vector<Service> waitUser;
	private Vector<Room> roomUser;

	private Socket socket;

	private BufferedReader in;
	private OutputStream out;

	private String name; // user name

	public Service(Socket socket, Server server) { // constructor

		this.allUser = server.getAllUser();
		this.waitUser = server.getWaitUser();
		this.roomUser = server.getRoomUser();
		this.socket = socket;

		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();

			start();
		} catch (IOException e) {
			System.out.println("Stream open error... " + e.getMessage());
		}
	}

	public String getRoomInfo() { // get room information

		String str = "";

		for (int i = 0; i < roomUser.size(); i++) {
			Room room = roomUser.get(i);
			str += room.getTitle() + "--" + room.getCount();

			if (i < (roomUser.size() - 1)) {
				str += ",";
			}
		}

		return str;
	}

	public String getRoomUserInfo() { // in room get user information

		String str = "";

		for (int i = 0; i < myRoom.user.size(); i++) {
			Service service = myRoom.user.get(i);
			str += service.name;

			if (i < (myRoom.user.size() - 1)) {
				str += ",";
			}
		}

		return str;
	}

	public String getRoomUserInfo(String title) { // on click room -> user information

		String str = "";

		for (int i = 0; i < roomUser.size(); i++) {
			Room room = roomUser.get(i);

			if (room.getTitle().equals(title)) { // same title
				for (int j = 0; j < room.user.size(); j++) {
					Service service = room.user.get(j);
					str += service.name;

					if (j < (room.user.size() - 1)) {
						str += ",";
					}
				}

				break;
			}

		}

		return str;
	}

	public String getWaitUserInfo() { // waiting user information

		String str = "";

		for (int i = 0; i < waitUser.size(); i++) {
			Service service = waitUser.get(i);
			str += service.name;

			if (i < (waitUser.size() - 1)) {
				str += ",";
			}
		}

		return str;
	}

	public void messageAll(String message) { // all user

		for (int i = 0; i < allUser.size(); i++) {
			Service service = allUser.get(i);

			try {
				service.messageTo(message);
			} catch (IOException e) {
				allUser.remove(i--); // disconnect user remove
				System.out.println("Client exit... " + e.getMessage());
			}
		}
	}

	public void messageWait(String message) { // waiting room user

		for (int i = 0; i < waitUser.size(); i++) {

			Service service = waitUser.get(i);

			try {
				service.messageTo(message);
			} catch (IOException e) {
				waitUser.remove(i--); // disconnect user remove
				System.out.println("Client exit... " + e.getMessage());
			}
		}
	}

	public void messageRoom(String message) { // room user

		for (int i = 0; i < myRoom.user.size(); i++) {

			Service service = myRoom.user.get(i);

			try {
				service.messageTo(message);
			} catch (IOException e) {
				myRoom.user.remove(i--); // disconnect user remove
				System.out.println("Client exit... " + e.getMessage());
			}
		}
	}

	public void messageTo(String message) throws IOException { // delivery message

		out.write((message + "\n").getBytes());
	}

	public void messageSelectedMusicIndex(String message) throws IOException { // music index

		for (int i = 0; i < myRoom.user.size(); i++) {

			Service service = myRoom.user.get(i);
			// Main.dynamicBeat.setNowSelected(myRoom.getSelectedMusicIndex());

			try {
				service.messageTo(message);
			} catch (IOException e) {
				myRoom.user.remove(i--); // disconnect user remove
				System.out.println("Music index error... " + e.getMessage());
			}
		}
	}

	public void messageShowSelectMusic(String message) throws IOException { // easy button start

		int hostIndex = 0;

		String host = null;

		for (int i = 0; i < myRoom.user.size(); i++) {

			if (myRoom.getIsHost()) {
				hostIndex = 0;
				host = Integer.toString(hostIndex);
			}

			Service service = myRoom.user.get(i);

			try {
				service.messageTo(message + "|" + host);
			} catch (IOException e) {
				myRoom.user.remove(i--); // disconnect user remove
				System.out.println("Music start error... " + e.getMessage());
			}
		}
	}

	public void messageEasyStart(String message) throws IOException { // easy button start

		for (int i = 0; i < myRoom.user.size(); i++) {

			Service service = myRoom.user.get(i);

			try {
				service.messageTo(message);
			} catch (IOException e) {
				myRoom.user.remove(i--); // disconnect user remove
				System.out.println("Music start error... " + e.getMessage());
			}
		}
	}

	public void messageHardStart(String message) throws IOException { // easy button start

		for (int i = 0; i < myRoom.user.size(); i++) {

			Service service = myRoom.user.get(i);

			try {
				service.messageTo(message);
			} catch (IOException e) {
				myRoom.user.remove(i--); // disconnect user remove
				System.out.println("Music start error... " + e.getMessage());
			}
		}
	}

	@Override
	public void run() {

		try {
			while (true) {

				String message = in.readLine();

				if (message == null) { // abnormal end
					return;
				}

				if (message.trim().length() > 0) {
					System.out.println("From Client: " + message + ":" + socket.getInetAddress().getHostAddress());

					String msgs[] = message.split("\\|");
					String protocol = msgs[0];

					switch (protocol) {
					case "100": // enter
						allUser.add(this);
						waitUser.add(this);
						break;

					case "150": // title name
						name = msgs[1];
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitUserInfo());
						break;

					case "160": // create room
						myRoom = new Room();
						myRoom.setTitle(msgs[1]);
						myRoom.setCount(1);
						myRoom.setBoss(name);
						myRoom.setIsHost(true);

						roomUser.add(myRoom);
						waitUser.remove(this);
						myRoom.user.add(this);

						messageRoom("200|" + name);
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitUserInfo());
						break;

					case "170": // in roby room user
						messageTo("170|" + getRoomUserInfo(msgs[1]));
						break;

					case "175": // in room room user
						messageTo("175|" + getRoomUserInfo());
						break;

					case "200": // enter
						for (int i = 0; i < roomUser.size(); i++) { // find room title
							Room room = roomUser.get(i);

							if (room.getTitle().equals(msgs[1])) { // same room title
								myRoom = room;
								myRoom.setCount(myRoom.getCount() + 1);
								break;
							}
						}

						waitUser.remove(this);
						myRoom.user.add(this);

						messageRoom("200|" + name);
						messageTo("202|" + myRoom.getTitle());
						messageWait("160|" + getRoomInfo());
						messageWait("180|" + getWaitUserInfo());
						break;

					case "300": // message
						messageRoom("300|[" + name + "] -> " + msgs[1]);
						break;

					case "400": // exit
						myRoom.setCount(myRoom.getCount() - 1);
						messageRoom("400|" + name);
						myRoom.user.remove(this);
						waitUser.add(this);
						messageRoom("175|" + getRoomUserInfo());
						messageRoom("160|" + getRoomInfo());
						break;

					case "450": // choice
						messageShowSelectMusic(msgs[0]);
						break;

					case "500": // music choice left
						myRoom.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
						messageSelectedMusicIndex("500|" + Integer.toString(myRoom.getSelectedMusicIndex()));
						break;

					case "600": // music choice right
						myRoom.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
						messageSelectedMusicIndex("600|" + Integer.toString(myRoom.getSelectedMusicIndex()));
						break;

					case "700": // easy button
						myRoom.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
						messageEasyStart("700|" + msgs[1]);
						break;

					case "800": // hard button
						myRoom.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
						messageHardStart("800|" + msgs[1]);
						break;
					}
				}
			}
		} catch (IOException e) {
			System.out.println("Service error... " + e.getMessage());
		}
	}

	// getter and setter
	public Room getMyRoom() {
		return myRoom;
	}

	public void setMyRoom(Room myRoom) {
		this.myRoom = myRoom;
	}

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

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public BufferedReader getIn() {
		return in;
	}

	public void setIn(BufferedReader in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}
}
