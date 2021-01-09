package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.mail.MessagingException;

import Database.DbConnection;
import Database.Services.UserService.FindUser;
import Mailer.Mailer;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5000;
	
	public static void main(String[] args) {
		
		 String host = "pop.gmail.com";//change accordingly  
		  String mailStoreType = "pop3";  
		  String username= "vitaliygbbay@gmail.com";  
		  String password= "vitvitvit";//change accordingly  
		  
//		  Mailer.receiveEmail(host, mailStoreType, username, password);  
//		  Mailer.check(host, mailStoreType, username, password);  
		  try {
			Mailer.readInboundEmails(host, mailStoreType, username, password);
		} catch (MessagingException e) {
			e.printStackTrace();
		}  
		
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