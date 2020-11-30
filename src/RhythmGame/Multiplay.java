package RhythmGame;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Multiplay extends JFrame implements WindowListener{
	private ImageIcon signInButtonEnteredImage = new ImageIcon(
			Main.class.getResource("../images/startButtonEntered.png"));
	private ImageIcon signInButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon signUpButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	private ImageIcon signUpButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private JButton signInButton = new JButton(signInButtonBasicImage);
	private JButton signUpButton = new JButton(signUpButtonBasicImage);
	
	public Multiplay() {
		addWindowListener(this);
		setTitle("Multiplay");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		
		signInButton.setBounds(40, 200, 400, 100);
		signInButton.setBorderPainted(false);
		signInButton.setContentAreaFilled(false);
		signInButton.setFocusPainted(false);
		signInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signInButton.setIcon(signInButtonEnteredImage);
				signInButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				signInButton.setIcon(signInButtonBasicImage);
				signInButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				new Sign.SignInForm();
				setVisible(false);
			}
		});
		add(signInButton);
		
		signUpButton.setBounds(40, 200, 400, 100);
		signUpButton.setBorderPainted(false);
		signUpButton.setContentAreaFilled(false);
		signUpButton.setFocusPainted(false);
		signUpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signUpButton.setIcon(signUpButtonEnteredImage);
				signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
				buttonEnteredMusic.start();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				signUpButton.setIcon(signUpButtonBasicImage);
				signUpButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				new Sign.SignUpForm();
				setVisible(false);
			}
		});
		add(signUpButton);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		Main.dynamicBeat.setVisible(true);
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
