package multi;

public class Test {

	public static void main(String[] args) {
		
		/*
		 * create room test good.
        System.out.println(RoomManager.createRoom());
        System.out.println(RoomManager.createRoom());

        Thread thread = new Thread(() -> System.out.println(RoomManager.createRoom()));
        Thread thread2 = new Thread(() -> System.out.println(RoomManager.createRoom()));
        Thread thread3 = new Thread(() -> System.out.println(RoomManager.createRoom()));
        thread.start();
        thread2.start();
        thread3.start();
        */
		
		// test good
		GameUser gameUser = new GameUser(1, "FoxMon");
		Room room = RoomManager.createRoom();
		room.enterUser(gameUser);
		
		GameUser gameUser2 = new GameUser(2, "FoxMon2");
		room.enterUser(gameUser2);
		
		room.exitUser(gameUser);
	}
}
