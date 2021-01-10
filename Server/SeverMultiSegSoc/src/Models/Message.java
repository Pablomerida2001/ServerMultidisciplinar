package Models;

import java.util.Date;

public class Message {
	private int messageNumber;
	private String subject;
	private String messageBody;
	private Date date;
	private String from;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(int messageNumber, String subject, String messageBody, Date date, String from) {
		super();
		this.messageNumber = messageNumber;
		this.subject = subject;
		this.messageBody = messageBody;
		this.date = date;
		this.from = from;
	}
	
	public int getMessageNumber() {
		return messageNumber;
	}

	public void setMessageNumber(int messageNumber) {
		this.messageNumber = messageNumber;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public Date getDate() {
		return date;
	}

	public String getFrom() {
		return from;
	}
	
	
}
