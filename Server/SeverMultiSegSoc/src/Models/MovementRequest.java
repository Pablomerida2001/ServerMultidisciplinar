package Models;
/*
 * Clase MovementRequest
 * 
 * Modelo que se uteliza para guardar los datos del correo
 *  
 * @Author Grupo2
 * 
 * @Version 1.0
 * 
 */
import java.io.Serializable;

public class MovementRequest implements Serializable{
	private String movement;
	private String date;
	
	
	/**
     * Constructor con 2 parametros
     * @param movement - type String - movimiento del usuario
     * @param date - type String - fecha y hora del movimiento
     */
	public MovementRequest(String movement, String date) {
		super();
		this.movement = movement;
		this.date = date;
	}
	 /**
     * Constructor por defecto
     */
	public MovementRequest() {
		super();
	}
	
	/*
	 * Getters y Setters
	 */
	public String getMovement() {
		return movement;
	}
	public void setMovement(String movement) {
		this.movement = movement;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
