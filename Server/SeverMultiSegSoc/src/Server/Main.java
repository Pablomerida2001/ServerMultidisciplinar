package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.mail.MessagingException;

import Database.DbConnection;
import Database.Services.UserService.FindUser;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5000;
	
	public static void main(String[] args) {
		
		RecieveEmailThread emailThread = new RecieveEmailThread("vbay.sanjose@alumnado.fundacionloyola.net", "67757111", true);
		emailThread.start();
		
//		DbConnection dbconnection = new DbConnection();
//		try {
//			server = new ServerSocket(port);
//			while(true) {
//					socket = new Socket();		
//					System.out.println("Waiting connection...");
//					socket = server.accept();
//					ConnectionThread connection = new ConnectionThread(socket, dbconnection);
//					connection.start();
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}	
	}
}