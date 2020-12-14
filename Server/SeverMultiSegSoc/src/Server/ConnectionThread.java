package Server;

import java.net.Socket;

public class ConnectionThread extends Thread{
	
	Socket socket;
	
	public ConnectionThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		
	}
	

}