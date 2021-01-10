package Server;

import java.util.ArrayList;

import javax.mail.MessagingException;

import Mailer.Mailer;
import Models.Message;

public class RecieveEmailThread extends Thread {
	
	private String user;
	private String password;
	private ArrayList<Message> email = new ArrayList<Message>();
	private boolean userStillOnLine = false;
	
	public RecieveEmailThread(String user, String password, boolean userStillOnLine) {
		this.user = user;
		this.password = password;
		this.userStillOnLine = userStillOnLine;
	}
	
	@Override
	public void run() {
		while(userStillOnLine) {
			try {
				email = Mailer.readInboundEmails(user, password);
			if (email.size() > 0) {
				//Se va acrear objeto que se va a mandar a cliente
				//mandar a cliente
				System.out.println(email.size());
			} else {
				System.out.println("Without new emails");
			}
			} catch (MessagingException me) {
				System.out.println("Error in RecieveEmailThread, " + me.getMessage());
				break;
			}
			try {
				this.sleep(30000);
			} catch (InterruptedException e) {
				System.out.println("Error in RecieveEmailThread, " + e.getMessage());
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
