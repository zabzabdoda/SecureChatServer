package Backend;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.JTextArea;

public class Server {
	
	public LinkedList<ClientThread> clients;
	private ServerSocket ss;
	private String name;
	private char[] password;
	private int port;
	private JTextArea textBox;
	
	public Server(String chatroom, char[] password, int port, JTextArea textBox) {
		this.name = chatroom;
		this.password = password;
		this.port = port;
		this.textBox = textBox;
	}
	
	public void startServer() {
		try {
			clients = new LinkedList<ClientThread>();
			ss = new ServerSocket(port);
			textBox.append("Server is now listening on port: " + port+"\n");
			while(true){
				Socket newUser = ss.accept();
				DataInputStream dis = new DataInputStream(newUser.getInputStream());
				DataOutputStream dos = new DataOutputStream(newUser.getOutputStream());
				textBox.append("A new user has joined: " + newUser.getInetAddress()+"\n");
				ClientThread t = new ClientThread(newUser,dis,dos,this);
				clients.add(t);
				t.sendMessageToClient("You have been connected to ip: " + ss.getInetAddress()+", port: " + ss.getLocalPort());
				t.sendMessageToClient("Type !exit to leave the server");
				t.start();
			}
			
		} catch (IOException e) {
		}finally {
			textBox.append("Server Shutting Down\n");
		}
	}
	
	public void sendMessageToAll(String message,ClientThread ct) {
		textBox.append(message+"\n");
		for (ClientThread client : clients){
			if(client != ct) {
				client.sendMessageToClient(message);
			}
		}
	}
	
	public void disposeThread(ClientThread t) {
		t.cancel();
		clients.remove(t);
	}
	
	public void closeServer() {
		sendMessageToAll("[Server] Server is shutting down...",null);
		for (ClientThread client : clients) {
			client.cancel();
		}
		clients.clear();
		try {
			ss.close();
		} catch (IOException e) {
		}
		ss = null;
	}
	
	public JTextArea getTextBox() {
		return textBox;
	}
	
	
}