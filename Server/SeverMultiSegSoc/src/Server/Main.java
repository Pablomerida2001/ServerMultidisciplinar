package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.Session;

import Database.DbConnection;
import Database.Services.UserService.FindUser;
import Mailer.Mailer;

public class Main {

	static ServerSocket server; 
	static Socket socket;
	static int port = 5013;
	
	public static void main(String[] args) {
//		RecieveEmailThread emailThread;
//		try {
//			emailThread = new RecieveEmailThread("seguridadsocialmulti@gmail.com", "usuario1+", true, true, null);
//			emailThread.start();
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Session session = Mailer.getConnectionToPOP3("vitaliygbbay@gmail.com", "vitvitvit");
		try {
			boolean result = Mailer.send(session, "vitaliygbbay@gmail.com", "vitvitvit", "vitaliygbbay@gmail.com", "sub", "msg");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // True -> correo enviado
		
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