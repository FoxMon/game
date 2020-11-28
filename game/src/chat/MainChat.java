package chat;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import RhythmGame.DynamicBeat;
import RhythmGame.KeyListener;
import RhythmGame.Main;

public class MainChat extends JFrame implements ActionListener, Runnable, WindowListener {

	JList<String> roomInfo, roomUser, waitInfo;
	JScrollPane spRoomInfo, spRoomPerson, spWaitInfo;
	JButton btCreate, btEnter, btExit;
	JPanel p;

	public static Client client;

	private BufferedReader in;
	private OutputStream out;
	private String selectedRoom;
	private String id = Sign.SignInForm.dto.getName();

	public MainChat() {

		addWindowListener(this);
		setTitle("WaitingRoom");

		client = new Client(); // first, create client frame

		roomInfo = new JList<String>();
		roomInfo.setBorder(new TitledBorder("Room Info"));
		roomInfo.addMouseListener(new MouseAdapter() { // mouse event
			@Override
			public void mouseClicked(MouseEvent e) {
				String str = roomInfo.getSelectedValue();

				if (str == null) {
					return;
				}

				System.out.println("STR = " + str);
				selectedRoom = str.substring(0, str.indexOf("-"));
				sendMessage("170|" + selectedRoom);
			}
		});

		roomUser = new JList<String>();
		roomUser.setBorder(new TitledBorder("User Information"));

		waitInfo = new JList<String>(new DefaultListModel());
		waitInfo.setBorder(new TitledBorder("Waiting Users Information"));

		spRoomInfo = new JScrollPane(roomInfo);
		spRoomPerson = new JScrollPane(roomUser);

		spWaitInfo = new JScrollPane(waitInfo);

		btCreate = new JButton("Create");
		btEnter = new JButton("Enter");
		btExit = new JButton("Exit");

		p = new JPanel();

		spRoomInfo.setBounds(10, 10, 300, 300);
		spRoomPerson.setBounds(320, 10, 150, 300);
		spWaitInfo.setBounds(10, 320, 300, 130);

		btCreate.setBounds(320, 330, 150, 30);
		btEnter.setBounds(320, 370, 150, 30);
		btExit.setBounds(320, 410, 150, 30);

		p.setLayout(null);
		p.setBackground(Color.GRAY);
		p.add(spRoomInfo);
		p.add(spRoomPerson);
		p.add(spWaitInfo);
		p.add(btCreate);
		p.add(btEnter);
		p.add(btExit);

		add(p);
		setBounds(300, 200, 500, 500);

		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		connect();// server Connect

		new Thread(this).start();// waiting for server message

		sendMessage("100|");// alarm

		// id = JOptionPane.showInputDialog(this, "Title:");
		sendMessage("150|" + id);
		eventListener();
	}

	private void eventListener() { // event

		// waiting room
		btCreate.addActionListener(this);
		btEnter.addActionListener(this);
		btExit.addActionListener(this);

		// client room
		client.tfSend.addActionListener(this);
		client.btChange.addActionListener(this);
		client.btExit.addActionListener(this);
		client.btChoice.addActionListener(this);

		// dynamic beat left, right, easy
		Main.dynamicBeat.leftButton.addActionListener(this);
		Main.dynamicBeat.rightButton.addActionListener(this);
		Main.dynamicBeat.easyButton.addActionListener(this);
		Main.dynamicBeat.hardButton.addActionListener(this);
		Main.dynamicBeat.addKeyListener(new KeyListener());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();

		if (ob == btCreate) {// request create room
			String title = JOptionPane.showInputDialog(this, "RoomTitle:");
			Client.btChoice.setVisible(true);
			client.setIndex(0);
			// send room title to server
			sendMessage("160|" + title);
			client.setTitle("CharRoom-[" + title + "]");
			sendMessage("175|");

			setVisible(false);
			client.setVisible(true);
			// Main.dynamicBeat.enterMain();
			// Main.dynamicBeat.setVisible(true);
		} else if (ob == btEnter) { // request enter room
			Client.btChoice.setVisible(false);
			if (selectedRoom == null) {
				JOptionPane.showMessageDialog(this, "Choice room!");
			} else {
				sendMessage("200|" + selectedRoom);
				sendMessage("175|");

				setVisible(false);
				client.setVisible(true);
			}
			// Main.dynamicBeat.enterMain();
			// Main.dynamicBeat.setVisible(true);

		}  else if (ob == btExit) { // exit -> end
			Main.dynamicBeat.setVisible(true);
			Main.dynamicBeat.backRoomButton.setVisible(false);
			Main.dynamicBeat.backIntroButton.setVisible(false);
			//waitInfo.remove();
			client.dispose();
			dispose();
		} else if (ob == client.btExit) { // request exit room

			sendMessage("400|");
			client.setVisible(false);
			setVisible(true);

			// 멀티기능 비활성화
			Main.dynamicBeat.setVisible(false);

		} else if (ob == client.btChange) { // to change title
			String title = JOptionPane.showInputDialog(this, "RoomTitle:");
			sendMessage("400|" + title);
			client.setTitle("CharRoom-[" + title + "]");
			// 멀티기능 비활성화
			Main.dynamicBeat.setVisible(false);

		} else if (ob == client.tfSend) {// request send message

			String msg = client.tfSend.getText();

			if (msg.length() > 0) {
				sendMessage("300|" + msg);
				client.tfSend.setText("");
			}
		}else if (ob == client.btChoice) { // choice music button
			
			sendMessage("450|");
		} else if (ob == Main.dynamicBeat.leftButton) { // left

			client.setSelectedMusicIndex(Main.dynamicBeat.getNowSelected());
			String msg = Integer.toString(client.getSelectedMusicIndex());
			sendMessage("500|" + msg);
		} else if (ob == Main.dynamicBeat.rightButton) { // right

			client.setSelectedMusicIndex(Main.dynamicBeat.getNowSelected());
			String msg = Integer.toString(client.getSelectedMusicIndex());
			sendMessage("600|" + msg);
		} else if (ob == Main.dynamicBeat.easyButton) { // easy

			if (client.getSelectedMusicIndex() == -1) {
				client.setSelectedMusicIndex(Main.dynamicBeat.getNowSelected());
			}

			String msg = Integer.toString(client.getSelectedMusicIndex());
			sendMessage("700|" + msg);
		} else if (ob == Main.dynamicBeat.hardButton) { // hard

			if (client.getSelectedMusicIndex() == -1) {
				client.setSelectedMusicIndex(Main.dynamicBeat.getNowSelected());
			}

			String msg = Integer.toString(client.getSelectedMusicIndex());
			sendMessage("800|" + msg);
		}
	}

