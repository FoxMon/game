package RhythmGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {

	private Image noteRouteLineImage = new ImageIcon(Main.class.getResource("../images/noteRouteLine.png")).getImage();
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("../images/judgementLine.png")).getImage();
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("../images/gameInfo.png")).getImage();

	private Image noteRouteAImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
//	private Image noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteWImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	private Image noteRouteRImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();

	private Image blueFlareImage;

	private String titleName;
	private String difficulty;
	private String musicTitle;
	private Music gameMusic;
	//private String judge;
	private int score = 0;

	ArrayList<Note> noteList = new ArrayList<Note>();

	public Game(String titleName, String difficulty, String musicTitle) {
		this.titleName = titleName;
		this.difficulty = difficulty;
		this.musicTitle = musicTitle;
		gameMusic = new Music(this.musicTitle, false);
	}

	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteLineImage, 172, 30, null);
		g.drawImage(noteRouteAImage, 176, 30, null);
		g.drawImage(noteRouteLineImage, 276, 30, null);
		g.drawImage(noteRouteSImage, 280, 30, null);
		g.drawImage(noteRouteLineImage, 380, 30, null);
		g.drawImage(noteRouteDImage, 384, 30, null);
		g.drawImage(noteRouteLineImage, 484, 30, null);
		g.drawImage(noteRouteFImage, 488, 30, null);
		g.drawImage(noteRouteLineImage, 588, 30, null);
		g.drawImage(noteRouteSpace1Image, 592, 30, null);
		g.drawImage(noteRouteLineImage, 692, 30, null);
//		g.drawImage(noteRouteSpace2Image, 692, 30, null);
		g.drawImage(noteRouteLineImage, 792, 30, null);
		g.drawImage(noteRouteLImage, 796, 30, null);
		g.drawImage(noteRouteLineImage, 896, 30, null);
		g.drawImage(noteRouteWImage, 900, 30, null);
		g.drawImage(noteRouteLineImage, 1000, 30, null);
		g.drawImage(noteRouteRImage, 1004, 30, null);
		g.drawImage(noteRouteLineImage, 1104, 30, null);

		g.drawImage(gameInfoImage, 0, 660, null);
		g.drawImage(judgementLineImage, 0, 580, null);

		// notes
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);

			if (!note.isProceeded()) {
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
			}
		}
		g.setColor(Color.white);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// 제목, 난이도
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.drawString(titleName, 20, 702);
		g.drawString(difficulty, 1190, 702);

		// 키 이름
		g.setFont(new Font("Arial", Font.PLAIN, 26));
		g.setColor(Color.DARK_GRAY);
		g.drawString("A", 221, 609);
		g.drawString("S", 325, 609);
		g.drawString("D", 429, 609);
		g.drawString("F", 533, 609);
		g.drawString("SPACE", 598, 609);
		g.drawString("L", 841, 609);
		g.drawString("W", 942, 609);
		g.drawString("R", 1048, 609);

		// 점수
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.drawString(Integer.toString(score), 565, 702);

		// 판정 표시
		g.drawImage(blueFlareImage, 100, 100, null);
		if (Main.judge != null && Main.judge != "None")
			g.drawString(Main.judge, 450, 450);

		// 콤보 표시
		g.drawString(Main.printCombo, 600, 450);
		if (Main.judge=="Miss") {
			Main.combo=0;
			blueFlareImage=null;
		}
		if(Main.combo==0)
			Main.printCombo="";
		else if(Main.combo>1)
			Main.printCombo="Combo: "+Main.combo;
	}

	public void pressA() {
		judge("A");
		noteRouteAImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseA() {
		noteRouteAImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressS() {
		judge("S");
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseS() {
		noteRouteSImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressSpace() {
		judge("SPACE");
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
//		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumBig1.mp3", false).start();
	}

	public void releaseSpace() {
		noteRouteSpace1Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
//		noteRouteSpace2Image = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressL() {
		judge("L");
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseL() {
		noteRouteLImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressW() {
		judge("W");
		noteRouteWImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseW() {
		noteRouteWImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	public void pressR() {
		judge("R");
		noteRouteRImage = new ImageIcon(Main.class.getResource("../images/noteRoutePressed.png")).getImage();
		new Music("drumSmall1.mp3", false).start();
	}

	public void releaseR() {
		noteRouteRImage = new ImageIcon(Main.class.getResource("../images/noteRoute.png")).getImage();
	}

	@Override
	public void run() {
		dropNotes(this.titleName);
	}

	public void close() {
		gameMusic.close();
		this.interrupt();
	}

	// 노트 찍기, bpm 보고, 첫 박자의 시간대를 구해야 함.
	public void dropNotes(String titleName) {
		Beat[] beats = null;
		if (titleName.equals("Joakim Karud - Mighty Love") && difficulty.equals("Easy")) {
			int startTime = 4460 - Main.REACH_TIME * 1000;
			int gap = 1000;
			beats = new Beat[] { new Beat(startTime, "A"), new Beat(startTime + gap * 2, "A"),
					new Beat(startTime + gap * 4, "A"), new Beat(startTime + gap * 6, "A"),
					new Beat(startTime + gap * 8, "S"), new Beat(startTime + gap * 10, "S"),
					new Beat(startTime + gap * 12, "S"), new Beat(startTime + gap * 14, "S"),
					new Beat(startTime + gap * 16, "D"), new Beat(startTime + gap * 18, "D"),
					new Beat(startTime + gap * 20, "D"), new Beat(startTime + gap * 22, "D"),
					new Beat(startTime + gap * 24, "SPACE"), new Beat(startTime + gap * 26, "SPACE"),
					new Beat(startTime + gap * 28, "SPACE"), new Beat(startTime + gap * 30, "L"),
					new Beat(startTime + gap * 32, "W"), new Beat(startTime + gap * 32, "R"), };
		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Easy")) {
			int startTime = 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Easy")) {
			int startTime = 1000 - Main.REACH_TIME * 1000;
			beats = new Beat[] { new Beat(startTime, "Space"), };
		}

		int i = 0;
		gameMusic.start();

		// 자원을 효율적으로 사용하기 위한 코드
		while (i < beats.length && !isInterrupted()) {
			boolean dropped = false;

			if (beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}

			if (!dropped) {
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 노트 판정
	public void judge(String input) {
		for (int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if (input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}

	// 판정 이벤트
	public void judgeEvent(String judge) {
		if (!judge.equals("None")) {
			score(judge);
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/blueFlareImage.png")).getImage();
			if (judge.equals("Perfect") || judge.equals("Great") || judge.equals("Good"))
				Main.combo++;
			else if(judge.equals("Miss"))
				Main.combo = 0;
		} 
		Main.judge = judge;
	}

	// 점수
	public void score(String judge) {
		switch (judge) {
		case "Perfect":
			score += 100;
		case "Great":
			score += 70;
		case "Good":
			score += 50;
		}
	}
}
