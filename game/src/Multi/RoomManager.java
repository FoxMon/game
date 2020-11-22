package Multi;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomManager {

	private static List roomList;
	private static AtomicInteger atomicInteger; // can synchronize in thread.
	
	static { // set static parameter
		roomList = new ArrayList();
		atomicInteger = new AtomicInteger();
	}
	
	public RoomManager() { // default constructor
		
	}
	
	public static Room createRoom() { // create room
		int roomID = atomicInteger.incrementAndGet(); // room id
		Room room = new Room(roomID);
		roomList.add(room);
		
		System.out.println("Create Room!");
		
		return room;
	}
	
    public static Room createRoom(GameUser owner) { // user create room for room owner
        int roomID = atomicInteger.incrementAndGet(); // room id

        Room room = new Room(roomID);
        room.enterUser(owner);
        room.setOwner(owner);
        roomList.add(room);
        
        System.out.println("Room Created!");
        
        return room;
    }
    
    public static Room createRoom(List users) {
        int roomId = atomicInteger.incrementAndGet();// room id

        Room room = new Room(roomId);
        room.enterUser(users);
        roomList.add(room);
        
        System.out.println("Room Created!");
        
        return room;
    }

    public static Room getRoom(Room gameRoom){
        int idx = roomList.indexOf(gameRoom);

        if(idx > 0) {
            return (Room)roomList.get(idx);
        } else {
            return null;
        }
    }
    
    public static void removeRoom(Room room) { // remove room
    	room.close();
    	roomList.remove(room);
    	
    	System.out.println("Remove Room!");
    }
    
    public static int roomCount() { // get room count
    	return roomList.size();
    }
    
    public static List returnRoomList() {
    	return roomList;
    }
}
