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
		if (Main.judge != null && Main.judge != "None") {
			g.drawString(Main.judge, 450, 450);
		}

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
					new Beat(startTime + gap * 34, "A"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "L"), new Beat(startTime + gap * 37, "W"),
					new Beat(startTime + gap * 38, "R"), new Beat(startTime + gap * 40, "W"),
					new Beat(startTime + gap * 41, "W"), new Beat(startTime + gap * 43, "F"),
					new Beat(startTime + gap * 45, "SPACE"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "L"), new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 49, "W"), new Beat(startTime + gap * 49, "A"),
					new Beat(startTime + gap * 51, "F"), new Beat(startTime + gap * 52, "SPACE"),
					new Beat(startTime + gap * 54, "A"), new Beat(startTime + gap * 55, "S"),
					new Beat(startTime + gap * 56, "D"), new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "SPACE"), new Beat(startTime + gap * 59, "L"),
					new Beat(startTime + gap * 60, "W"), new Beat(startTime + gap * 61, "R"),
					new Beat(startTime + gap * 62, "W"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 64, "SPACE"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 66, "D"), new Beat(startTime + gap * 67, "S"),
					new Beat(startTime + gap * 68, "A"), new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 69, "SPACE"), new Beat(startTime + gap * 70, "A"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 71, "R"),
					new Beat(startTime + gap * 71, "L"), new Beat(startTime + gap * 72, "W"),
					new Beat(startTime + gap * 73, "W"), new Beat(startTime + gap * 75, "A"),
					new Beat(startTime + gap * 75, "S"), new Beat(startTime + gap * 75, "D"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 76, "A"),
					new Beat(startTime + gap * 76, "L"), new Beat(startTime + gap * 76, "F"),
					new Beat(startTime + gap * 76, "S"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"), new Beat(startTime + gap * 77, "S"),
					new Beat(startTime + gap * 77, "R"), new Beat(startTime + gap * 77, "SPACE"),
					new Beat(startTime + gap * 79, "A"), new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 80, "S"), new Beat(startTime + gap * 80, "SPACE"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "F"),
					new Beat(startTime + gap * 83, "L"), new Beat(startTime + gap * 83, "R"),
					new Beat(startTime + gap * 84, "L"), new Beat(startTime + gap * 84, "R"),
					new Beat(startTime + gap * 85, "W"), new Beat(startTime + gap * 85, "SPACE"),
					new Beat(startTime + gap * 86, "L"), new Beat(startTime + gap * 86, "W"),
					new Beat(startTime + gap * 87, "W"), new Beat(startTime + gap * 87, "R"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 89, "R"),
					new Beat(startTime + gap * 90, "SPACE"), new Beat(startTime + gap * 90, "W"),
					new Beat(startTime + gap * 91, "A"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 91, "L"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "SPACE"), new Beat(startTime + gap * 93, "W"),
					new Beat(startTime + gap * 94, "L"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 94, "R"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 95, "W"), new Beat(startTime + gap * 95, "F"),
					new Beat(startTime + gap * 95, "SPACE"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 96, "L"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 97, "W"), new Beat(startTime + gap * 97, "A"),
					new Beat(startTime + gap * 97, "F"), new Beat(startTime + gap * 98, "SPACE"),
					new Beat(startTime + gap * 98, "A"), new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 100, "D"), new Beat(startTime + gap * 100, "F"),
					new Beat(startTime + gap * 100, "SPACE"), new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 101, "W"), new Beat(startTime + gap * 102, "R"),
					new Beat(startTime + gap * 103, "W"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "SPACE"), new Beat(startTime + gap * 105, "F"),
					new Beat(startTime + gap * 105, "D"), new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 105, "A"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 106, "SPACE"), new Beat(startTime + gap * 107, "A"),
					new Beat(startTime + gap * 108, "F"), new Beat(startTime + gap * 109, "SPACE"),
					new Beat(startTime + gap * 110, "R"), new Beat(startTime + gap * 111, "W"),
					new Beat(startTime + gap * 112, "A"), new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 112, "L"), new Beat(startTime + gap * 112, "R"),
					new Beat(startTime + gap * 113, "S"), new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "L"), new Beat(startTime + gap * 114, "R"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 115, "W"),
					new Beat(startTime + gap * 115, "S"), new Beat(startTime + gap * 115, "SPACE"),
					new Beat(startTime + gap * 117, "R"), new Beat(startTime + gap * 117, "SPACE"),
					new Beat(startTime + gap * 117, "A"), new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 118, "SPACE"),
					new Beat(startTime + gap * 120, "A"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "D"), new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 124, "SPACE"), new Beat(startTime + gap * 125, "A"),
					new Beat(startTime + gap * 126, "S"), new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 128, "F"), new Beat(startTime + gap * 129, "SPACE"),
					
					new Beat(startTime + gap * 130, "S"), new Beat(startTime + gap * 131, "L"),
					new Beat(startTime + gap * 132, "D"), new Beat(startTime + gap * 133, "A"),
					new Beat(startTime + gap * 133, "D"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 135, "SPACE"), new Beat(startTime + gap * 136, "SPACE"),
					new Beat(startTime + gap * 137, "SPACE"), new Beat(startTime + gap * 137, "L"),
					new Beat(startTime + gap * 139, "A"), new Beat(startTime + gap * 139, "S"),
					new Beat(startTime + gap * 139, "D"), new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 139, "SPACE"), new Beat(startTime + gap * 139, "L"),
					new Beat(startTime + gap * 140, "W"), new Beat(startTime + gap * 141, "R"),
					
					new Beat(startTime + gap * 142, "W"), new Beat(startTime + gap * 143, "F"),
					new Beat(startTime + gap * 145, "SPACE"), new Beat(startTime + gap * 146, "S"),
					new Beat(startTime + gap * 148, "L"), new Beat(startTime + gap * 148, "S"),
					new Beat(startTime + gap * 149, "W"), new Beat(startTime + gap * 149, "A"),
					new Beat(startTime + gap * 151, "F"), new Beat(startTime + gap * 152, "SPACE"),
					new Beat(startTime + gap * 154, "A"), new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "D"), new Beat(startTime + gap * 157, "F"),
					new Beat(startTime + gap * 158, "SPACE"), new Beat(startTime + gap * 159, "L"),
					new Beat(startTime + gap * 160, "W"), new Beat(startTime + gap * 161, "R"),
					new Beat(startTime + gap * 162, "W"), new Beat(startTime + gap * 163, "L"),
					new Beat(startTime + gap * 164, "SPACE"), new Beat(startTime + gap * 165, "F"),
					new Beat(startTime + gap * 166, "D"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 168, "A"), new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 169, "SPACE"), new Beat(startTime + gap * 170, "A"),
					new Beat(startTime + gap * 170, "S"), new Beat(startTime + gap * 171, "R"),
					new Beat(startTime + gap * 171, "L"), new Beat(startTime + gap * 172, "W"),
					new Beat(startTime + gap * 173, "W"), new Beat(startTime + gap * 175, "A"),
					new Beat(startTime + gap * 175, "S"), new Beat(startTime + gap * 175, "D"),
					new Beat(startTime + gap * 175, "F"), new Beat(startTime + gap * 176, "A"),
					new Beat(startTime + gap * 176, "L"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 176, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 177, "S"),
					new Beat(startTime + gap * 177, "R"), new Beat(startTime + gap * 177, "SPACE"),
					new Beat(startTime + gap * 179, "A"), new Beat(startTime + gap * 179, "D"),
					new Beat(startTime + gap * 180, "S"), new Beat(startTime + gap * 180, "SPACE"),
					new Beat(startTime + gap * 181, "F"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "L"), new Beat(startTime + gap * 183, "R"),
					
					new Beat(startTime + gap * 185, "SPACE"), new Beat(startTime + gap * 187, "F"),
					new Beat(startTime + gap * 189, "D"), new Beat(startTime + gap * 191, "S"),
					new Beat(startTime + gap * 193, "A"), new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 197, "SPACE"), new Beat(startTime + gap * 199, "A"),
					new Beat(startTime + gap * 201, "S"), new Beat(startTime + gap * 203, "R"),
					new Beat(startTime + gap * 205, "L"), new Beat(startTime + gap * 207, "W"),
					new Beat(startTime + gap * 209, "W"), new Beat(startTime + gap * 211, "A"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 215, "D"),
					new Beat(startTime + gap * 217, "F"), new Beat(startTime + gap * 219, "A"),
					new Beat(startTime + gap * 221, "L"), new Beat(startTime + gap * 223, "F"),
					new Beat(startTime + gap * 225, "S"), new Beat(startTime + gap * 227, "D"),
					new Beat(startTime + gap * 228, "D")
					};
		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Hard")) {
			
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
					new Beat(startTime + gap * 34, "A"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "L"), new Beat(startTime + gap * 37, "W"),
					new Beat(startTime + gap * 38, "R"), new Beat(startTime + gap * 40, "W"),
					new Beat(startTime + gap * 41, "W"), new Beat(startTime + gap * 43, "F"),
					new Beat(startTime + gap * 45, "SPACE"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "L"), new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 49, "W"), new Beat(startTime + gap * 49, "A"),
					new Beat(startTime + gap * 51, "F"), new Beat(startTime + gap * 52, "SPACE"),
					new Beat(startTime + gap * 54, "A"), new Beat(startTime + gap * 55, "S"),
					new Beat(startTime + gap * 56, "D"), new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "SPACE"), new Beat(startTime + gap * 59, "L"),
					new Beat(startTime + gap * 60, "W"), new Beat(startTime + gap * 61, "R"),
					new Beat(startTime + gap * 62, "W"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 64, "SPACE"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 66, "D"), new Beat(startTime + gap * 67, "S"),
					new Beat(startTime + gap * 68, "A"), new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 69, "SPACE"), new Beat(startTime + gap * 70, "A"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 71, "R"),
					new Beat(startTime + gap * 71, "L"), new Beat(startTime + gap * 72, "W"),
					new Beat(startTime + gap * 73, "W"), new Beat(startTime + gap * 75, "A"),
					new Beat(startTime + gap * 75, "S"), new Beat(startTime + gap * 75, "D"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 76, "A"),
					new Beat(startTime + gap * 76, "L"), new Beat(startTime + gap * 76, "F"),
					new Beat(startTime + gap * 76, "S"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"), new Beat(startTime + gap * 77, "S"),
					new Beat(startTime + gap * 77, "R"), new Beat(startTime + gap * 77, "SPACE"),
					new Beat(startTime + gap * 79, "A"), new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 80, "S"), new Beat(startTime + gap * 80, "SPACE"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "F"),
					new Beat(startTime + gap * 83, "L"), new Beat(startTime + gap * 83, "R"),
					new Beat(startTime + gap * 84, "L"), new Beat(startTime + gap * 84, "R"),
					new Beat(startTime + gap * 85, "W"), new Beat(startTime + gap * 85, "SPACE"),
					new Beat(startTime + gap * 86, "L"), new Beat(startTime + gap * 86, "W"),
					new Beat(startTime + gap * 87, "W"), new Beat(startTime + gap * 87, "R"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 89, "R"),
					new Beat(startTime + gap * 90, "SPACE"), new Beat(startTime + gap * 90, "W"),
					new Beat(startTime + gap * 91, "A"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 91, "L"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "SPACE"), new Beat(startTime + gap * 93, "W"),
					new Beat(startTime + gap * 94, "L"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 94, "R"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 95, "W"), new Beat(startTime + gap * 95, "F"),
					new Beat(startTime + gap * 95, "SPACE"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 96, "L"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 97, "W"), new Beat(startTime + gap * 97, "A"),
					new Beat(startTime + gap * 97, "F"), new Beat(startTime + gap * 98, "SPACE"),
					new Beat(startTime + gap * 98, "A"), new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 100, "D"), new Beat(startTime + gap * 100, "F"),
					new Beat(startTime + gap * 100, "SPACE"), new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 101, "W"), new Beat(startTime + gap * 102, "R"),
					new Beat(startTime + gap * 103, "W"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "SPACE"), new Beat(startTime + gap * 105, "F"),
					new Beat(startTime + gap * 105, "D"), new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 105, "A"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 106, "SPACE"), new Beat(startTime + gap * 107, "A"),
					new Beat(startTime + gap * 108, "F"), new Beat(startTime + gap * 109, "SPACE"),
					new Beat(startTime + gap * 110, "R"), new Beat(startTime + gap * 111, "W"),
					new Beat(startTime + gap * 112, "A"), new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 112, "L"), new Beat(startTime + gap * 112, "R"),
					new Beat(startTime + gap * 113, "S"), new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "L"), new Beat(startTime + gap * 114, "R"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 115, "W"),
					new Beat(startTime + gap * 115, "S"), new Beat(startTime + gap * 115, "SPACE"),
					new Beat(startTime + gap * 117, "R"), new Beat(startTime + gap * 117, "SPACE"),
					new Beat(startTime + gap * 117, "A"), new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 118, "SPACE"),
					new Beat(startTime + gap * 120, "A"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "D"), new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 124, "SPACE"), new Beat(startTime + gap * 125, "A"),
					new Beat(startTime + gap * 126, "S"), new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 128, "F"), new Beat(startTime + gap * 129, "SPACE"),
					
					new Beat(startTime + gap * 130, "S"), new Beat(startTime + gap * 131, "L"),
					new Beat(startTime + gap * 132, "D"), new Beat(startTime + gap * 133, "A"),
					new Beat(startTime + gap * 133, "D"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 135, "SPACE"), new Beat(startTime + gap * 136, "SPACE"),
					new Beat(startTime + gap * 137, "SPACE"), new Beat(startTime + gap * 137, "L"),
					new Beat(startTime + gap * 139, "A"), new Beat(startTime + gap * 139, "S"),
					new Beat(startTime + gap * 139, "D"), new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 139, "SPACE"), new Beat(startTime + gap * 139, "L"),
					new Beat(startTime + gap * 140, "W"), new Beat(startTime + gap * 141, "R"),
					
					new Beat(startTime + gap * 142, "W"), new Beat(startTime + gap * 143, "F"),
					new Beat(startTime + gap * 145, "SPACE"), new Beat(startTime + gap * 146, "S"),
					new Beat(startTime + gap * 148, "L"), new Beat(startTime + gap * 148, "S"),
					new Beat(startTime + gap * 149, "W"), new Beat(startTime + gap * 149, "A"),
					new Beat(startTime + gap * 151, "F"), new Beat(startTime + gap * 152, "SPACE"),
					new Beat(startTime + gap * 154, "A"), new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "D"), new Beat(startTime + gap * 157, "F"),
					new Beat(startTime + gap * 158, "SPACE"), new Beat(startTime + gap * 159, "L"),
					new Beat(startTime + gap * 160, "W"), new Beat(startTime + gap * 161, "R"),
					new Beat(startTime + gap * 162, "W"), new Beat(startTime + gap * 163, "L"),
					new Beat(startTime + gap * 164, "SPACE"), new Beat(startTime + gap * 165, "F"),
					new Beat(startTime + gap * 166, "D"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 168, "A"), new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 169, "SPACE"), new Beat(startTime + gap * 170, "A"),
					new Beat(startTime + gap * 170, "S"), new Beat(startTime + gap * 171, "R"),
					new Beat(startTime + gap * 171, "L"), new Beat(startTime + gap * 172, "W"),
					new Beat(startTime + gap * 173, "W"), new Beat(startTime + gap * 175, "A"),
					new Beat(startTime + gap * 175, "S"), new Beat(startTime + gap * 175, "D"),
					new Beat(startTime + gap * 175, "F"), new Beat(startTime + gap * 176, "A"),
					new Beat(startTime + gap * 176, "L"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 176, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 177, "S"),
					new Beat(startTime + gap * 177, "R"), new Beat(startTime + gap * 177, "SPACE"),
					new Beat(startTime + gap * 179, "A"), new Beat(startTime + gap * 179, "D"),
					new Beat(startTime + gap * 180, "S"), new Beat(startTime + gap * 180, "SPACE"),
					new Beat(startTime + gap * 181, "F"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "L"), new Beat(startTime + gap * 183, "R"),
					
					new Beat(startTime + gap * 185, "SPACE"), new Beat(startTime + gap * 187, "F"),
					new Beat(startTime + gap * 189, "D"), new Beat(startTime + gap * 191, "S"),
					new Beat(startTime + gap * 193, "A"), new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 197, "SPACE"), new Beat(startTime + gap * 199, "A"),
					new Beat(startTime + gap * 201, "S"), new Beat(startTime + gap * 203, "R"),
					new Beat(startTime + gap * 205, "L"), new Beat(startTime + gap * 207, "W"),
					new Beat(startTime + gap * 209, "W"), new Beat(startTime + gap * 211, "A"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 215, "D"),
					new Beat(startTime + gap * 217, "F"), new Beat(startTime + gap * 219, "A"),
					new Beat(startTime + gap * 221, "L"), new Beat(startTime + gap * 223, "F"),
					new Beat(startTime + gap * 225, "S"), new Beat(startTime + gap * 227, "D"),
					};
		} else if (titleName.equals("Joakim Karud - Wild Flower") && difficulty.equals("Hard")) {
			
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
		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Easy")) {
			
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
					new Beat(startTime + gap * 34, "A"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "L"), new Beat(startTime + gap * 37, "W"),
					new Beat(startTime + gap * 38, "R"), new Beat(startTime + gap * 40, "W"),
					new Beat(startTime + gap * 41, "W"), new Beat(startTime + gap * 43, "F"),
					new Beat(startTime + gap * 45, "SPACE"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "L"), new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 49, "W"), new Beat(startTime + gap * 49, "A"),
					new Beat(startTime + gap * 51, "F"), new Beat(startTime + gap * 52, "SPACE"),
					new Beat(startTime + gap * 54, "A"), new Beat(startTime + gap * 55, "S"),
					new Beat(startTime + gap * 56, "D"), new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "SPACE"), new Beat(startTime + gap * 59, "L"),
					new Beat(startTime + gap * 60, "W"), new Beat(startTime + gap * 61, "R"),
					new Beat(startTime + gap * 62, "W"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 64, "SPACE"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 66, "D"), new Beat(startTime + gap * 67, "S"),
					new Beat(startTime + gap * 68, "A"), new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 69, "SPACE"), new Beat(startTime + gap * 70, "A"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 71, "R"),
					new Beat(startTime + gap * 71, "L"), new Beat(startTime + gap * 72, "W"),
					new Beat(startTime + gap * 73, "W"), new Beat(startTime + gap * 75, "A"),
					new Beat(startTime + gap * 75, "S"), new Beat(startTime + gap * 75, "D"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 76, "A"),
					new Beat(startTime + gap * 76, "L"), new Beat(startTime + gap * 76, "F"),
					new Beat(startTime + gap * 76, "S"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"), new Beat(startTime + gap * 77, "S"),
					new Beat(startTime + gap * 77, "R"), new Beat(startTime + gap * 77, "SPACE"),
					new Beat(startTime + gap * 79, "A"), new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 80, "S"), new Beat(startTime + gap * 80, "SPACE"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "F"),
					new Beat(startTime + gap * 83, "L"), new Beat(startTime + gap * 83, "R"),
					new Beat(startTime + gap * 84, "L"), new Beat(startTime + gap * 84, "R"),
					new Beat(startTime + gap * 85, "W"), new Beat(startTime + gap * 85, "SPACE"),
					new Beat(startTime + gap * 86, "L"), new Beat(startTime + gap * 86, "W"),
					new Beat(startTime + gap * 87, "W"), new Beat(startTime + gap * 87, "R"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 89, "R"),
					new Beat(startTime + gap * 90, "SPACE"), new Beat(startTime + gap * 90, "W"),
					new Beat(startTime + gap * 91, "A"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 91, "L"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "SPACE"), new Beat(startTime + gap * 93, "W"),
					new Beat(startTime + gap * 94, "L"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 94, "R"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 95, "W"), new Beat(startTime + gap * 95, "F"),
					new Beat(startTime + gap * 95, "SPACE"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 96, "L"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 97, "W"), new Beat(startTime + gap * 97, "A"),
					new Beat(startTime + gap * 97, "F"), new Beat(startTime + gap * 98, "SPACE"),
					new Beat(startTime + gap * 98, "A"), new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 100, "D"), new Beat(startTime + gap * 100, "F"),
					new Beat(startTime + gap * 100, "SPACE"), new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 101, "W"), new Beat(startTime + gap * 102, "R"),
					new Beat(startTime + gap * 103, "W"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "SPACE"), new Beat(startTime + gap * 105, "F"),
					new Beat(startTime + gap * 105, "D"), new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 105, "A"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 106, "SPACE"), new Beat(startTime + gap * 107, "A"),
					new Beat(startTime + gap * 108, "F"), new Beat(startTime + gap * 109, "SPACE"),
					new Beat(startTime + gap * 110, "R"), new Beat(startTime + gap * 111, "W"),
					new Beat(startTime + gap * 112, "A"), new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 112, "L"), new Beat(startTime + gap * 112, "R"),
					new Beat(startTime + gap * 113, "S"), new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "L"), new Beat(startTime + gap * 114, "R"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 115, "W"),
					new Beat(startTime + gap * 115, "S"), new Beat(startTime + gap * 115, "SPACE"),
					new Beat(startTime + gap * 117, "R"), new Beat(startTime + gap * 117, "SPACE"),
					new Beat(startTime + gap * 117, "A"), new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 118, "SPACE"),
					new Beat(startTime + gap * 120, "A"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "D"), new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 124, "SPACE"), new Beat(startTime + gap * 125, "A"),
					new Beat(startTime + gap * 126, "S"), new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 128, "F"), new Beat(startTime + gap * 129, "SPACE"),
					
					new Beat(startTime + gap * 130, "S"), new Beat(startTime + gap * 131, "L"),
					new Beat(startTime + gap * 132, "D"), new Beat(startTime + gap * 133, "A"),
					new Beat(startTime + gap * 133, "D"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 135, "SPACE"), new Beat(startTime + gap * 136, "SPACE"),
					new Beat(startTime + gap * 137, "SPACE"), new Beat(startTime + gap * 137, "L"),
					new Beat(startTime + gap * 139, "A"), new Beat(startTime + gap * 139, "S"),
					new Beat(startTime + gap * 139, "D"), new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 139, "SPACE"), new Beat(startTime + gap * 139, "L"),
					new Beat(startTime + gap * 140, "W"), new Beat(startTime + gap * 141, "R"),
					
					new Beat(startTime + gap * 142, "W"), new Beat(startTime + gap * 143, "F"),
					new Beat(startTime + gap * 145, "SPACE"), new Beat(startTime + gap * 146, "S"),
					new Beat(startTime + gap * 148, "L"), new Beat(startTime + gap * 148, "S"),
					new Beat(startTime + gap * 149, "W"), new Beat(startTime + gap * 149, "A"),
					new Beat(startTime + gap * 151, "F"), new Beat(startTime + gap * 152, "SPACE"),
					new Beat(startTime + gap * 154, "A"), new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "D"), new Beat(startTime + gap * 157, "F"),
					new Beat(startTime + gap * 158, "SPACE"), new Beat(startTime + gap * 159, "L"),
					new Beat(startTime + gap * 160, "W"), new Beat(startTime + gap * 161, "R"),
					new Beat(startTime + gap * 162, "W"), new Beat(startTime + gap * 163, "L"),
					new Beat(startTime + gap * 164, "SPACE"), new Beat(startTime + gap * 165, "F"),
					new Beat(startTime + gap * 166, "D"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 168, "A"), new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 169, "SPACE"), new Beat(startTime + gap * 170, "A"),
					new Beat(startTime + gap * 170, "S"), new Beat(startTime + gap * 171, "R"),
					new Beat(startTime + gap * 171, "L"), new Beat(startTime + gap * 172, "W"),
					new Beat(startTime + gap * 173, "W"), new Beat(startTime + gap * 175, "A"),
					new Beat(startTime + gap * 175, "S"), new Beat(startTime + gap * 175, "D"),
					new Beat(startTime + gap * 175, "F"), new Beat(startTime + gap * 176, "A"),
					new Beat(startTime + gap * 176, "L"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 176, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 177, "S"),
					new Beat(startTime + gap * 177, "R"), new Beat(startTime + gap * 177, "SPACE"),
					new Beat(startTime + gap * 179, "A"), new Beat(startTime + gap * 179, "D"),
					new Beat(startTime + gap * 180, "S"), new Beat(startTime + gap * 180, "SPACE"),
					new Beat(startTime + gap * 181, "F"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "L"), new Beat(startTime + gap * 183, "R"),
					
					new Beat(startTime + gap * 185, "SPACE"), new Beat(startTime + gap * 187, "F"),
					new Beat(startTime + gap * 189, "D"), new Beat(startTime + gap * 191, "S"),
					new Beat(startTime + gap * 193, "A"), new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 197, "SPACE"), new Beat(startTime + gap * 199, "A"),
					new Beat(startTime + gap * 201, "S"), new Beat(startTime + gap * 203, "R"),
					new Beat(startTime + gap * 205, "L"), new Beat(startTime + gap * 207, "W"),
					new Beat(startTime + gap * 209, "W"), new Beat(startTime + gap * 211, "A"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 215, "D"),
					new Beat(startTime + gap * 217, "F"), new Beat(startTime + gap * 219, "A"),
					new Beat(startTime + gap * 221, "L"), new Beat(startTime + gap * 223, "F"),
					new Beat(startTime + gap * 225, "S"), new Beat(startTime + gap * 227, "D"),
					};
		} else if (titleName.equals("Bensound - Energy") && difficulty.equals("Hard")) {
			
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
		} else if (titleName.equals("MinSu - ungjang") && difficulty.equals("Easy")) {
			
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
					new Beat(startTime + gap * 34, "A"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "L"), new Beat(startTime + gap * 37, "W"),
					new Beat(startTime + gap * 38, "R"), new Beat(startTime + gap * 40, "W"),
					new Beat(startTime + gap * 41, "W"), new Beat(startTime + gap * 43, "F"),
					new Beat(startTime + gap * 45, "SPACE"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "L"), new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 49, "W"), new Beat(startTime + gap * 49, "A"),
					new Beat(startTime + gap * 51, "F"), new Beat(startTime + gap * 52, "SPACE"),
					new Beat(startTime + gap * 54, "A"), new Beat(startTime + gap * 55, "S"),
					new Beat(startTime + gap * 56, "D"), new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "SPACE"), new Beat(startTime + gap * 59, "L"),
					new Beat(startTime + gap * 60, "W"), new Beat(startTime + gap * 61, "R"),
					new Beat(startTime + gap * 62, "W"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 64, "SPACE"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 66, "D"), new Beat(startTime + gap * 67, "S"),
					new Beat(startTime + gap * 68, "A"), new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 69, "SPACE"), new Beat(startTime + gap * 70, "A"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 71, "R"),
					new Beat(startTime + gap * 71, "L"), new Beat(startTime + gap * 72, "W"),
					new Beat(startTime + gap * 73, "W"), new Beat(startTime + gap * 75, "A"),
					new Beat(startTime + gap * 75, "S"), new Beat(startTime + gap * 75, "D"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 76, "A"),
					new Beat(startTime + gap * 76, "L"), new Beat(startTime + gap * 76, "F"),
					new Beat(startTime + gap * 76, "S"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"), new Beat(startTime + gap * 77, "S"),
					new Beat(startTime + gap * 77, "R"), new Beat(startTime + gap * 77, "SPACE"),
					new Beat(startTime + gap * 79, "A"), new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 80, "S"), new Beat(startTime + gap * 80, "SPACE"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "F"),
					new Beat(startTime + gap * 83, "L"), new Beat(startTime + gap * 83, "R"),
					new Beat(startTime + gap * 84, "L"), new Beat(startTime + gap * 84, "R"),
					new Beat(startTime + gap * 85, "W"), new Beat(startTime + gap * 85, "SPACE"),
					new Beat(startTime + gap * 86, "L"), new Beat(startTime + gap * 86, "W"),
					new Beat(startTime + gap * 87, "W"), new Beat(startTime + gap * 87, "R"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 89, "R"),
					new Beat(startTime + gap * 90, "SPACE"), new Beat(startTime + gap * 90, "W"),
					new Beat(startTime + gap * 91, "A"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 91, "L"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "SPACE"), new Beat(startTime + gap * 93, "W"),
					new Beat(startTime + gap * 94, "L"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 94, "R"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 95, "W"), new Beat(startTime + gap * 95, "F"),
					new Beat(startTime + gap * 95, "SPACE"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 96, "L"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 97, "W"), new Beat(startTime + gap * 97, "A"),
					new Beat(startTime + gap * 97, "F"), new Beat(startTime + gap * 98, "SPACE"),
					new Beat(startTime + gap * 98, "A"), new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 100, "D"), new Beat(startTime + gap * 100, "F"),
					new Beat(startTime + gap * 100, "SPACE"), new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 101, "W"), new Beat(startTime + gap * 102, "R"),
					new Beat(startTime + gap * 103, "W"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "SPACE"), new Beat(startTime + gap * 105, "F"),
					new Beat(startTime + gap * 105, "D"), new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 105, "A"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 106, "SPACE"), new Beat(startTime + gap * 107, "A"),
					new Beat(startTime + gap * 108, "F"), new Beat(startTime + gap * 109, "SPACE"),
					new Beat(startTime + gap * 110, "R"), new Beat(startTime + gap * 111, "W"),
					new Beat(startTime + gap * 112, "A"), new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 112, "L"), new Beat(startTime + gap * 112, "R"),
					new Beat(startTime + gap * 113, "S"), new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "L"), new Beat(startTime + gap * 114, "R"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 115, "W"),
					new Beat(startTime + gap * 115, "S"), new Beat(startTime + gap * 115, "SPACE"),
					new Beat(startTime + gap * 117, "R"), new Beat(startTime + gap * 117, "SPACE"),
					new Beat(startTime + gap * 117, "A"), new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 118, "SPACE"),
					new Beat(startTime + gap * 120, "A"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "D"), new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 124, "SPACE"), new Beat(startTime + gap * 125, "A"),
					new Beat(startTime + gap * 126, "S"), new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 128, "F"), new Beat(startTime + gap * 129, "SPACE"),
					};
		} else if (titleName.equals("MinSu - ungjang") && difficulty.equals("Hard")) {
			
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
					new Beat(startTime + gap * 32, "W"), new Beat(startTime + gap * 32, "R"), 
					};
		} else if (titleName.equals("MinSu - YEP") && difficulty.equals("Easy")) {
			
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
					new Beat(startTime + gap * 34, "A"), new Beat(startTime + gap * 34, "S"),
					new Beat(startTime + gap * 34, "D"), new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "L"), new Beat(startTime + gap * 37, "W"),
					new Beat(startTime + gap * 38, "R"), new Beat(startTime + gap * 40, "W"),
					new Beat(startTime + gap * 41, "W"), new Beat(startTime + gap * 43, "F"),
					new Beat(startTime + gap * 45, "SPACE"), new Beat(startTime + gap * 46, "S"),
					new Beat(startTime + gap * 48, "L"), new Beat(startTime + gap * 48, "S"),
					new Beat(startTime + gap * 49, "W"), new Beat(startTime + gap * 49, "A"),
					new Beat(startTime + gap * 51, "F"), new Beat(startTime + gap * 52, "SPACE"),
					new Beat(startTime + gap * 54, "A"), new Beat(startTime + gap * 55, "S"),
					new Beat(startTime + gap * 56, "D"), new Beat(startTime + gap * 57, "F"),
					new Beat(startTime + gap * 58, "SPACE"), new Beat(startTime + gap * 59, "L"),
					new Beat(startTime + gap * 60, "W"), new Beat(startTime + gap * 61, "R"),
					new Beat(startTime + gap * 62, "W"), new Beat(startTime + gap * 63, "L"),
					new Beat(startTime + gap * 64, "SPACE"), new Beat(startTime + gap * 65, "F"),
					new Beat(startTime + gap * 66, "D"), new Beat(startTime + gap * 67, "S"),
					new Beat(startTime + gap * 68, "A"), new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 69, "SPACE"), new Beat(startTime + gap * 70, "A"),
					new Beat(startTime + gap * 70, "S"), new Beat(startTime + gap * 71, "R"),
					new Beat(startTime + gap * 71, "L"), new Beat(startTime + gap * 72, "W"),
					new Beat(startTime + gap * 73, "W"), new Beat(startTime + gap * 75, "A"),
					new Beat(startTime + gap * 75, "S"), new Beat(startTime + gap * 75, "D"),
					new Beat(startTime + gap * 75, "F"), new Beat(startTime + gap * 76, "A"),
					new Beat(startTime + gap * 76, "L"), new Beat(startTime + gap * 76, "F"),
					new Beat(startTime + gap * 76, "S"), new Beat(startTime + gap * 76, "D"),
					new Beat(startTime + gap * 77, "D"), new Beat(startTime + gap * 77, "S"),
					new Beat(startTime + gap * 77, "R"), new Beat(startTime + gap * 77, "SPACE"),
					new Beat(startTime + gap * 79, "A"), new Beat(startTime + gap * 79, "D"),
					new Beat(startTime + gap * 80, "S"), new Beat(startTime + gap * 80, "SPACE"),
					new Beat(startTime + gap * 81, "F"), new Beat(startTime + gap * 81, "F"),
					new Beat(startTime + gap * 83, "L"), new Beat(startTime + gap * 83, "R"),
					new Beat(startTime + gap * 84, "L"), new Beat(startTime + gap * 84, "R"),
					new Beat(startTime + gap * 85, "W"), new Beat(startTime + gap * 85, "SPACE"),
					new Beat(startTime + gap * 86, "L"), new Beat(startTime + gap * 86, "W"),
					new Beat(startTime + gap * 87, "W"), new Beat(startTime + gap * 87, "R"),
					new Beat(startTime + gap * 88, "L"), new Beat(startTime + gap * 88, "D"),
					new Beat(startTime + gap * 89, "D"), new Beat(startTime + gap * 89, "R"),
					new Beat(startTime + gap * 90, "SPACE"), new Beat(startTime + gap * 90, "W"),
					new Beat(startTime + gap * 91, "A"), new Beat(startTime + gap * 91, "D"),
					new Beat(startTime + gap * 91, "L"), new Beat(startTime + gap * 91, "F"),
					new Beat(startTime + gap * 93, "SPACE"), new Beat(startTime + gap * 93, "W"),
					new Beat(startTime + gap * 94, "L"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 94, "R"), new Beat(startTime + gap * 94, "W"),
					new Beat(startTime + gap * 95, "W"), new Beat(startTime + gap * 95, "F"),
					new Beat(startTime + gap * 95, "SPACE"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 96, "L"), new Beat(startTime + gap * 96, "S"),
					new Beat(startTime + gap * 97, "W"), new Beat(startTime + gap * 97, "A"),
					new Beat(startTime + gap * 97, "F"), new Beat(startTime + gap * 98, "SPACE"),
					new Beat(startTime + gap * 98, "A"), new Beat(startTime + gap * 98, "S"),
					new Beat(startTime + gap * 100, "D"), new Beat(startTime + gap * 100, "F"),
					new Beat(startTime + gap * 100, "SPACE"), new Beat(startTime + gap * 100, "L"),
					new Beat(startTime + gap * 101, "W"), new Beat(startTime + gap * 102, "R"),
					new Beat(startTime + gap * 103, "W"), new Beat(startTime + gap * 104, "L"),
					new Beat(startTime + gap * 105, "SPACE"), new Beat(startTime + gap * 105, "F"),
					new Beat(startTime + gap * 105, "D"), new Beat(startTime + gap * 105, "S"),
					new Beat(startTime + gap * 105, "A"), new Beat(startTime + gap * 106, "F"),
					new Beat(startTime + gap * 106, "SPACE"), new Beat(startTime + gap * 107, "A"),
					new Beat(startTime + gap * 108, "F"), new Beat(startTime + gap * 109, "SPACE"),
					new Beat(startTime + gap * 110, "R"), new Beat(startTime + gap * 111, "W"),
					new Beat(startTime + gap * 112, "A"), new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 112, "L"), new Beat(startTime + gap * 112, "R"),
					new Beat(startTime + gap * 113, "S"), new Beat(startTime + gap * 113, "F"),
					new Beat(startTime + gap * 114, "L"), new Beat(startTime + gap * 114, "R"),
					new Beat(startTime + gap * 114, "D"), new Beat(startTime + gap * 115, "W"),
					new Beat(startTime + gap * 115, "S"), new Beat(startTime + gap * 115, "SPACE"),
					new Beat(startTime + gap * 117, "R"), new Beat(startTime + gap * 117, "SPACE"),
					new Beat(startTime + gap * 117, "A"), new Beat(startTime + gap * 117, "D"),
					new Beat(startTime + gap * 118, "S"), new Beat(startTime + gap * 118, "SPACE"),
					new Beat(startTime + gap * 120, "A"), new Beat(startTime + gap * 121, "S"),
					new Beat(startTime + gap * 122, "D"), new Beat(startTime + gap * 123, "F"),
					new Beat(startTime + gap * 124, "SPACE"), new Beat(startTime + gap * 125, "A"),
					new Beat(startTime + gap * 126, "S"), new Beat(startTime + gap * 127, "D"),
					new Beat(startTime + gap * 128, "F"), new Beat(startTime + gap * 129, "SPACE"),
					
					new Beat(startTime + gap * 130, "S"), new Beat(startTime + gap * 131, "L"),
					new Beat(startTime + gap * 132, "D"), new Beat(startTime + gap * 133, "A"),
					new Beat(startTime + gap * 133, "D"), new Beat(startTime + gap * 134, "D"),
					new Beat(startTime + gap * 135, "SPACE"), new Beat(startTime + gap * 136, "SPACE"),
					new Beat(startTime + gap * 137, "SPACE"), new Beat(startTime + gap * 137, "L"),
					new Beat(startTime + gap * 139, "A"), new Beat(startTime + gap * 139, "S"),
					new Beat(startTime + gap * 139, "D"), new Beat(startTime + gap * 139, "F"),
					new Beat(startTime + gap * 139, "SPACE"), new Beat(startTime + gap * 139, "L"),
					new Beat(startTime + gap * 140, "W"), new Beat(startTime + gap * 141, "R"),
					
					new Beat(startTime + gap * 142, "W"), new Beat(startTime + gap * 143, "F"),
					new Beat(startTime + gap * 145, "SPACE"), new Beat(startTime + gap * 146, "S"),
					new Beat(startTime + gap * 148, "L"), new Beat(startTime + gap * 148, "S"),
					new Beat(startTime + gap * 149, "W"), new Beat(startTime + gap * 149, "A"),
					new Beat(startTime + gap * 151, "F"), new Beat(startTime + gap * 152, "SPACE"),
					new Beat(startTime + gap * 154, "A"), new Beat(startTime + gap * 155, "S"),
					new Beat(startTime + gap * 156, "D"), new Beat(startTime + gap * 157, "F"),
					new Beat(startTime + gap * 158, "SPACE"), new Beat(startTime + gap * 159, "L"),
					new Beat(startTime + gap * 160, "W"), new Beat(startTime + gap * 161, "R"),
					new Beat(startTime + gap * 162, "W"), new Beat(startTime + gap * 163, "L"),
					new Beat(startTime + gap * 164, "SPACE"), new Beat(startTime + gap * 165, "F"),
					new Beat(startTime + gap * 166, "D"), new Beat(startTime + gap * 167, "S"),
					new Beat(startTime + gap * 168, "A"), new Beat(startTime + gap * 169, "F"),
					new Beat(startTime + gap * 169, "SPACE"), new Beat(startTime + gap * 170, "A"),
					new Beat(startTime + gap * 170, "S"), new Beat(startTime + gap * 171, "R"),
					new Beat(startTime + gap * 171, "L"), new Beat(startTime + gap * 172, "W"),
					new Beat(startTime + gap * 173, "W"), new Beat(startTime + gap * 175, "A"),
					new Beat(startTime + gap * 175, "S"), new Beat(startTime + gap * 175, "D"),
					new Beat(startTime + gap * 175, "F"), new Beat(startTime + gap * 176, "A"),
					new Beat(startTime + gap * 176, "L"), new Beat(startTime + gap * 176, "F"),
					new Beat(startTime + gap * 176, "S"), new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 177, "D"), new Beat(startTime + gap * 177, "S"),
					new Beat(startTime + gap * 177, "R"), new Beat(startTime + gap * 177, "SPACE"),
					new Beat(startTime + gap * 179, "A"), new Beat(startTime + gap * 179, "D"),
					new Beat(startTime + gap * 180, "S"), new Beat(startTime + gap * 180, "SPACE"),
					new Beat(startTime + gap * 181, "F"), new Beat(startTime + gap * 181, "F"),
					new Beat(startTime + gap * 183, "L"), new Beat(startTime + gap * 183, "R"),
					
					new Beat(startTime + gap * 185, "SPACE"), new Beat(startTime + gap * 187, "F"),
					new Beat(startTime + gap * 189, "D"), new Beat(startTime + gap * 191, "S"),
					new Beat(startTime + gap * 193, "A"), new Beat(startTime + gap * 195, "F"),
					new Beat(startTime + gap * 197, "SPACE"), new Beat(startTime + gap * 199, "A"),
					new Beat(startTime + gap * 201, "S"), new Beat(startTime + gap * 203, "R"),
					new Beat(startTime + gap * 205, "L"), new Beat(startTime + gap * 207, "W"),
					new Beat(startTime + gap * 209, "W"), new Beat(startTime + gap * 211, "A"),
					new Beat(startTime + gap * 213, "S"), new Beat(startTime + gap * 215, "D"),
					new Beat(startTime + gap * 217, "F"), new Beat(startTime + gap * 219, "A"),
					new Beat(startTime + gap * 221, "L"), new Beat(startTime + gap * 223, "F"),
					new Beat(startTime + gap * 225, "S"), new Beat(startTime + gap * 227, "D"),
					new Beat(startTime + gap * 228, "D")
					};
		} else if (titleName.equals("MinSu - YEP") && difficulty.equals("Hard")) {
			
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
			blueFlareImage = new ImageIcon(Main.class.getResource("../images/FlareImage.png")).getImage();
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
