package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.Session;

import Database.DbConnection;
import Database.User;
import Database.Services.Movement.CreateMovement;
import Database.Services.UserService.ChangeOnlineStatus;
import Database.Services.UserService.FindUser;
import Mailer.Mailer;
import Models.DataRequestResponse;
import Models.LoginRequest;
import Models.MovementRequest;
import Models.SendEmailRequest;

public class ConnectionThread extends Thread{
	
	private Socket socket;
	private DbConnection dbconnection;
	private ObjectInputStream dataIS;
	private ObjectOutputStream dataOS;
	private User user;
	private Session session;
	
	
	
	public ConnectionThread(Socket socket, DbConnection dbconnection) {
		this.socket = socket;
		this.dbconnection = dbconnection;
	}
	
	public void run() {
		try {
			dataIS = new ObjectInputStream(socket.getInputStream());
			dataOS = new ObjectOutputStream(socket.getOutputStream());
			DataRequestResponse request;
			
//			RecieveEmailThread emailThread = new RecieveEmailThread("vbay.sanjose@alumnado.fundacionloyola.net", "67757111", true);
			
			while(true) {
				request = (DataRequestResponse) dataIS.readObject();

				switch (request.getAction()) {
				case "0001":
					String userName = ((LoginRequest)request.getData().get(0)).getUserName();
					String password = ((LoginRequest)request.getData().get(0)).getPassword();
					login(request.getAction(), userName,password);
					break;
				case "0004":
					returnUserData(request.getAction());
					break;
				case "0005":
					String movement = ((MovementRequest)request.getData().get(0)).getMovement();
					String date = ((MovementRequest)request.getData().get(0)).getDate();
					registerMovement(movement, date);
					break;
				case "006":
					SendEmailRequest emailRequest = (SendEmailRequest) request.getData().get(0); 
					sendEmail(request.getAction(), emailRequest.getFrom(), emailRequest.getPassword(), emailRequest.getTo(),
							emailRequest.getSub(), emailRequest.getMsg());
				}
			}
			
		} catch(java.net.SocketException ee) {
			ChangeOnlineStatus.changeOnlineStatus(false, user.getEmail(), user.getPassword());
			System.out.println("Client disconnected");
			return;
		}catch (IOException e) {
			System.out.println("Error in ConnectionThread (IOException), " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error in ConnectionThread (ClassNotFoundException), " + e.getMessage());
		}
	}
	
	public void startEmail() {
		try {
			RecieveEmailThread emailThread = new RecieveEmailThread(this.user.getEmail(), this.user.getPassword(), true, true, dataOS);
			emailThread.start();
		} catch (MessagingException e) {
//			DataRequestResponse response = new DataRequestResponse(request.getAction(), "Error", e.getMessage(), null);
//			dataOS.writeObject(response);
		}
		session = Mailer.getConnectionToPOP3(this.user.getEmail(), this.user.getPassword());
	}
	
	public void sendEmail(String action, String from, String password, String to, String sub, String msg) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", null);
		try {
			try {
				boolean result = Mailer.send(session, from, password, to, sub, msg); // True -> correo enviado
				response.addData(result);
				dataOS.writeObject(response);
			} catch (MessagingException e) {
				System.out.println("Error, in sendEmail (MessagingException) " + e.getMessage());
				response.setError("Error");
				response.setErrorMessage(e.getMessage());
				response.setData(null);
				dataOS.writeObject(response);
			}
		} catch (Exception e) {
			System.out.println("Error in sendEmail, " + e.getMessage());
		}
																	
	}
	
	public void login(String action, String email, String passwrd) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", null);
		this.user = FindUser.FindUser(email, passwrd);
		try {
			if(user.getId() != -1) { // Si id es igual a -1, entonces usuario no existe
				if(user.getOnline()) {
					response.setError("Error");
					response.setErrorMessage("Ya existe una sesión iniciada con esta cuenta");
					dataOS.writeObject(response);	
				}else {
					System.out.println("si");
					dataOS.writeObject(response);
					ChangeOnlineStatus.changeOnlineStatus(true, user.getEmail(), user.getPassword());
					startEmail();
				}
			}else {
				response.setError("Error");
				response.setErrorMessage("Usuario o contraseña incorrecto");
				dataOS.writeObject(response);
			}
		} catch (Exception e) {
			System.out.println("Error in login. " + e.getMessage());
		}
	}
		
	public void returnUserData(String action) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", new ArrayList<Object>());
		try {
			response.addData(user);
			dataOS.writeObject(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerMovement(String movement, String date) {
		CreateMovement.InsertMovement(user.getId(),user.getRole(), movement, date);
	}
}