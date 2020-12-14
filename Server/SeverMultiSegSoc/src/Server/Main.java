package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5000;
	
	public static void main(String[] args) {
		DbConnection dbconnection = new DbConnection();
		try {
			server = new ServerSocket(port);
			while(true) {
					socket = new Socket();		
					System.out.println("Waiting connection...");
					socket = server.accept();
					ConnectionThread connection = new ConnectionThread(socket, dbconnection);
					connection.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}