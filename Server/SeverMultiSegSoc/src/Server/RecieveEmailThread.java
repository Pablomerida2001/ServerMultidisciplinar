package Server;
/*
 * Clase RecieveEmailThread
 * 
 * Es un hilo que se encarga de recibir los correos
 * 
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.mail.Folder;
import javax.mail.MessagingException;

import Mailer.Mailer;
import Models.DataRequestResponse;
import Models.Message;

public class RecieveEmailThread extends Thread {
	
	private String user;
	private String password;
	private ArrayList<Message> email = new ArrayList<Message>();
	private boolean userStillOnLine = false;
	private boolean getAllEmails = false;
	private Folder inbox;
	private ObjectOutputStream outputStream; 
	private final int DELAY = 60000; // Retraso del proceso de recibo de email
	
	/**
     * Constructor con 5 parametros
     * @param user - type String - email del usuario
     * @param password - type String - contraseña del usuario
     * @param getAllEmails - type boolean - True -> recoger todo los correos
     * 										False -> recoger solo los correos leidos
     * @param userStillOnLine - type boolean - - True -> usuario online
     * 										False -> usuario offnline
     * @return User -> los datos del usuario
     */
	public RecieveEmailThread(String user, String password, 
			boolean getAllEmails, boolean userStillOnLine, ObjectOutputStream outputStream)
			throws MessagingException {
		this.user = user;
		this.password = password;
		this.userStillOnLine = userStillOnLine;
		this.getAllEmails = getAllEmails;
		this.outputStream = outputStream;
	}
	
	@Override
	public void run() {
		while(userStillOnLine) {
			DataRequestResponse response = new DataRequestResponse("1111", "", "", new ArrayList<Object>());
			boolean isAllowToSleep = true;
			try {
			    this.inbox = Mailer.getConnectionToIMAP(user, password); // Se establece la conecion con el servidor
				email = Mailer.readInboundEmails(inbox, user, password, getAllEmails);
				this.inbox.close(true); // Es necesario cerrar cada coneccion, ya que gmail no permite varias conecciones
			if (email.size() > 0) { // Si hay correos
				response.addData(email);
				outputStream.writeObject(response);
			} else { // No hay correos
				email.clear();
				email.add(new Message(-1, "", "", null, "No hay nuevos correos"));
				response.addData(email);
				outputStream.writeObject(response);
			}
			}catch (Exception e) {
				if(e.getMessage().contains("[AUTHENTICATIONFAILED]")) { // Error de identificacion o verificacion
					System.out.println("Inncorrect email or password ");
					userStillOnLine = false;
					isAllowToSleep = false;
					response.setError("Error");
					response.setErrorMessage("La contraseña o email es incorrecta");
					try {
						outputStream.writeObject(response);
					} catch (IOException e1) {
					}
					break;
				} else if(e.getMessage().contains("[ALERT]")){ // Alerta de coneciones simultaneas, etc..
					System.out.println("ALERT in RecieveEmailThread, " + e.getMessage());
				} else {
					System.out.println("Error in RecieveEmailThread " + e.getMessage());
					isAllowToSleep = false;
				}
			}
			if(isAllowToSleep) {
				try {
					this.sleep(DELAY);
				} catch (InterruptedException e) {
					System.out.println("Interrupcion de proceso sleep" + e.getMessage());
				}
			}
		}
	}

	/*
	 * Getters & Setters
	 */
	public ArrayList<Message> getEmail() {
		return email;
	}

	public boolean isUserStillOnLine() {
		return userStillOnLine;
	}

	public void setUserStillOnLine(boolean userStillOnLine) {
		this.userStillOnLine = userStillOnLine;
	}

	public void setGetAllEmails(boolean getAllEmails) {
		this.getAllEmails = getAllEmails;
	}
}