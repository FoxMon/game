package Multi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Room {
	
	private int roomID;
	
	private String roomName;
	
	private List userList;
	
	private GameUser roomOwner;
	
	public Room(int roomID) { // no one else
		this.roomID = roomID;
		userList = new ArrayList();
	}
	
	public Room(GameUser user) { // user create the room
		userList = new ArrayList();
		user.enterRoom(this);
		userList.add(user);
		this.roomOwner = user;
	}
	
	public Room(List users) { // userlist create the room
		this.userList = users;
		
		for(int i = 0; i < users.size(); i++) {
			Object temp = users.get(i);
			GameUser user = (GameUser)temp;
			user.enterRoom(this);
		}
		
		this.roomOwner = (GameUser)userList.get(0);
	}
	
	public void enterUser(GameUser user) {
		user.enterRoom(this);
		userList.add(user);
	}
	
	public void enterUser(List users) {
		for(int i = 0; i < users.size(); i++) {
			Object temp = users.get(i);
			GameUser user = (GameUser)temp;
			user.enterRoom(this);
		}
		
		userList.add(users);
	}
	
	public void exitUser(GameUser user) { // fire
		user.exitRoom(this);
		userList.remove(user);
		
		if(userList.size() < 1) { // under 1
			RoomManager.removeRoom(this);
			
			return;
		}
		
		if(userList.size() < 2) { // under 2
			this.roomOwner = (GameUser)userList.get(0); // change room leader
			
			return;
		}
	}
	
	public void close() { // close room
		for(int i = 0; i < userList.size(); i++) {
			Object temp = userList.get(i);
			GameUser user = (GameUser)temp;
			user.exitRoom(this);
		}
		
		this.userList.clear();
		this.userList = null;
	}
	
	// logic
	
	public void broadcast(byte[] data) { // transfer data for what?
		for(int i = 0; i < userList.size(); i++) {
			// casting
			Object temp = userList.get(i);
			GameUser user = (GameUser)temp;
			
			/*
			try {
				// transfer data in here...
			} catch(IOException e) {
				System.out.println("Data transfer error... " + e.getMessage());
			}
			*/
		}
	}
	
	public void setOwner(GameUser gameUser) { // set room manager
		this.roomOwner = gameUser;
	}
	
	public void setRoomName(String name) {
		this.roomName = name;
	}
	
	public GameUser getUserByNickName(String name) { // search user name
		for(int i = 0; i < userList.size(); i++) {
			Object temp = userList.get(i);
			GameUser user = (GameUser)temp;
			
			if(user.getNickName().equals(name)) { // success
				return user;
			}
		}
		
		return null; // fail
	}
	
	public GameUser getUser(GameUser gameUser) { // search user
		int idx = userList.indexOf(gameUser);
		
		if(idx > 0) {
			return (GameUser)userList.get(idx);
		} else {
			return null;
		}
	}

	public int getUserSize() { // get user count
		return userList.size();
	}
	
	@Override
	public boolean equals(Object o) { // equals hash code
		if (this == o) {
			return true;
		}
		
		if (o == null || getClass() != o.getClass())
			return false;

		Room gameRoom = (Room)o;

		return this.roomID == gameRoom.roomID;
	}
	
	@Override
	public int hashCode() {
		return roomID;
	}
	
	// setter and getter
	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public List getUserList() {
		return userList;
	}

	public void setUserList(List userList) {
		this.userList = userList;
	}

	public GameUser getRoomOwner() {
		return roomOwner;
	}

	public void setRoomOwner(GameUser roomOwner) {
		this.roomOwner = roomOwner;
	}

	public String getRoomName() {
		return roomName;
	}	
}
