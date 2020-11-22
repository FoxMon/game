package RhythmGame;

import javax.swing.JFrame;

public class Main {
	
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	public static final int NOTE_SPEED = 2;
	public static final int SLEEP_TIME = 10;
	public static final int REACH_TIME = 2;
	
	public static long wheelTime = 0; // 휠 판정을 위해 휠 돌리는 시작시간을 저장하는 변수
	
	public static int combo = 0; // 콤보
	
	public static String judge; // 판정 변수
	public static String printCombo = "Combo: " + combo; // "combo= x"를 저장하는 변수
	
	public static DynamicBeat dynamicBeat;
	
	public static void main(String[] args) {
		
		dynamicBeat = new DynamicBeat();
	}
}
