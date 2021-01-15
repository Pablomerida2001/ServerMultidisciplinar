package Models;
/*
 * Clase RecieveEmailRequest
 * 
 * Modelo que se uteliza para guardar los datos que se utelizan para configurar el prpceso de recibo de correo
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;

public class RecieveEmailRequest implements Serializable{
	private boolean getAllEmail;
	private boolean userOnLine;

	 /**
     * Constructor con 2 parametros
     * @param AllEmails - type boolean - True -> recoget todos los correos
     * 										False -> recoget solo los correos no leidos
     * @param userOnLine - type boolean - True -> user online
     * 										False -> user offline
     */
	public RecieveEmailRequest(boolean getAllEmail, boolean userOnLine) {
		super();
		this.getAllEmail = getAllEmail;
		this.userOnLine = userOnLine;
	}
	
	/**
     * Constructor por defecto
     */
	public RecieveEmailRequest() {
		super();
	}

	/*
	 * Getters y Setters
	 */
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
