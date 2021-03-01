package Backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JTextArea;

public class ClientThread extends Thread{
	
	private Socket socket;
	private DataInputStream dis;
	private DataOutputStream dos;
	private Server server;
	private boolean isCanceled,passwordAccepted;
	private JTextArea textBox;
	
	public ClientThread(Socket socket, DataInputStream dis, DataOutputStream dos, Server server) {
		passwordAccepted = false;
		this.socket = socket;
		this.dis = dis;
		this.dos = dos;
		this.server = server;
		this.isCanceled = false;
		this.textBox = server.getTextBox();
	}
	
	@Override
	public void run(){
		while(true && !isCanceled) {
			try {
				String input = this.dis.readUTF();
				if(input.contains("!exit")) {
					cancel();
				}else {
					server.sendMessageToAll(input,this);
				}
			} catch (IOException e) {
				server.disposeThread(this);
				cancel();
			}
		}
		server.disposeThread(this);
	}
	
	public void cancel() {
		isCanceled = true;
		try {
			dis.close();
			dos.close();
			System.out.println("Closing Connection for: " + socket.getInetAddress());
		} catch (IOException e) {
			System.out.println("Something went wrong when closing connection for: " + socket.getInetAddress());
		}

	}
	
	public void sendMessageToClient(String message) {
		try {
			this.dos.writeUTF(message);
		} catch (IOException e) {
			System.out.print("Message failed to send: " +message + ", " + socket.getInetAddress());
		}
	}
	
}
