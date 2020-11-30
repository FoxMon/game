package chatTest;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {

	private DataInputStream in;
    private DataOutputStream out;
    
	private Socket socket;
    
    private ClientGUI gui;
    
    private String msg;
    private String nickName;
    
    public final void setGui(ClientGUI gui) { // set client gui
        this.gui = gui;
    }
    
    public void connect() {
        try {
            socket = new Socket("127.0.0.1", 7777);
            
            System.out.println("Server connect...");
             
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
             
            // send nickname
            out.writeUTF(nickName); 
            
            System.out.println("Client : Send Success!!!!");
            
            while(in!=null){
                msg=in.readUTF();
                gui.appendMessage(msg);             
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setNickname(String nickName) {
        this.nickName = nickName;
    }
    
    public void sendMessage(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            System.out.println("Send error... " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
    	
    	ClientBackground clientBackground = new ClientBackground();
        clientBackground.connect();
    }
}
