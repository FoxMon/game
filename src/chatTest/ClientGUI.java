package chatTest;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientGUI extends JFrame implements ActionListener {
	
    private JTextArea textArea = new JTextArea(40, 25);
    private JTextField textField = new JTextField(25);
    
    private ClientBackground client = new ClientBackground();
    
    private static String nickName;
    
    public ClientGUI() { // constructor
        
        add(textArea, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
        textField.addActionListener(this);
         
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        setTitle("Client");
         
        client.setGui(this);
        client.setNickname(nickName);
        client.connect();
    }
    
    public void appendMessage(String msg) {
        textArea.append(msg);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) { // send Message
        String msg = nickName+ ":" + textField.getText()+"\n";
        client.sendMessage(msg);
        textField.setText("");
    }
    
    public static void main(String[] args) {
    	
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Set Nickname : ");
        
        nickName = scanner.nextLine();
        scanner.close();        
         
        new ClientGUI();
    }
}
