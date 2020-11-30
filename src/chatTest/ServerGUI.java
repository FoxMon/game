package chatTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.*;
import java.io.*;

public class ServerGUI extends JFrame implements ActionListener {

	private JTextArea textArea = new JTextArea(40, 25);
	private JTextField textField = new JTextField(25);
	
	private ServerBackground server = new ServerBackground();
	
	public ServerGUI() throws IOException { // constructor
		add(textArea, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        textField.addActionListener(this);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(200, 100, 400, 600);
        setTitle("Server");
        
        server.setGUI(this);
        server.init();
	}
	
	public void appendMessage(String msg) { // append
		textArea.append(msg);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { // event
		String msg = "Server : "+ textField.getText() + "\n";
        System.out.print(msg);
        server.sendMessage(msg);
        textField.setText(""); // reset
	}
	
	public static void main(String[] args) throws IOException {
		
		new ServerGUI();
	}
}
