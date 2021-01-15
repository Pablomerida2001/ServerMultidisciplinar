package Models;
/*
 * Clase SendEmailRequest
 * 
 * Modelo que se uteliza para guardar los datos del correo que hay que enviar
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;

public class SendEmailRequest implements Serializable{
	private String from;
	private String password;
	private String to;
	private String sub;
	private String msg;
	
	/*
	 * Constructor con 5 parametros
     * @param from - type String - email del usuario
     * @param password - type String - contraseña del usuario
     * @param to - type String - email a donde hay que enviar correo
     * @param sub - type String - asunto del correo
     * @param msg - type String - mensaje
	 */
	public SendEmailRequest(String from, String password, String to, String sub, String msg) {
		super();
		this.from = from;
		this.password = password;
		this.to = to;
		this.sub = sub;
		this.msg = msg;
	}

	/**
     * Constructor por defecto
     */
	public SendEmailRequest() {
		super();
	}

	/*
	 * Getters y Setters
	 */
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
