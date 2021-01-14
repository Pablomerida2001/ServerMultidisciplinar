package Models;

import java.io.Serializable;

public class RecieveEmailRequest implements Serializable {
	private boolean getAllEmail;
	private boolean userOnLine;



	public RecieveEmailRequest(boolean getAllEmail, boolean userOnLine) {
		super();
		this.getAllEmail = getAllEmail;
		this.userOnLine = userOnLine;
	}


	public RecieveEmailRequest() {
		super();
	}


	public boolean isGetAllEmail() {
		return getAllEmail;
	}

	public void setGetAllEmail(boolean getAllEmail) {
		this.getAllEmail = getAllEmail;
	}


	public boolean isUserOnLine() {
		return userOnLine;
	}


	public void setUserOnLine(boolean userOnLine) {
		this.userOnLine = userOnLine;
	}


}