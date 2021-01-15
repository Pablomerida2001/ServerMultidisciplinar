package Models;
/*
 * Clase LoginRequest
 * 
 * Modelo que se uteliza para enviar los datos del login
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;

public class LoginRequest implements Serializable{
	private String userName;
	private String password;
	
	/**
     * Constructor con 2 parametros
     * @param userName - type String - email del usuario
     * @param password - type String - contraseña del usuario
     */
	public LoginRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	/**
     * Constructor por defecto
     */
	public LoginRequest() {
		super();
	}
	
	/*
	 * Getters y Setters
	 */
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
