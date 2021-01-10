package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import Database.DbConnection;
import Database.User;
import Database.Services.Movement.CreateMovement;
import Database.Services.UserService.ChangeOnlineStatus;
import Database.Services.UserService.FindUser;
import Mailer.Mailer;

public class ConnectionThread extends Thread{
	
	Socket socket;
	DbConnection dbconnection;
	DataInputStream dataIS;
	DataOutputStream dataOS;
	User user;
	
	
	
	public ConnectionThread(Socket socket, DbConnection dbconnection) {
		this.socket = socket;
		this.dbconnection = dbconnection;
	}
	
	public void run() {
		try {
			dataIS = new DataInputStream(socket.getInputStream());
			dataOS = new DataOutputStream(socket.getOutputStream());
			
			while(true) {
				String msg = dataIS.readUTF();
				String[] values = msg.split("\\*");

				switch (values[0]) {
				case "0001":
					login(values[1], values[2]);
					break;
				case "0004":
					returnUserData();
					break;
				case "0005":
					registerMovement(values[1], values[2]);
					break;
				}
			}
			
			
			
		} catch(java.net.SocketException ee) {
			ChangeOnlineStatus.changeOnlineStatus(false, user.getEmail(), user.getPassword());
			System.out.println("Client disconnected");
			return;
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendEmail(String from, String password, String to, String sub, String msg) {
		try {
			try {
				boolean result = Mailer.send(from, password, to, sub, msg); // True -> correo enviado
				dataOS.writeInt(1);
			} catch (MessagingException e) {
				System.out.println("Error, in sendEmail (MessagingException) " + e.getMessage());
				dataOS.writeInt(0);
			}
		} catch (Exception e) {
			System.out.println("Error in login. " + e.getMessage());
		}
																	
	}
	
	public void login(String email, String passwrd) {
		this.user = FindUser.FindUser(email, passwrd);
		
		try {
			if(user.getId() != -1) { // Si id es igual a -1, entonces usuario no existe
				if(user.getOnline()) {
					dataOS.writeInt(1);	
				}else {
					dataOS.writeInt(0);
					ChangeOnlineStatus.changeOnlineStatus(true, user.getEmail(), user.getPassword());
				}
			}else {
				dataOS.writeInt(2);
			}
		} catch (Exception e) {
			System.out.println("Error in login. " + e.getMessage());
		}
	}
		
	public void returnUserData() {
		try {
			String msg = user.getId() + "*" + user.getName() + "*" + user.getSurname()+
					"*" +user.getRole() + "*" + user.getEmail() + "*" + user.getPassword();
			dataOS.writeUTF(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerMovement(String movement, String date) {
		CreateMovement.InsertMovement(user.getId(),user.getRole(), movement, date);
	}
}