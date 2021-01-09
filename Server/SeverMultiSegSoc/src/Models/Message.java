package Models;

import java.util.Date;

public class Message {
	private String subject;
	private String messageBody;
	private Date date;
	private String from;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String subject, String messageBody, Date date, String from) {
		super();
		this.subject = subject;
		this.messageBody = messageBody;
		this.date = date;
		this.from = from;
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
