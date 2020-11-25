package chat;

import java.util.Vector;
import RhythmGame.DynamicBeat;

public class Room {

	
	private String title; // room title
	private String boss; // room owner
	
	Vector<Service> user; // client info
	
	private int count; // room people
	private int selectedMusicIndex; // selected music index
	
	private boolean isHost;
	
	public Room() {
		
		isHost = false;
		selectedMusicIndex = -1;
		user = new Vector<>();
	}
	
	// getter and setter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public Vector<Service> getUser() {
		return user;
	}

	public void setUser(Vector<Service> user) {
		this.user = user;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void setSelectedMusicIndex(int selectedMusicIndex) {
		this.selectedMusicIndex = selectedMusicIndex;
	}
	
	public int getSelectedMusicIndex() {
		return selectedMusicIndex;
	}
	
	public void setIsHost(boolean isHost) {
		this.isHost = isHost;
	}
	
	public boolean getIsHost() {
		return this.isHost;
	}
}
