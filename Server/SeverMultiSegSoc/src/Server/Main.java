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
	static int port = 5013;
	
	public static void main(String[] args) {
		
//		RecieveEmailThread emailThread;
//		try {
//			emailThread = new RecieveEmailThread("vbay.sanjose@alumnado.fundacionloyola.net", "67757111", true, true, null);
//			emailThread.start();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
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