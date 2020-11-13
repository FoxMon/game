package multi;

import java.net.Socket;

public class GameUser {
	
	private int id;
	
	private String nickName;
	
	private Room room;
	
	private Socket socket;
	
	private PlayerGameInfo.Location playerLocation; // game info
	private PlayerGameInfo.Status playerStatus; // game info
	
	public GameUser() { // no one has any information
		// empty
	}
	
    public GameUser(String nickName) { // nickname
		this.nickName = nickName;
	}

    public GameUser(int id, String nickName) { // id, nickname
		this.id = id;
		this.nickName = nickName;
	}
    
    public void enterRoom(Room room) { // go to room
    	this.room = room;
    	
    	if(this.room != null) {
    		System.out.println(nickName + " in!");
    		return;
    	}
    	
    	room.enterUser(this);
    }
    
    public void exitRoom(Room room) {
    	this.room = null;
    	
    	// event
    }
    
    public void setPlayerStatus(PlayerGameInfo.Status status) {
    	this.playerStatus = status;
    }
    
    public void setPlayerLocation(PlayerGameInfo.Location location) {
    	this.playerLocation = location;
    }

    // getter, setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
    
    @Override
    public boolean equals(Object o) { // equals hashcode
    	if(this == o) {
    		return true;
    	}
    	
    	if(o == null || getClass() != o.getClass()) {
    		return false;
    	}
    	
    	GameUser gameUser = (GameUser)o;
    	
    	return (this.id == gameUser.id);
    }
    
    @Override
    public int hashCode() {
    	return id;
    }
}