	public void connect() { // request server for connection

		try {
			Socket socket = new Socket("localhost", 5678);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = socket.getOutputStream();
		} catch (UnknownHostException e) {
			System.out.println("Client connection error... " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) { // send to server

		try {
			out.write((message + "\n").getBytes());
		} catch (IOException e) {
			System.out.println("Message send error... " + e.getMessage());
		}
	}

	@Override
	public void run() {

		try {

			while (true) {

				String message = in.readLine();// message -> server message
				String msgs[] = message.split("\\|");
				String protocol = msgs[0];

				switch (protocol) {

				case "300":
					client.textArea.append(msgs[1] + "\n");
					client.textArea.setCaretPosition(client.textArea.getText().length());
					break;

				case "160": // create room
					if (msgs.length > 1) {
						String roomNames[] = msgs[1].split(",");
						roomInfo.setListData(roomNames);
					}
					break;

				case "170": // in roby user info
					String roomInwons[] = msgs[1].split(",");
					roomUser.setListData(roomInwons);
					break;

				case "175": // in room user info
					String myRoomInwons[] = msgs[1].split(",");
					client.liPerson.setListData(myRoomInwons);
					break;

				case "180": // robby user info
					String waitNames[] = msgs[1].split(",");
					waitInfo.setListData(waitNames);
					break;

				case "200": // enter room
					client.textArea.append("[" + msgs[1] + "] entered.\n");
					client.textArea.setCaretPosition(client.textArea.getText().length());
					break;

				case "400": // room exit
					client.textArea.append("[" + msgs[1] + "] exit.\n");
					client.textArea.setCaretPosition(client.textArea.getText().length());
					break;

				case "202": // get room title
					client.setTitle("CharRoom-[" + msgs[1] + "]");
					break;

				case "450": // choice
					Main.dynamicBeat.enterMain();
					Main.dynamicBeat.setVisible(true);
					Main.dynamicBeat.backIntroButton.setVisible(false);
					Main.dynamicBeat.backRoomButton.setVisible(true);
					if (Integer.parseInt(msgs[1]) == client.getIndex()) {
						Main.dynamicBeat.leftButton.setVisible(true);
						Main.dynamicBeat.rightButton.setVisible(true);
						Main.dynamicBeat.easyButton.setVisible(true);
						Main.dynamicBeat.hardButton.setVisible(true);
					} else {
						Main.dynamicBeat.leftButton.setVisible(false);
						Main.dynamicBeat.rightButton.setVisible(false);
						Main.dynamicBeat.easyButton.setVisible(false);
						Main.dynamicBeat.hardButton.setVisible(false);
					}

					client.setVisible(false);
					break;

				case "500": // music choice left button
					client.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
					Main.dynamicBeat.selectTrack(client.getSelectedMusicIndex());
					break;

				case "600": // music choice right button
					client.setSelectedMusicIndex(Integer.parseInt(msgs[1]));
					Main.dynamicBeat.selectTrack(client.getSelectedMusicIndex());
					break;

				case "700": // easy button start
					int index = Integer.parseInt(msgs[1]);
					Main.dynamicBeat.gameStart(index, "Easy");
					break;

				case "800": // hard button start
					int idx = Integer.parseInt(msgs[1]);
					Main.dynamicBeat.gameStart(idx, "Hard");
					break;
				}
			}
		} catch (IOException e) {
			System.out.println("Run error... " + e.getMessage());
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		Main.dynamicBeat.setVisible(true);
		
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