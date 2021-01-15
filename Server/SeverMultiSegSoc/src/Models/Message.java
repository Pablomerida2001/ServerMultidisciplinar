package Models;
/*
 * Clase Message
 * 
 * Modelo que se uteliza para guardar los datos del correo
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable{
	private int messageNumber;
	private String subject;
	private String messageBody;
	private Date date;
	private String from;
	
	/**
     * Constructor por defecto
     */
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * Constructor con 5 parametros
     * @param messageNumber - type int - id del correo
     * @param from - type String - email de la persona que ha enviado
     * @param to - type String - email a donde hay que enviar correo
     * @param sub - type String - asunto del correo
     * @param msg - type String - mensaje
     * @return voolean -> True -> se ha enviado correctamente
     * 					throw new MessagingException -> se ha ocurrido error
	 */
	public Message(int messageNumber, String subject, String messageBody, Date date, String from) {
		super();
		this.messageNumber = messageNumber;
		this.subject = subject;
		this.messageBody = messageBody;
		this.date = date;
		this.from = from;
	}
	
	/*
	 * Getters y Setters
	 */
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
