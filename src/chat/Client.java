package chat;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import RhythmGame.DynamicBeat;
import RhythmGame.Main;

public class Client extends JFrame implements WindowListener{ // client UI

	JTextField tfSend;
	JLabel laMessage;
	
	JTextArea textArea;
	
	JScrollPane spTextArea;
	JScrollPane spList;
	
	JList<String> liPerson;
	
	JButton btChange;
	JButton btExit;
	static JButton btChoice;
	
	JPanel p;
	
	private int selectedMusicIndex;
	private int index;
	
	public Client() {
		
		addWindowListener(this);
		selectedMusicIndex = -1;
		index = -1;
		
		setTitle("ChatRoom");
		tfSend = new JTextField(15);
		laMessage = new JLabel("Message");
		
		textArea = new JTextArea();
		textArea.setLineWrap(true); // auto CR, LF
		liPerson = new JList<String>();
		
		spTextArea = new JScrollPane(textArea);
		spList = new JScrollPane(liPerson);
		
		btChange = new JButton("ChangeTitle");
		btExit = new JButton("Exit");
		btChoice = new JButton("Choice");

		p = new JPanel();
		spTextArea.setBounds(10, 10, 380, 390);
		laMessage.setBounds(10, 410, 60, 30);
		tfSend.setBounds(70, 410, 320, 30);
		// spList.setBounds(400, 10, 120, 350);
		spList.setBounds(400, 10, 120, 310);
		btChoice.setBounds(400, 330, 120, 30);
		btChange.setBounds(400, 370, 120, 30);
		btExit.setBounds(400, 410, 120, 30);
		
		p.setLayout(null);
		p.setBackground(Color.BLACK);
		p.add(spTextArea);
		p.add(laMessage);
		p.add(tfSend);
		p.add(spList);
		p.add(btChange);
		p.add(btExit);
		p.add(btChoice);
		
		
		setResizable(false);
		setLocationRelativeTo(null);
		btChoice.setVisible(false);
		add(p);
		setBounds(550, 200, 550, 500);	
		setVisible(false);
		tfSend.requestFocus();
	}
	
	public void setSelectedMusicIndex(int selectedMusicIndex) {
		this.selectedMusicIndex = selectedMusicIndex;
	}
	
	public int getSelectedMusicIndex() {
		return selectedMusicIndex;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		dispose();
		new MainChat();
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
