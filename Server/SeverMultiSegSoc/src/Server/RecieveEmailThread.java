package Server;

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
	
	public RecieveEmailThread(String user, String password, 
			boolean getAllEmails, boolean userStillOnLine, ObjectOutputStream outputStream)
			throws MessagingException {
		this.user = user;
		this.password = password;
		this.userStillOnLine = userStillOnLine;
		this.getAllEmails = getAllEmails;
		this.outputStream = outputStream;
//		this.inbox = Mailer.getConnectionToIMAP(user, password);
	}
	
	@Override
	public void run() {
		while(userStillOnLine) {
			DataRequestResponse response = new DataRequestResponse("1111", "", "", new ArrayList<Object>());
			try {
			    this.inbox = Mailer.getConnectionToIMAP(user, password);
				email = Mailer.readInboundEmails(inbox, user, password, getAllEmails);
			if (email.size() > 0) { // 
				response.addData(email);
				System.out.println(email.size());
				if(email.size() > 1) {
					Mailer.flagAsSeen(email.get(0), user, password);
				}
				outputStream.writeObject(response);
			} else {
				response.addData(email);
				System.out.println("Without new emails");
				outputStream.writeObject(response);
			}
			}catch (Exception e) {
				System.out.println("Error in RecieveEmailThread, " + e.getMessage());
			}
			try {
				this.sleep(30000);
			} catch (InterruptedException e) {
				System.out.println("Error in RecieveEmailThread (InterruptedException), " + e.getMessage());
			}
		}
	}

	public ArrayList<Message> getEmail() {
		return email;
	}

	public boolean isUserStillOnLine() {
		return userStillOnLine;
	}

	public void setUserStillOnLine(boolean userStillOnLine) {
		this.userStillOnLine = userStillOnLine;
	}
	
	
}
