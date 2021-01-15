package Server;
/*
 * Metodo que gestiona las peticiones de un Cliente determinado
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.Session;

import Database.DbConnection;
import Database.User;
import Database.Services.GetManualContent.GetManualContent;
import Database.Services.Movement.CreateMovement;
import Database.Services.UserService.ChangeOnlineStatus;
import Database.Services.UserService.FindUser;
import Mailer.Mailer;
import Models.ContentModel;
import Models.ContentRequest;
import Models.DataRequestResponse;
import Models.LoginRequest;
import Models.Message;
import Models.MovementRequest;
import Models.RecieveEmailRequest;
import Models.SendEmailRequest;

public class ConnectionThread extends Thread{
	
	private Socket socket;
	private ObjectInputStream objectIS;
	private ObjectOutputStream objectOS;
	private User user;
	private Session session;
	private RecieveEmailThread emailThread;
	
	public ConnectionThread(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		try {
			objectIS = new ObjectInputStream(socket.getInputStream());
			objectOS = new ObjectOutputStream(socket.getOutputStream());
			DataRequestResponse request;
			
			while(true) {
				request = (DataRequestResponse) objectIS.readObject();

				switch (request.getAction()) {
				case "0001":
					String userName = ((LoginRequest)request.getData().get(0)).getUserName();
					String password = ((LoginRequest)request.getData().get(0)).getPassword();
					login(request.getAction(), userName,password, true);
					break;
				case "0002":
					userName = ((LoginRequest)request.getData().get(0)).getUserName();
					password = ((LoginRequest)request.getData().get(0)).getPassword();
					login(request.getAction(), userName,password, false);
					break;
				case "0003":
					returnAndroidContent(((ContentRequest)request.getData().get(0)).getIndex(),
							((ContentRequest)request.getData().get(0)).getLenguage());
					break;
				case "0004":
					returnUserData(request.getAction());
					break;
				case "0005":
					String movement = ((MovementRequest)request.getData().get(0)).getMovement();
					String date = ((MovementRequest)request.getData().get(0)).getDate();
					registerMovement(movement, date);
					break;
				case "0006":
					SendEmailRequest emailRequest = (SendEmailRequest) request.getData().get(0); 
					sendEmail(request.getAction(), emailRequest.getFrom(), emailRequest.getPassword(), emailRequest.getTo(),
							emailRequest.getSub(), emailRequest.getMsg());
					break;
				case "0007":
					startRecievingEmail((RecieveEmailRequest) request.getData().get(0));
					break;
				case "0008":
					changeStateOfRecievingEmails(((RecieveEmailRequest) request.getData().get(0)).isGetAllEmail());
					break;
				case "0009":
					flagAsSeen((Message) request.getData().get(0));
					break;
				}
			}
			
		} catch(java.net.SocketException ee) {
			if(user != null) {
				ChangeOnlineStatus.changeOnlineStatus(false, user.getEmail(), user.getPassword());
				emailThread.setUserStillOnLine(false); // Terminar el hilo cuando el usuario se desconecta
			}
			System.out.println("Client disconnected");
			return;
		}catch (IOException e) {
			System.out.println("Error in ConnectionThread (IOException), " + e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println("Error in ConnectionThread (ClassNotFoundException), " + e.getMessage());
		}
	}
	
	/*
	 * Cambia el hilo para recibir determinado estado de correos 
	 */
	public void changeStateOfRecievingEmails(boolean allEmails) {
		emailThread.setUserStillOnLine(false);
		emailThread.interrupt();
		try {
			emailThread = new RecieveEmailThread(this.user.getEmail(), this.user.getPassword(), 
					allEmails, true, objectOS);
			emailThread.start();
		} catch (MessagingException e) {
			System.out.println("Error in changeStateOfRecievingEmails " + e.getMessage());
		}
	}
	
	/*
	 * Actualizar el estado de correo (de 'No leido' a 'Leido')
	 */
	public void flagAsSeen(Message email) {
		try {
			Mailer.flagAsSeen(email, this.user.getEmail(), this.user.getPassword());
		} catch (MessagingException e) {
			System.out.println("Error in changeStateOfRecievingEmails " + e.getMessage());
		}
	}
	
	public void startRecievingEmail(RecieveEmailRequest emailRequest) {
		try {
			emailThread = new RecieveEmailThread(this.user.getEmail(), this.user.getPassword(), 
					emailRequest.isGetAllEmail(), emailRequest.isUserOnLine(), objectOS);
			emailThread.start();
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void sendEmail(String action, String from, String password, String to, String sub, String msg) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", null);
		try {
			try {
				session = Mailer.getConnectionToPOP3(from, password);
				boolean result = Mailer.send(session, from, password, to, sub, msg); // True -> correo enviado
				response.addData(result);
				objectOS.writeObject(response);
			} catch (MessagingException e) {
				System.out.println("Error, in sendEmail (MessagingException) " + e.getMessage());
				response.setError("Error");
				response.setErrorMessage(e.getMessage());
				objectOS.writeObject(response);
			}
		} catch (Exception e) {
			System.out.println("Error in sendEmail, " + e.getMessage());
		}
																	
	}
	
	public void login(String action, String email, String passwrd, boolean checkOnlineStatus) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", null);
		this.user = FindUser.FindUser(email, passwrd);
		try {
			if(user.getId() != -1) { // Si id es igual a -1, entonces usuario no existe
				if(checkOnlineStatus && user.getOnline()) {
					response.setError("Error");
					response.setErrorMessage("Ya existe una sesión iniciada con esta cuenta");
					objectOS.writeObject(response);	
				}else {
					System.out.println("si");
					objectOS.writeObject(response);
					if(checkOnlineStatus) {
						ChangeOnlineStatus.changeOnlineStatus(true, user.getEmail(), user.getPassword());
					}
				}
			}else {
				response.setError("Error");
				response.setErrorMessage("Usuario o contraseña incorrecto");
				objectOS.writeObject(response);
			}
		} catch (Exception e) {
			System.out.println("Error in login. " + e.getMessage());
		}
	}
		
	public void returnAndroidContent(int index, String lenguage) {
		String content = GetManualContent.getContent(index, lenguage);
		ContentModel textData = new ContentModel(content);
		DataRequestResponse response = new DataRequestResponse();
		response.setAction("0003");
		response.addData(textData);
		try {
			objectOS.writeObject(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void returnUserData(String action) {
		DataRequestResponse response = new DataRequestResponse(action, "", "", new ArrayList<Object>());
		try {
			response.addData(user);
			objectOS.writeObject(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void registerMovement(String movement, String date) {
		CreateMovement.InsertMovement(user.getId(),user.getRole(), movement, date);
	}
}